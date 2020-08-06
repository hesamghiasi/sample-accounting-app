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
public class ApiCreateAccountRequest {
    @NonNull @NotEmpty @NotBlank
    //// TODO: 8/6/20 national code validator
    private String nationalCode;
    @NonNull @NotEmpty @NotBlank
    private String currency;
    @Min(1)
    private long balance;
}
