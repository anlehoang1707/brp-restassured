package brp;

import brp.model.user.NewUserPOJO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import brp.commons.BaseTest;
import brp.model.LoginPOJO;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Level_04_Refactor_Level_03_With_POJO_For_POST extends BaseTest {
    RequestSpecification request;
    String token;
    final String baseUri = "https://api.anhtester.com/api";
    NewUserPOJO newUser = new NewUserPOJO();

    @BeforeMethod
    public void beforeMethod() throws JsonProcessingException {
        LoginPOJO login = new LoginPOJO();
        login.setUsername("anhtester");
        login.setPassword("Demo@123");
        ObjectMapper objectMapper = new ObjectMapper();
        String loginBody = objectMapper.writeValueAsString(login);

        request = given();
        request.baseUri(baseUri).header("accept","application/json").contentType("application/json").body(loginBody);

        Response response = request.when().post("/login");
        response.prettyPrint();
        response.then().statusCode(200);
        token = response.jsonPath().getString("token");

        newUser.setUsername("anle" + generateRandomNumber());
        newUser.setEmail("anle" + generateRandomNumber() + "@gmail.com");
        newUser.setPassword("Demo@123");
        newUser.setFirstName("An");
        newUser.setLastName("Le");
        newUser.setPhone("0912345678");
        newUser.setUserStatus(1);
    }

    @Test
    public void TC_01_Verify_GET_Method() {
        Response response = request.when().get("/users");
        response.then().statusCode(200);
        response.then().body("response[1].firstName",equalTo("John"));
    }

    @Test
    public void TC_02_Verify_POST_Method() throws JsonProcessingException {


        ObjectMapper objectMapper = new ObjectMapper();
        String newUserBody = objectMapper.writeValueAsString(newUser);
        RequestSpecification request = given();
        request.baseUri(baseUri).contentType("application/json");
        request.header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization","Bearer "+ token);
        request.body(newUserBody);
        Response response = request.when().post("/user");
        response.prettyPrint();
    }

    @Test
    public void TC_03_Verify_PUT_Method() {

    }

    @Test
    public void TC_04_Verify_DELETE_Method() {

    }

    @AfterMethod
    public void afterMethod() {

    }
}
