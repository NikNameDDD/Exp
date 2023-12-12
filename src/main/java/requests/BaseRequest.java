package requests;

import dataproviders.ConnectionsDataProvider;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class BaseRequest {

    protected RequestSpecification getBaseRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(ConnectionsDataProvider.getInstance().getBaseUri())
                .setRelaxedHTTPSValidation()
                .setContentType("application/json")
                .build();
    }
}