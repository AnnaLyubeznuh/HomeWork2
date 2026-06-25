import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestPostman {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    public void testGetRequest() {
        given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .body("headers.host", equalTo("postman-echo.com"));
    }

    @Test
    public void testPostRawText() {
        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .contentType(ContentType.TEXT)
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", equalTo(requestBody))
                .body("headers.host", equalTo("postman-echo.com"));
    }

    @Test
    public void testPostFormData() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("foo1", "bar1")
                .formParam("foo2", "bar2")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("form.foo1", equalTo("bar1"))
                .body("form.foo2", equalTo("bar2"))
                .body("headers.host", equalTo("postman-echo.com"));
    }

    @Test
    public void testPutRequest() {
        String putBody = "Updated string content";

        given()
                .contentType(ContentType.TEXT)
                .body(putBody)
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .body("data", equalTo(putBody))
                .body("headers.host", equalTo("postman-echo.com"));
    }

    @Test
    public void testPatchRequest() {
        String patchBody = "Patched string content";

        given()
                .contentType(ContentType.TEXT)
                .body(patchBody)
                .when()
                .patch("/patch")
                .then()
                .statusCode(200)
                .body("data", equalTo(patchBody))
                .body("headers.host", equalTo("postman-echo.com"));
    }

    @Test
    public void testDeleteRequest() {
        String deleteBody = "Deleted content statement";

        given()
                .contentType(ContentType.TEXT)
                .body(deleteBody)
                .when()
                .delete("/delete")
                .then()
                .statusCode(200)
                .body("data", equalTo(deleteBody))
                .body("headers.host", equalTo("postman-echo.com"));
    }
}
