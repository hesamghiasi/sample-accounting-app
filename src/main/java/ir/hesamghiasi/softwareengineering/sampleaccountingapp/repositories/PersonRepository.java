package ir.hesamghiasi.softwareengineering.sampleaccountingapp.repositories;

import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {

}
