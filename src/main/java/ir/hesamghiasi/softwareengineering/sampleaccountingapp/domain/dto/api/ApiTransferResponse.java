package ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Hesam Ghiasi created on 8/6/20
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiTransferResponse {
    private String id;
    private String withdrawalAccountId;
    private String depositAccountId;
    private float transferAmount;
    private long timestamp;
}
