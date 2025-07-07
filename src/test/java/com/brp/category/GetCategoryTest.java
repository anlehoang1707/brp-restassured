package com.brp.category;

import commons.ApiEndPoint;
import commons.ApiKeyword;
import commons.BaseTest;
import com.brp.helpers.TestDataHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GetCategoryTest extends BaseTest {
    @BeforeClass
    public void beforeClass() throws JsonProcessingException {
        login();

    }

    @Test
    public void TC_01_Category_Get_All() {
        Response response = ApiKeyword.get(ApiEndPoint.GET_ALL_CATEGORYS.getPathString());
        Assert.assertEquals(ApiKeyword.getStatusCode(response),200);
        Assert.assertEquals(ApiKeyword.getResponseKeyValue(response,"message"),"Success");

    }

    @Test
    public void TC_02_Category_Get_By_Id() {
        int validCategoryId = TestDataHelper.getValidCategoryId();
        Response response = ApiKeyword.get(ApiEndPoint.GET_CATEGORY_BY_ID.getPathString(String.valueOf(validCategoryId)));
        Assert.assertEquals(ApiKeyword.getStatusCode(response),200);
        Assert.assertEquals(ApiKeyword.getResponseKeyValueInt(response,"response.id"),validCategoryId);

    }

    @Test
    public void TC_03_Category_Get_By_Invalid_Id_Returns_400() {
        int invalidCategoryId = 500000;
        Response response = ApiKeyword.get(ApiEndPoint.GET_CATEGORY_BY_ID.getPathString(String.valueOf(invalidCategoryId)));
        Assert.assertEquals(ApiKeyword.getStatusCode(response),400);
        Assert.assertEquals(ApiKeyword.getResponseKeyValue(response,"message"),"Not found");
        Assert.assertEquals(ApiKeyword.getResponseKeyValue(response,"errors"),"No categogy found with the submitted id");

    }

    @AfterMethod
    public void afterClass() {

    }
}
