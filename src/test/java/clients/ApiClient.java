package clients;

import dto.CreateUserRequest;
import dto.LoginUserRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static clients.Urls.BASE_URL;
import static io.restassured.RestAssured.given;

public class ApiClient {
    protected RequestSpecification getDefaultRequestSpecification() {
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON);
    }

    public Response createUser(CreateUserRequest createUserRequest) {
        return getDefaultRequestSpecification()
                .body(createUserRequest)//.log().all()
                .when()
                .post("/api/auth/register");

    }

    public Response loginUser(LoginUserRequest loginUserRequest) {
        return getDefaultRequestSpecification()
                .body(loginUserRequest)
                .when()
                .post("api/auth/login");
    }

    public Response deleteUser(String bearerToken) {
        return getDefaultRequestSpecification()
                .header("authorization", bearerToken)
                .when()
                .delete("/api/auth/user");
    }
}