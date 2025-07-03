import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Level_01_Use_RestAssured_In_TestClass {
    RequestSpecification request;


    @BeforeMethod
    public void beforeMethod() {
        request = given();
        request.baseUri("https://api.anhtester.com/api").basePath("/users").header("accept","application/json");

    }

    @Test
    public void TC_01_Verify_GET_Method() {
        Response response = request.when().get();
        response.prettyPeek();
        response.then().statusCode(200);
        response.then().body("response[1].firstName",equalTo("John"));
    }

    @Test
    public void TC_02_Verify_POST_Method() {
//        request.body("{\n" +
//                "  \"username\": \"theUser\",\n" +
//                "  \"firstName\": \"John\",\n" +
//                "  \"lastName\": \"James\",\n" +
//                "  \"email\": \"john@email.com\",\n" +
//                "  \"password\": \"Demo@123\",\n" +
//                "  \"phone\": \"0912345678\",\n" +
//                "  \"userStatus\": 1\n" +
//                "}").contentType("application/json");
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

    }
}
