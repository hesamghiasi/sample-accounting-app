package ir.hesamghiasi.softwareengineering.sampleaccountingapp.services.implementations;

import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreCreateAccountRequest;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreCreateAccountResponse;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreTransferMoneyRequest;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreTransferMoneyResponse;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.entities.Account;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.entities.Person;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.exceptions.PersonNotFoundException;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.repositories.AccountRepository;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.repositories.PersonRepository;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.services.contracts.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Hesam Ghiasi created on 8/6/20
 */
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final PersonRepository personRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, PersonRepository personRepository) {
        this.accountRepository = accountRepository;
        this.personRepository = personRepository;
    }

    @Override
    public CoreCreateAccountResponse createAccount(CoreCreateAccountRequest coreRequest) throws PersonNotFoundException {
        CoreCreateAccountResponse coreCreateAccountResponse = new CoreCreateAccountResponse();
        Optional<Person> byId = personRepository.findById(coreRequest.getPersonId());
        if(!byId.isPresent())
            throw new PersonNotFoundException();
        Account account = new Account();
        account.setPerson(byId.get());
        account.setCoreCurrency(coreRequest.getCurrency());
        account.setBalance(coreRequest.getBalance());
        accountRepository.save(account);
        BeanUtils.copyProperties(account, coreCreateAccountResponse);
        coreCreateAccountResponse.setPersonId(account.getPerson().getId());
        return coreCreateAccountResponse;
    }

}
