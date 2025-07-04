package com.users;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Level_02_RestAssured_In_TestClass_With_Authentication {
    RequestSpecification request;

    @BeforeClass
    public void beforeMethod() {
        request = given();
        request.baseUri("https://api.anhtester.com/api").header("accept","application/json").contentType("application/json");

        request.body("""
                {
                  "username": "anhtester",
                  "password": "Demo@123"
                }""");

        Response response = request.when().post("/login");
        response.prettyPrint();
        response.then().statusCode(200);
        response.then().body(containsString("token"));
        String token = response.getBody().asString().split(":")[1].replace("/","").replace("}","").trim();
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
