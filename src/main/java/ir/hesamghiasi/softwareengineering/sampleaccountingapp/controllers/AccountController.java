package ir.hesamghiasi.softwareengineering.sampleaccountingapp.controllers;

import io.swagger.annotations.Api;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.api.ApiCreateAccountRequest;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.api.ApiCreateAccountResponse;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.api.ApiTransferRequest;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.api.ApiTransferResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Hesam Ghiasi created on 8/6/20
 */
@RestController
@RequestMapping("/accounts")
@Slf4j
@Api(value = "Account", produces = "application/json", consumes = "application/json", tags = "Account")
public class AccountController {

    @PostMapping("")
    public ResponseEntity<ApiCreateAccountResponse> createAccount(@Valid @RequestBody ApiCreateAccountRequest createAccountRequest){
        ApiCreateAccountResponse apiCreateAccountResponse = new ApiCreateAccountResponse();

        return new ResponseEntity(apiCreateAccountResponse, HttpStatus.CREATED);
    }

    @PatchMapping("")
    public ResponseEntity<ApiTransferResponse> transfer(@Valid @RequestBody ApiTransferRequest transferRequest){
        ApiTransferResponse apiTransferResponse = new ApiTransferResponse();

        return new ResponseEntity<>(apiTransferResponse, HttpStatus.NO_CONTENT);
    }
}
