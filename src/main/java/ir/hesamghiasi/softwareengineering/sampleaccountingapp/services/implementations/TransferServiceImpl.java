package ir.hesamghiasi.softwareengineering.sampleaccountingapp.services.implementations;

import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreTransferMoneyRequest;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreTransferMoneyResponse;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.entities.Account;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.entities.MoneyTransfer;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.enums.CoreCurrency;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.exceptions.BalanceNotEnoughException;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.exceptions.DepositAccountNotFoundException;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.exceptions.WithdrawalAccountNotFoundException;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.exceptions.WithdrawalAndDepositEqualityException;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.repositories.AccountRepository;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.repositories.MoneyTransferRepository;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.services.contracts.TransferService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Hesam Ghiasi created on 8/6/20
 */
@Service
public class TransferServiceImpl implements TransferService {

    private final AccountRepository accountRepository;
    private final MoneyTransferRepository moneyTransferRepository;

    @Autowired
    public TransferServiceImpl(AccountRepository accountRepository, MoneyTransferRepository moneyTransferRepository) {
        this.accountRepository = accountRepository;
        this.moneyTransferRepository = moneyTransferRepository;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    //// TODO: 8/6/20 handle transaction exception and rollback
    public CoreTransferMoneyResponse transferMoney(CoreTransferMoneyRequest coreRequest) throws DepositAccountNotFoundException, WithdrawalAccountNotFoundException, BalanceNotEnoughException, WithdrawalAndDepositEqualityException {
        if(coreRequest.getDepositAccountId().equals(coreRequest.getWithdrawalAccountId()))
            throw new WithdrawalAndDepositEqualityException();
        CoreTransferMoneyResponse coreTransferMoneyResponse = new CoreTransferMoneyResponse();
        Optional<Account> withdrawalById = accountRepository.findById(coreRequest.getWithdrawalAccountId());
        Optional<Account> depositById = accountRepository.findById(coreRequest.getDepositAccountId());
        if(!withdrawalById.isPresent())
            throw new WithdrawalAccountNotFoundException();
        if(!depositById.isPresent())
            throw new DepositAccountNotFoundException();
        Account accountToWithdraw = withdrawalById.get();
        Account accountToDeposit = depositById.get();
        if(accountToWithdraw.getBalance() < coreRequest.getTransferAmount())
            throw new BalanceNotEnoughException();
        accountToWithdraw.setBalance(accountToWithdraw.getBalance() - coreRequest.getTransferAmount());
        float convertedTransferAmount = coreRequest.getTransferAmount() * this.getConversionCoefficient(accountToWithdraw.getCoreCurrency(), accountToDeposit.getCoreCurrency());
        accountToDeposit.setBalance(accountToDeposit.getBalance() + convertedTransferAmount);
        MoneyTransfer moneyTransfer = new MoneyTransfer();
        moneyTransfer.setDepositAccount(accountToDeposit);
        moneyTransfer.setWithdrawalAccount(accountToWithdraw);
        moneyTransfer.setTransferAmount(coreRequest.getTransferAmount());
        moneyTransfer.setTimestamp(java.sql.Timestamp.valueOf(LocalDateTime.now()));
        accountRepository.save(accountToWithdraw);
        accountRepository.save(accountToDeposit);
        moneyTransfer = moneyTransferRepository.save(moneyTransfer);
        BeanUtils.copyProperties(moneyTransfer, coreTransferMoneyResponse);
        coreTransferMoneyResponse.setDepositAccountId(accountToDeposit.getId());
        coreTransferMoneyResponse.setWithdrawalAccountId(accountToWithdraw.getId());
        coreTransferMoneyResponse.setTimestamp(moneyTransfer.getTimestamp().getTime());
        return coreTransferMoneyResponse;
    }

    private float getConversionCoefficient(CoreCurrency withdrawCurrency, CoreCurrency depositCurrency){
        //// TODO: 8/6/20 connect to an external service and get conversion coefficient
        //// TODO: 8/6/20 circuit breaker mechanism needed
        return 1;
    }
}
