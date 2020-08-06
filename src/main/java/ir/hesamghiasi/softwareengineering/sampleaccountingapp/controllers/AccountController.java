package ir.hesamghiasi.softwareengineering.sampleaccountingapp.controllers;

import io.swagger.annotations.Api;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.api.ApiCreateAccountRequest;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.api.ApiCreateAccountResponse;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreCreateAccountRequest;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreCreateAccountResponse;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.enums.CoreCurrency;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.exceptions.PersonNotFoundException;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.services.contracts.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

/**
 * @author Hesam Ghiasi created on 8/6/20
 */
@RestController
@RequestMapping("/accounts")
@Slf4j
@Api(value = "Account", produces = "application/json", consumes = "application/json", tags = "Account")
public class AccountController {

    //// TODO: 8/6/20 swagger docs
    //// TODO: 8/6/20 handle controller exceptions with controller advice (validations, ...)

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("")
    public ResponseEntity<ApiCreateAccountResponse> createAccount(@Valid @RequestBody ApiCreateAccountRequest createAccountRequest) {
        ApiCreateAccountResponse apiCreateAccountResponse = new ApiCreateAccountResponse();
        CoreCreateAccountRequest coreCreateAccountRequest = new CoreCreateAccountRequest();
        BeanUtils.copyProperties(createAccountRequest, coreCreateAccountRequest);
        coreCreateAccountRequest.setCurrency(CoreCurrency.valueOf(createAccountRequest.getCurrency().name()));
        CoreCreateAccountResponse account = null;
        try {
            account = accountService.createAccount(coreCreateAccountRequest);
        } catch (PersonNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found");
        }
        BeanUtils.copyProperties(account, apiCreateAccountResponse);
        apiCreateAccountResponse.setCurrency(createAccountRequest.getCurrency());
        return new ResponseEntity(apiCreateAccountResponse, HttpStatus.CREATED);
    }

}
