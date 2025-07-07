package brp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import brp.model.LoginPOJO;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Level_03_RestAssured_In_TestClass_With_Authentication_JsonPath_POJO_Lombok {
    RequestSpecification request;

    @BeforeClass
    public void beforeMethod() throws JsonProcessingException {
        LoginPOJO login = new LoginPOJO();
        login.setUsername("anhtester");
        login.setPassword("Demo@123");
        ObjectMapper objectMapper = new ObjectMapper();
        String loginBody = objectMapper.writeValueAsString(login);

        request = given();
        request.baseUri("https://api.anhtester.com/api").header("accept","application/json").contentType("application/json").body(loginBody);

        Response response = request.when().post("/login");
        response.prettyPrint();
        response.then().statusCode(200);

        String token = response.jsonPath().getString("token");
        System.out.println(token);
    }

    @Test
    public void TC_01_Verify_GET_Method() {
        Response response = request.when().get("/users");
        response.then().statusCode(200);
        response.then().body("response[1].firstName",equalTo("John"));
    }

    @Test
    public void TC_02_Verify_POST_Method() {
//        Response response = request.when().post();
//        response.prettyPrint();
//        response.then().statusCode(200);
//        response.then().body("message",equalTo("Success"));

    }

    @Test
    public void TC_03_Verify_PUT_Method() {

    }

    @Test
    public void TC_04_Verify_DELETE_Method() {

    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("TBD After Method");
    }
}
