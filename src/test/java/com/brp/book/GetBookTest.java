package com.brp.book;

import commons.ApiEndPoint;
import commons.ApiKeyword;
import commons.BaseTest;
import com.brp.helpers.TestDataHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GetBookTest extends BaseTest {
    @BeforeClass
    public void beforeClass() throws JsonProcessingException {
        login();

    }

    @Test
    public void TC_01_Book_Get_All_WithOut_Pagination() {
        Response response = ApiKeyword.get(ApiEndPoint.GET_ALL_BOOKS.getPathString());
        Assert.assertEquals(ApiKeyword.getStatusCode(response),200);
        Assert.assertEquals(ApiKeyword.getResponseKeyValue(response,"message"),"Success");
    }

    @Test
    public void TC_02_Book_Get_By_Id() {
        int validBookId = TestDataHelper.getValidBookId();
        Response response = ApiKeyword.get(ApiEndPoint.GET_BOOK_BY_ID.getPathString(String.valueOf(validBookId)));
        Assert.assertEquals(ApiKeyword.getStatusCode(response),200);
        Assert.assertEquals(ApiKeyword.getResponseKeyValueInt(response,"response.id"),validBookId);

    }

    @Test
    public void TC_03_Book_Get_By_Invalid_Id_Returns_400() {
        int invalidBookId = 500000;
        Response response = ApiKeyword.get(ApiEndPoint.GET_BOOK_BY_ID.getPathString(String.valueOf(invalidBookId)));
        Assert.assertEquals(ApiKeyword.getStatusCode(response),400);
        Assert.assertEquals(ApiKeyword.getResponseKeyValue(response,"message"),"Not found");
        Assert.assertEquals(ApiKeyword.getResponseKeyValue(response,"errors"),"No book found with the submitted id");

    }

    @AfterClass
    public void afterClass() {
        // Implement any cleanup logic if necessary
    }
}
