package helpers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RequestHelper {

    private static final String BASE_URI = "https://serverest.dev";

    public static void setup() {
        RestAssured.baseURI = BASE_URI;
    }

    public static RequestSpecification given() {
        return RestAssured.given()
            .contentType(ContentType.JSON)
            .log().all();
    }

    public static Response post(String path, String jsonBody) {
        return given()
            .body(jsonBody)
        .when()
            .post(path)
        .then()
            .log().all()
            .extract().response();
    }
}
