package ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.api;

import ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.enums.ApiCurrency;
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
public class ApiCreateAccountRequest {
    @NonNull @NotEmpty @NotBlank
    //// TODO: 8/6/20 national code validator
    private String personId;
    @NonNull @NotEmpty @NotBlank
    private ApiCurrency currency;
    @Min(1)
    private float balance;
}
