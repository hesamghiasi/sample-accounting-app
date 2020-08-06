package ir.hesamghiasi.softwareengineering.sampleaccountingapp.controllers;

import io.swagger.annotations.Api;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.api.ApiCreatePersonRequest;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.api.ApiCreatePersonResponse;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.api.ApiUpdatePersonRequest;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.api.ApiUpdatePersonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Hesam Ghiasi created on 8/6/20
 */
@RestController
@RequestMapping("/persons")
@Slf4j
@Api(value = "Person", produces = "application/json", consumes = "application/json", tags = "Person")
public class PersonController {

    @PostMapping("")
    public ResponseEntity<ApiCreatePersonResponse> createPerson(@Valid @RequestBody ApiCreatePersonRequest createPersonRequest){
        ApiCreatePersonResponse apiCreatePersonResponse =  new ApiCreatePersonResponse();

        return new ResponseEntity<>(apiCreatePersonResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{personId}")
    public ResponseEntity<ApiUpdatePersonResponse> updatePerson(@Valid @RequestBody ApiUpdatePersonRequest updatePersonRequest){
        ApiUpdatePersonResponse apiUpdatePersonResponse = new ApiUpdatePersonResponse();

        return new ResponseEntity<>(apiUpdatePersonResponse, HttpStatus.OK);
    }
}
