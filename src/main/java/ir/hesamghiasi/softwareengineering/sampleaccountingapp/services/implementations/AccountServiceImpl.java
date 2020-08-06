package ir.hesamghiasi.softwareengineering.sampleaccountingapp.services.implementations;

import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreCreateAccountRequest;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreCreateAccountResponse;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreTransferMoneyRequest;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreTransferMoneyResponse;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.services.contracts.AccountService;
import org.springframework.stereotype.Service;

/**
 * @author Hesam Ghiasi created on 8/6/20
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Override
    public CoreCreateAccountResponse createAccount(CoreCreateAccountRequest coreRequest) {
        return null;
    }

    @Override
    public CoreTransferMoneyResponse transferMoney(CoreTransferMoneyRequest coreRequest) {
        return null;
    }
}
