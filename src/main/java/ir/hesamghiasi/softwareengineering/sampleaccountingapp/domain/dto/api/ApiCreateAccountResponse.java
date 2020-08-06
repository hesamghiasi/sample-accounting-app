package ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Hesam Ghiasi created on 8/6/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiCreateAccountResponse {
    private String id;
    private String nationalCode;
    private String currency;
    private long balance;
}
