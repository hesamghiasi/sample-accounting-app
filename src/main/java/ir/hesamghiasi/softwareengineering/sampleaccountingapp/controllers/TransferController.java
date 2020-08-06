package ir.hesamghiasi.softwareengineering.sampleaccountingapp.controllers;

import io.swagger.annotations.Api;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.api.ApiTransferRequest;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.api.ApiTransferResponse;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreTransferMoneyRequest;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreTransferMoneyResponse;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.exceptions.BalanceNotEnoughException;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.exceptions.DepositAccountNotFoundException;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.exceptions.WithdrawalAccountNotFoundException;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.exceptions.WithdrawalAndDepositEqualityException;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.services.contracts.TransferService;
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
@RequestMapping("/money-transfer")
@Slf4j
@Api(value = "Account", produces = "application/json", consumes = "application/json", tags = "Account")
public class TransferController {

    //// TODO: 8/6/20 swagger docs
    //// TODO: 8/6/20 handle controller exceptions with controller advice (validations, ...)

    private final TransferService transferService;

    @Autowired
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("")
    public ResponseEntity<ApiTransferResponse> transferMoney(
            @Valid @RequestBody ApiTransferRequest transferRequest
    )
    {
        ApiTransferResponse apiTransferResponse = new ApiTransferResponse();
        CoreTransferMoneyRequest coreTransferMoneyRequest = new CoreTransferMoneyRequest();
        BeanUtils.copyProperties(transferRequest, coreTransferMoneyRequest);
        CoreTransferMoneyResponse coreTransferMoneyResponse = null;
        try {
            coreTransferMoneyResponse = transferService.transferMoney(coreTransferMoneyRequest);
        } catch (DepositAccountNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Deposit account not found");
        } catch (WithdrawalAccountNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Withdrawal account not found");
        } catch (BalanceNotEnoughException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Balance not enough");
        } catch (WithdrawalAndDepositEqualityException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Withdraw account cannot be the same as deposit account");
        }
        BeanUtils.copyProperties(coreTransferMoneyResponse, apiTransferResponse);
        return new ResponseEntity<>(apiTransferResponse, HttpStatus.CREATED);
    }

}
