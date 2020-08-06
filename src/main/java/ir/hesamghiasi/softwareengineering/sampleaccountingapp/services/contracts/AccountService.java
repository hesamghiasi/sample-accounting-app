package ir.hesamghiasi.softwareengineering.sampleaccountingapp.services.contracts;

import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreCreateAccountRequest;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreCreateAccountResponse;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreTransferMoneyRequest;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreTransferMoneyResponse;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.exceptions.PersonNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    CoreCreateAccountResponse createAccount(CoreCreateAccountRequest coreRequest) throws PersonNotFoundException;}
