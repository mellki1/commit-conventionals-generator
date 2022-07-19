package dev.melqui.commitconventionalsgerenatorapi;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class CommitResourceTest {

    @Test
    void tetPersistEndpoint() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"type\":\"FEATURE\",\"sprintNumber\":1,\"taskId\":\"TASK-1\",\"message\":\"Message\"}")
                .post("/commit")
                .then()
                .statusCode(200)
                .body("type", is("FEATURE"))
                .body("sprintNumber", is(1))
                .body("taskId", is("TASK-1"))
                .body("message", is("Message"));
    }
}