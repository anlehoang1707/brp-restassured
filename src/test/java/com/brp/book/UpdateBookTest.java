package com.brp.book;

import commons.ApiEndPoint;
import commons.ApiKeyword;
import commons.BaseTest;
import com.brp.helpers.TestDataHelper;
import model.book.UpdateBookPOJO;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UpdateBookTest extends BaseTest {
    UpdateBookPOJO updateBookPOJO;
    String bookName;
    int updateBookId;

    @BeforeMethod
    public void beforeMethod() throws JsonProcessingException {
        login();

        bookName = getRandomName();

        updateBookPOJO = new UpdateBookPOJO();
        updateBookPOJO.setName(bookName);
        updateBookPOJO.setCategoryId(TestDataHelper.getValidCategoryId());
        updateBookPOJO.setPrice(1200);
        updateBookPOJO.setReleaseDate("2023/12/29");
        updateBookPOJO.setImageIds(TestDataHelper.getValidImageList(0,2));
        updateBookPOJO.setStatus(true);

        updateBookId = TestDataHelper.getValidBookId();
    }

    @Test
    public void TC_01_Book_Update_Full_Details_With_PUT() {
        Response response = ApiKeyword.put(ApiEndPoint.PUT_BOOK_BY_ID.getPathString(updateBookId),updateBookPOJO);

        Assert.assertEquals(ApiKeyword.getStatusCode(response),200);
        Assert.assertEquals(ApiKeyword.getResponseKeyValue(response,"message"),"Success");
        Assert.assertEquals(ApiKeyword.getResponseKeyValueInt(response,"response.id"),updateBookId);

    }

    @Test
    public void TC_02_Book_Partial_Update_With_PATCH() {

    }

    @Test
    public void TC_03_Book_Update_Nonexistent_Book_Returns_400() {
        updateBookId = 50000000;
        Response response = ApiKeyword.put(ApiEndPoint.PUT_BOOK_BY_ID.getPathString(updateBookId),updateBookPOJO);

        Assert.assertEquals(ApiKeyword.getStatusCode(response),400);
        Assert.assertEquals(ApiKeyword.getResponseKeyValue(response,"message"),"Not found");
        Assert.assertEquals(ApiKeyword.getResponseKeyValue(response,"errors"),"No book found with the submitted id");

    }


    @AfterClass
    public void afterClass() {
        // Implement any cleanup logic if necessary
    }
}
