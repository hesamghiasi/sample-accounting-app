package ir.hesamghiasi.softwareengineering.sampleaccountingapp.services.contracts;

import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreCreatePersonRequest;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreCreatePersonResponse;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreUpdatePersonRequest;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreUpdatePersonResponse;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.exceptions.DuplicateNationalCodeException;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.exceptions.PersonNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface PersonService {
    CoreCreatePersonResponse createPerson(CoreCreatePersonRequest coreRequest) throws DuplicateNationalCodeException;
    CoreUpdatePersonResponse updatePerson(CoreUpdatePersonRequest coreRequest) throws DuplicateNationalCodeException, PersonNotFoundException;
}
