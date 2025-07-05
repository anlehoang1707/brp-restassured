package brp.commons;

import brp.model.LoginPOJO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class BaseTest {
    @BeforeSuite
    public void deleteFileInReport() {
        // Remove all file in Allure attachment (json file)
        deleteAllFileInFolder("allure-results");
    }

    public void deleteAllFileInFolder(String folderName) {
        try {
            String pathFolderDownload = GlobalConstants.PROJECT_PATH + File.separator + folderName;
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            if (listOfFiles.length != 0) {
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equals("environment.properties")) {
                        new File(listOfFiles[i].toString()).delete();
                    }
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }


    protected int generateRandomNumber() {
        return new Random().nextInt(9999);
    }

    protected String convertPojoToJsonString(Object object)  {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
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
