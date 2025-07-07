package com.brp.category;

import commons.ApiEndPoint;
import commons.ApiKeyword;
import commons.BaseTest;
import model.category.NewCategoryPOJO;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateCategoryTest extends BaseTest {
    NewCategoryPOJO newCategoryPOJO;
    String categoryName;
    int categoryId;

    @BeforeMethod
    public void beforeMethod() throws JsonProcessingException {
        login();

        newCategoryPOJO = new NewCategoryPOJO();
        categoryName = getRandomName();
        newCategoryPOJO.setName(categoryName);

    }

    @Test
    public void TC_01_Category_Create_Valid() {
        String newCategoryBody = convertPojoToJsonString(newCategoryPOJO);

        Response response = ApiKeyword.post(ApiEndPoint.POST_CATEGORY.getPathString(),newCategoryBody );

        Assert.assertEquals(ApiKeyword.getStatusCode(response), 200);
        Assert.assertEquals(ApiKeyword.getResponseKeyValue(response,"message"), "Success");
        this.categoryId = Integer.parseInt(ApiKeyword.getResponseKeyValue(response, "response.id"));
    }

    @Test
    public void TC_02_Category_Create_Empty_Name_Returns_422() {
        newCategoryPOJO.setName("");
        String newCategoryBody = convertPojoToJsonString(newCategoryPOJO);

        Response response = ApiKeyword.post(ApiEndPoint.POST_CATEGORY.getPathString(),newCategoryBody );

        Assert.assertEquals(ApiKeyword.getStatusCode(response), 422);
        Assert.assertEquals(ApiKeyword.getResponseKeyValue(response,"message"), "The name field is required.");
        Assert.assertEquals(ApiKeyword.getResponseKeyValue(response,"errors.name[0]"), "The name field is required.");

    }

    @Test
    public void TC_03_Category_Create_Duplicate_Name_Returns_422() {
        String newCategoryBody = convertPojoToJsonString(newCategoryPOJO);
        Response response = ApiKeyword.post(ApiEndPoint.POST_CATEGORY.getPathString(), newCategoryBody);
        Response errorResponse = ApiKeyword.post(ApiEndPoint.POST_CATEGORY.getPathString(),newCategoryBody );
        Assert.assertEquals(ApiKeyword.getStatusCode(errorResponse), 422);
        Assert.assertEquals(ApiKeyword.getResponseKeyValue(errorResponse,"message"), "The name has already been taken.");
        Assert.assertEquals(ApiKeyword.getResponseKeyValue(errorResponse,"errors.name[0]"), "The name has already been taken.");

    }

    @AfterMethod
    public void afterMethod() {

    }
}

