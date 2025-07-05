package brp.users;

import brp.commons.GlobalConstants;
import brp.model.users.NewUserPOJO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import brp.commons.BaseTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Level_05_BaseTest_Reusable_Login_And_RequestSpecification_Setup extends BaseTest {
    RequestSpecification request;
    String token;
    final String baseUri = "https://api.anhtester.com/api";
    NewUserPOJO newUser = new NewUserPOJO();

    @BeforeClass
    public void beforeMethod() throws JsonProcessingException {
        login();

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
        Response response = getPreSetupRequest(GlobalConstants.BASE_URI).when().get("/users");
        response.then().statusCode(200);
        response.then().body("response[1].firstName",equalTo("John"));
    }

    @Test
    public void TC_02_Verify_POST_Method() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String newUserBody = objectMapper.writeValueAsString(newUser);

        Response response = getPreSetupRequest(GlobalConstants.BASE_URI,GlobalConstants.TOKEN).body(newUserBody).when().post("/user");

        response.prettyPrint();
    }

    @Test
    public void TC_03_Verify_PUT_Method() {

    }

    @Test
    public void TC_04_Verify_DELETE_Method() {
        Response response = getPreSetupRequest(GlobalConstants.BASE_URI,GlobalConstants.TOKEN).queryParam("username",newUser.getUsername()).when().delete("/user");
        response.prettyPrint();
        response.then().statusCode(200);
        response.then().body("message",equalTo("Success"));


    }

    @AfterMethod
    public void afterMethod() {

    }
}
