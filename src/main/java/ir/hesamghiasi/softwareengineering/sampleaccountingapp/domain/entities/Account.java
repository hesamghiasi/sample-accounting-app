package ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Hesam Ghiasi created on 8/6/20
 */
@Entity
@Table(name = "account")
@Data
public class Account {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    protected String id;
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="person_id")
    private Person person;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private long balance;
}
