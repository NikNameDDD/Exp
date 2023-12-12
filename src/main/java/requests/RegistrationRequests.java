package requests;

import io.restassured.response.ValidatableResponse;
import pojos.User;

import static io.restassured.RestAssured.given;
import static requests.UserServiceEndpoints.REGISTER_NON_EXISTENT_CLIENT;

public class RegistrationRequests extends BaseRequest {
    private static RegistrationRequests instance;

    private RegistrationRequests() {}

    public static RegistrationRequests getInstance() {
        if (instance == null) {
            instance = new RegistrationRequests();
        }
        return instance;
    }

    public ValidatableResponse saveNonExistentClientIntoApplicationProfiles(User user) {
        return given()
                .spec(getBaseRequestSpecification())
                .body(user)
                .when()
                .post(REGISTER_NON_EXISTENT_CLIENT.toString())
                .then();
    }
}
