package ir.hesamghiasi.softwareengineering.sampleaccountingapp.services.contracts;

import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreCreateAccountRequest;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreCreateAccountResponse;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreTransferMoneyRequest;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreTransferMoneyResponse;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    CoreCreateAccountResponse createAccount(CoreCreateAccountRequest coreRequest);
    CoreTransferMoneyResponse transferMoney(CoreTransferMoneyRequest coreRequest);
}
