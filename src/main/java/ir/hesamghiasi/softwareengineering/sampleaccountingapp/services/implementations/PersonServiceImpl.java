package ir.hesamghiasi.softwareengineering.sampleaccountingapp.services.implementations;

import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreCreatePersonRequest;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreCreatePersonResponse;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreUpdatePersonRequest;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core.CoreUpdatePersonResponse;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.entities.Person;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.exceptions.DuplicateNationalCodeException;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.exceptions.PersonNotFoundException;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.repositories.PersonRepository;
import ir.hesamghiasi.softwareengineering.sampleaccountingapp.services.contracts.PersonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public CoreCreatePersonResponse createPerson(CoreCreatePersonRequest coreRequest) throws DuplicateNationalCodeException {
        CoreCreatePersonResponse coreCreatePersonResponse = new CoreCreatePersonResponse();
        Optional<Person> byNationalCode = personRepository.findByNationalCode(coreRequest.getNationalCode());
        if(byNationalCode.isPresent())
            throw new DuplicateNationalCodeException();
        Person person = new Person();
        BeanUtils.copyProperties(coreRequest, person);
        person = personRepository.save(person);
        BeanUtils.copyProperties(person, coreCreatePersonResponse);
        return coreCreatePersonResponse;
    }

    @Override
    public CoreUpdatePersonResponse updatePerson(CoreUpdatePersonRequest coreRequest) throws DuplicateNationalCodeException, PersonNotFoundException {
        CoreUpdatePersonResponse coreUpdatePersonResponse = new CoreUpdatePersonResponse();
        Optional<Person> byId = personRepository.findById(coreRequest.getPersonId());
        if(!byId.isPresent())
            throw new PersonNotFoundException();
        Person personToBeUpdated = byId.get();
        if(coreRequest.getNationalCode() != null && !coreRequest.getNationalCode().isEmpty()){
            Optional<Person> byNationalCode = personRepository.findByNationalCode(coreRequest.getNationalCode());
            if(byNationalCode.isPresent() && !personToBeUpdated.getId().equals(byNationalCode.get().getId()))
                throw new DuplicateNationalCodeException();
            personToBeUpdated.setNationalCode(coreRequest.getNationalCode());
        }
        if(coreRequest.getFatherName() != null)
            personToBeUpdated.setFatherName(coreRequest.getFatherName());
        if(coreRequest.getFirstName() != null && !coreRequest.getFirstName().isEmpty())
            personToBeUpdated.setFirstName(coreRequest.getFirstName());
        if(coreRequest.getLastName() != null && !coreRequest.getLastName().isEmpty())
            personToBeUpdated.setLastName(coreRequest.getLastName());
        if(coreRequest.getHomeNumber() != null)
            personToBeUpdated.setHomeNumber(coreRequest.getHomeNumber());
        if(coreRequest.getMobileNumber() != null && !coreRequest.getMobileNumber().isEmpty())
            personToBeUpdated.setMobileNumber(coreRequest.getMobileNumber());
        personToBeUpdated = personRepository.save(personToBeUpdated);
        BeanUtils.copyProperties(personToBeUpdated, coreUpdatePersonResponse);
        return coreUpdatePersonResponse;
    }
}
