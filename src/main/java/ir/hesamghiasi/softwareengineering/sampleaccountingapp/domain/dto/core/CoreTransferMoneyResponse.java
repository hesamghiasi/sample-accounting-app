package ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core;

import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.entities.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Hesam Ghiasi created on 8/6/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoreTransferMoneyResponse {
    private String id;
    private String withdrawalAccountId;
    private String depositAccountId;
    private float transferAmount;
    private long timestamp;
}
