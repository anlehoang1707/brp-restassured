package com.brp.image;

import commons.ApiEndPoint;
import commons.ApiKeyword;
import commons.BaseTest;
import commons.GlobalConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

public class CreateImageTest extends BaseTest {
    File testImageFile;

    @BeforeMethod
    public void beforeMethod() throws JsonProcessingException {
        login();
        testImageFile = new File(GlobalConstants.IMAGE_PATH + "test_image.png");
    }

    @Test
    public void TC_01_Image_Upload_Valid_Image() {
        Response response = ApiKeyword.post(ApiEndPoint.POST_IMAGE.getPathString(),"image",testImageFile );

        Assert.assertEquals(ApiKeyword.getStatusCode(response), 200);
        Assert.assertEquals(ApiKeyword.getResponseKeyValue(response, "message"),"Success");

    }

    @Test
    public void TC_02_Image_Upload_Invalid_Format() {
        File invalidFile = new File(GlobalConstants.PROJECT_PATH + "src/test/resources/log4j2.xml");
        Response response = ApiKeyword.post(ApiEndPoint.POST_IMAGE.getPathString(),"image",invalidFile );
        Assert.assertEquals(ApiKeyword.getStatusCode(response), 422);
        Assert.assertEquals(ApiKeyword.getResponseKeyValue(response, "message"),"The image field must be a file of type: jpeg, bmp, png.");

    }

    @Test
    public void TC_03_Image_Upload_Too_Large_File_Returns_422() {
        File largeImageFile = new File(GlobalConstants.IMAGE_PATH + "large_image.jpg");
        Response response = ApiKeyword.post(ApiEndPoint.POST_IMAGE.getPathString(),"image",largeImageFile );
        Assert.assertEquals(ApiKeyword.getStatusCode(response), 422);
        Assert.assertEquals(ApiKeyword.getResponseKeyValue(response, "message"),"The image field must not be greater than 5120 kilobytes.");
    }

    @AfterMethod
    public void afterMethod() {

    }
}
