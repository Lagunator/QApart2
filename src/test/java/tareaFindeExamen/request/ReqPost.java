package tareaFindeExamen.request;

import io.restassured.response.Response;
import tareaFindeExamen.Configuration;

import static io.restassured.RestAssured.given;

public class ReqPost implements IRequest {
    private Response sendRequest(RequestInfo requestInfo) {
        return given()
                .auth()
                .preemptive()
                .basic(Configuration.user, Configuration.password)
                .body(requestInfo.getBody())
                .log()
                .all()
                .when()
                .post(requestInfo.getUrl());
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
                .body(requestInfo.getBody())
                .log()
                .all()
                .when()
                .post(requestInfo.getUrl());
    }

    @Override
    public Response sendWithToken(RequestInfo requestInfo) {
        Response response = sendRequestWithToken(requestInfo);
        response.then().log().all();
        return response;
    }
}

