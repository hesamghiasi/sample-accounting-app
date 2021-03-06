package ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.api;

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
public class ApiUpdatePersonRequest {
    private String firstName;
    private String lastName;
    //// TODO: 8/6/20 national code validator
    private String nationalCode;
    private String mobileNumber;
    private String homeNumber;
    private String fatherName;
}
