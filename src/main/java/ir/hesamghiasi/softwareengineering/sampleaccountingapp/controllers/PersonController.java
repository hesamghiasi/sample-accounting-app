package ir.hesamghiasi.softwareengineering.sampleaccountingapp.controllers;

import io.swagger.annotations.Api;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.api.ApiCreatePersonRequest;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.api.ApiCreatePersonResponse;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.api.ApiUpdatePersonRequest;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.api.ApiUpdatePersonResponse;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreCreatePersonRequest;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreCreatePersonResponse;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreUpdatePersonRequest;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreUpdatePersonResponse;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.exceptions.DuplicateNationalCodeException;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.exceptions.PersonNotFoundException;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.services.contracts.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

/**
 * @author Hesam Ghiasi created on 8/6/20
 */
@RestController
@RequestMapping("/persons")
@Slf4j
@Api(value = "Person", produces = "application/json", consumes = "application/json", tags = "Person")

public class PersonController {

    //// TODO: 8/6/20 swagger docs
    //// TODO: 8/6/20 handle controller exceptions with controller advice (validations, ...)

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("")
    public ResponseEntity<ApiCreatePersonResponse> createPerson(@Valid @RequestBody ApiCreatePersonRequest createPersonRequest) {
        ApiCreatePersonResponse apiCreatePersonResponse = new ApiCreatePersonResponse();
        CoreCreatePersonRequest coreCreatePersonRequest = new CoreCreatePersonRequest();
        BeanUtils.copyProperties(createPersonRequest, coreCreatePersonRequest);
        CoreCreatePersonResponse person = null;
        try {
            person = personService.createPerson(coreCreatePersonRequest);
        } catch (DuplicateNationalCodeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicate national code");
        }
        BeanUtils.copyProperties(person, apiCreatePersonResponse);
        return new ResponseEntity<>(apiCreatePersonResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{personId}")
    public ResponseEntity<ApiUpdatePersonResponse> updatePerson(
            @Valid @RequestBody ApiUpdatePersonRequest updatePersonRequest,
            @PathVariable String personId
    ) {
        ApiUpdatePersonResponse apiUpdatePersonResponse = new ApiUpdatePersonResponse();
        CoreUpdatePersonRequest coreUpdatePersonRequest = new CoreUpdatePersonRequest();
        BeanUtils.copyProperties(updatePersonRequest, coreUpdatePersonRequest);
        coreUpdatePersonRequest.setPersonId(personId);
        CoreUpdatePersonResponse coreUpdatePersonResponse = null;
        try {
            coreUpdatePersonResponse = personService.updatePerson(coreUpdatePersonRequest);
        } catch (DuplicateNationalCodeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicate national code");
        } catch (PersonNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found");
        }
        BeanUtils.copyProperties(coreUpdatePersonResponse, apiUpdatePersonResponse);
        return new ResponseEntity<>(apiUpdatePersonResponse, HttpStatus.OK);
    }
}
