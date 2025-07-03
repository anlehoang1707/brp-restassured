package brp.commons;

import brp.model.LoginPOJO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class BaseTest {

    protected int generateRandomNumber() {
        return new Random().nextInt(9999);
    }

    protected void login() throws JsonProcessingException {
        LoginPOJO login = new LoginPOJO();
        login.setUsername("anhtester");
        login.setPassword("Demo@123");
        ObjectMapper objectMapper = new ObjectMapper();
        String loginBody = objectMapper.writeValueAsString(login);

        RequestSpecification request = given();
        request.baseUri(GlobalConstants.BASE_URI).header("accept","application/json").contentType("application/json").body(loginBody);

        Response response = request.when().post("/login");
        response.then().statusCode(200);

        GlobalConstants.TOKEN = response.jsonPath().getString("token");
    }

    protected RequestSpecification getPreSetupRequest(String baseUri) {
        return given().baseUri(baseUri).contentType("application/json").header("Accept", "application/json")
                .header("Content-Type", "application/json");
    }

    protected RequestSpecification getPreSetupRequest(String baseUri, String token) {
        return given().baseUri(baseUri).contentType("application/json").header("Accept", "application/json")
                .header("Content-Type", "application/json").header("Authorization","Bearer "+ token);
    }
}
