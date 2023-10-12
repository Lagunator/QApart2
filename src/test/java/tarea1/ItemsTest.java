package tarea1;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ItemsTest {
    private static JSONObject itemBody;
    private static int idProject;

    @BeforeAll
    public static void setup() {
        itemBody = new JSONObject();
    }

    @Test
    public void crudItems() {
        itemBody.put("Content", "Lagu llora mucha tarea");

        // Create Item
        Response response = given().
                auth().
                preemptive().
                basic("upbapi@upbapi.com", "12345").
                body(itemBody.toString()).
                log().
                all().
                when().
                post("https://todo.ly/api/items.json");

        response.then().
                log().
                all().
                statusCode(200).
                body("Content", equalTo(itemBody.get("Content")));

        idProject = response.then().extract().path("Id");

        // Read Item
        response = given().
                auth().
                preemptive().
                basic("upbapi@upbapi.com", "12345").
                body(itemBody.toString()).
                log().
                all().
                when().
                get(String.format("https://todo.ly/api/items/%d.json", idProject));
        response.then().
                log().
                all().
                statusCode(200).
                body("Content", equalTo(itemBody.get("Content")));

        // Update Item
        itemBody.put("Content", "Lagu llora mucha tarea");


        response = given().
                auth().
                preemptive().
                basic("upbapi@upbapi.com", "12345").
                body(itemBody.toString()).
                log().
                all().
                when().
                put(String.format("https://todo.ly/api/items/%d.json", idProject));
        response.then().
                log().
                all().
                statusCode(200).
                body("Content", equalTo(itemBody.get("Content")));

        // Delete Item
        response = given().
                auth().
                preemptive().
                basic("upbapi@upbapi.com", "12345").
                body(itemBody.toString()).
                log().
                all().
                when().
                delete(String.format("https://todo.ly/api/items/%d.json", idProject));
        response.then().
                log().
                all().
                statusCode(200).
                body("Content", equalTo(itemBody.get("Content"))).
                body("Deleted", equalTo(true));

    }

}
