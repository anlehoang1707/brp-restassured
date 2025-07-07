package com.brp.image;

import commons.ApiEndPoint;
import commons.ApiKeyword;
import commons.BaseTest;
import com.brp.helpers.TestDataHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GetImageTest extends BaseTest {

    @BeforeMethod
    public void beforeMethod() throws JsonProcessingException {
        login();
    }


    @Test
    public void TC_01_Image_Get_All_Image() {
        Response response = ApiKeyword.get(ApiEndPoint.GET_ALL_IMAGES.getPathString());
        Assert.assertEquals(ApiKeyword.getStatusCode(response), 200);
        Assert.assertEquals(ApiKeyword.getResponseKeyValue(response, "message"), "Success");
    }

    @Test
    public void TC_02_Image_Get_By_Id() {
        int validImageId = TestDataHelper.getValidImageId();
        Response response = ApiKeyword.get(ApiEndPoint.GET_IMAGE_BY_ID.getPathString(String.valueOf(validImageId)));
        Assert.assertEquals(ApiKeyword.getStatusCode(response), 200);
        Assert.assertEquals(ApiKeyword.getResponseKeyValueInt(response, "response.id"), validImageId);

    }

    @Test
    public void TC_03_Image_Get_Invalid_Id_Returns_400() {
        int invalidImageId = 500000;
        Response response = ApiKeyword.get(ApiEndPoint.GET_IMAGE_BY_ID.getPathString(String.valueOf(invalidImageId)));
        Assert.assertEquals(ApiKeyword.getStatusCode(response), 400);
        Assert.assertEquals(ApiKeyword.getResponseKeyValue(response, "message"), "Not found");
        Assert.assertEquals(ApiKeyword.getResponseKeyValue(response, "errors"), "No image found with the submitted id");

    }

    @AfterMethod
    public void afterMethod() {

    }

}
