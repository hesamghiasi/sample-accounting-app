package ir.hesamghiasi.softwareengineering.sampleaccountingapp.services.contracts;

import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreCreatePersonRequest;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreCreatePersonResponse;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreUpdatePersonRequest;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreUpdatePersonResponse;
import org.springframework.stereotype.Service;

@Service
public interface PersonService {
    CoreCreatePersonResponse createPerson(CoreCreatePersonRequest coreRequest);
    CoreUpdatePersonResponse updatePerson(CoreUpdatePersonRequest coreRequest);
}
