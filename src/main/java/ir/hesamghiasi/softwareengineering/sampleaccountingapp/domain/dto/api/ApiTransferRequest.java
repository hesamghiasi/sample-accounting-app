package ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @author Hesam Ghiasi created on 8/6/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiTransferRequest {
    @NonNull @NotEmpty @NotBlank
    private String withdrawalAccountId;
    @NonNull @NotEmpty @NotBlank
    private String depositAccountId;
    @Min(1)
    private float transferAmount;
}
