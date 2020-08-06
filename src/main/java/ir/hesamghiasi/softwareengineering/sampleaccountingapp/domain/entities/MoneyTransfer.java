package ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.entities;

import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.enums.CoreCurrency;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Hesam Ghiasi created on 8/6/20
 */
@Entity
@Table(name = "money_transfer")
@Data
public class MoneyTransfer {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="withdraw_account_id")
    private Account withdrawalAccount;
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="deposit_account_id")
    private Account depositAccount;
    private float transferAmount;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date timestamp;

}
