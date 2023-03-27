package Backend;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class AuthTest {
    @Test
    void getXAuthToken1 () {
        Object response = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("username", "Viktor")
                .formParam("password", "e5f748c89d")
                .when()
                .post("https://test-stand.gb.ru/gateway/login")
                .then().extract()
                .jsonPath()
                .get("token")
                .toString();
        System.out.println("API.Token: " + response);
    }
    @Test
    void noGetXAuthTokenWithInvalidLogin(){
        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("username","Viktor01")
                .formParam("password","e5f748c89d")
                .when()
                .post("https://test-stand.gb.ru/gateway/login")
                .then()
                .statusCode(401);
    }
    @Test
    void noGetXAuthTokenWithInvalidPassword (){
        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("username","Viktor")
                .formParam("password","1234")
                .when()
                .post("https://test-stand.gb.ru/gateway/login")
                .then()
                .statusCode(401);
    }
}
