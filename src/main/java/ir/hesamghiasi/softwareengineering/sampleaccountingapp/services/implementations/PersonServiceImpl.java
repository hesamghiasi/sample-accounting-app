package ir.hesamghiasi.softwareengineering.sampleaccountingapp.services.implementations;

import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreCreatePersonRequest;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreCreatePersonResponse;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreUpdatePersonRequest;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreUpdatePersonResponse;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.repositories.PersonRepository;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.services.contracts.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Hesam Ghiasi created on 8/6/20
 */
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public CoreCreatePersonResponse createPerson(CoreCreatePersonRequest coreRequest) {

        return null;
    }

    @Override
    public CoreUpdatePersonResponse updatePerson(CoreUpdatePersonRequest coreRequest) {
        return null;
    }
}
