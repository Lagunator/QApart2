package tareaFindeExamen.request;
import tareaFindeExamen.Configuration;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ReqDelete implements IRequest {
    private Response sendRequest(RequestInfo requestInfo) {
        return given()
                .auth()
                .preemptive()
                .basic(Configuration.user, Configuration.password)
                .log()
                .all()
                .when()
                .delete(requestInfo.getUrl());
    }

    @Override
    public Response send(RequestInfo requestInfo) {
        Response response = sendRequest(requestInfo);
        response.then().log().all();
        return response;
    }

    private Response sendRequestWithToken(RequestInfo requestInfo) {
        return given()
                .header("Token", Configuration.token)
                .log()
                .all()
                .when()
                .delete(requestInfo.getUrl());
    }

    @Override
    public Response sendWithToken(RequestInfo requestInfo) {
        Response response = sendRequestWithToken(requestInfo);
        response.then().log().all();
        return response;
    }
}

