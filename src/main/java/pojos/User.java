package pojos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {

    private String passportNumber;
    private String firstName;
    private String lastName;
    private String password;
    private String mobilePhone;
    private String securityQuestion;
    private String securityAnswer;
    private String middleName;
    private boolean countryOfResidence;
    private String email;
    private String id;
    private String clientStatus;
}
