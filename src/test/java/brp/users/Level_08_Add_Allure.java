package brp.users;

import brp.commons.ApiKeyword;
import brp.commons.BaseTest;
import brp.model.users.NewUserPOJO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Level_08_Add_Allure extends BaseTest {
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

//    @Test(description = "Verify GET Method")
    public void TC_01_Verify_GET_Method() {
        Response response = ApiKeyword.getNoAuth("/users");
        Assert.assertEquals(ApiKeyword.getStatusCode(response), 200);
    }

    @Test(description = "Verify POST Method")
    public void TC_02_Verify_POST_Method() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String newUserBody = objectMapper.writeValueAsString(newUser);

        Response response = ApiKeyword.post("/user", newUserBody);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(description = "Verify PUT Method")
    public void TC_03_Verify_PUT_Method() {
//        Map<String, String> headers = new HashMap<>();
//        headers.put("Accepted","application/json");
//        Response response = ApiKeyword.get("/users",headers);

    }

    @Test(description = "Verify DELETE Method")
    public void TC_04_Verify_DELETE_Method() {
        Response response = ApiKeyword.deleteWithQuery("/user", "username", newUser.getUsername());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("message"), "Success");

//        response.then().body("message", equalTo("Success"));
    }

    @AfterClass
    public void afterMethod() {

    }
}
