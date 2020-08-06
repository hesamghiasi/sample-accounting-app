package ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * @author Hesam Ghiasi created on 8/6/20
 */
@Entity
@Data
@Table(name = "person", uniqueConstraints = @UniqueConstraint(columnNames = {"nationalCode"}))
public class Person {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    protected String id;
    private String firstName;
    private String lastName;
    private String nationalCode;
    private String mobileNumber;
    private String homeNumber;
    private String fatherName;
    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Account> accounts;
}
