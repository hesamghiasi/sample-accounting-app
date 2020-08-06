package ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @author Hesam Ghiasi created on 8/6/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoreTransferMoneyRequest {
    private String withdrawalAccountId;
    private String depositAccountId;
    private float transferAmount;
}
