package ir.hesamghiasi.softwareengineering.sampleaccountingapp.repositories;

import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
}
