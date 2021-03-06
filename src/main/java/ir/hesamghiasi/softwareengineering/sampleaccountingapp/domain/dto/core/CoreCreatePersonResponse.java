package ir.hesamghiasi.softwareengineering.sampleaccountingapp.domain.dto.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Hesam Ghiasi created on 8/6/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoreCreatePersonResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String nationalCode;
    private String mobileNumber;
    private String homeNumber;
    private String fatherName;
}
