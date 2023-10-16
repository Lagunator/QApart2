package tareaFindeExamen.cases;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tareaFindeExamen.Configuration;
import tareaFindeExamen.request.FactoryRequest;
import tareaFindeExamen.request.RequestInfo;

import static org.hamcrest.Matchers.equalTo;

public class CrudToken extends TestBase {

    @BeforeAll
    public static void setup() {
        requestInfo = new RequestInfo();
        requestInfo.setUrl(Configuration.host + "/api/authentication/token.json");
        response = FactoryRequest.make("get").send(requestInfo);
        Configuration.token = response.then().extract().path("TokenString");
        System.out.println(Configuration.token);
    }

    @Test
    public void createUpdateDeleteProject() {
        JSONObject body = new JSONObject();
        body.put("Content", "My Project UNO");

        int idProject = createProject(Configuration.host + "/api/projects.json", body);
        readProject(idProject, body);

        body.put("Content", "My Project DOS");
        updateProject(Configuration.host + "/api/projects/" + idProject + ".json", body);
        deleteProject(idProject, body);
    }

    private void deleteProject(int idProject, JSONObject body) {
        requestInfo.setUrl(Configuration.host + "/api/projects/" + idProject + ".json");
        response = FactoryRequest.make("delete").sendWithToken(requestInfo);
        validateResponse(body);
    }

    private void updateProject(String host, JSONObject body) {
        requestInfo.setUrl(host)
                .setBody(body.toString());
        response = FactoryRequest.make("put").sendWithToken(requestInfo);
        validateResponse(body);
    }

    private void readProject(int idProject, JSONObject body) {
        requestInfo.setUrl(Configuration.host + "/api/projects/" + idProject + ".json");
        response = FactoryRequest.make("get").sendWithToken(requestInfo);
        validateResponse(body);
    }

    private int createProject(String host, JSONObject body) {
        requestInfo.setUrl(host)
                .setBody(body.toString());
        response = FactoryRequest.make("post").sendWithToken(requestInfo);
        return validateResponse(body);
    }

    private int validateResponse(JSONObject body) {
        response.then().statusCode(200).
                body("Content", equalTo(body.get("Content")));
        return response.then().extract().path("Id");
    }
}

