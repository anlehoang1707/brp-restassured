package com.brp.book;

import commons.ApiEndPoint;
import commons.ApiKeyword;
import commons.BaseTest;
import model.book.NewBookPOJO;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteBookTest extends BaseTest {
    NewBookPOJO newBookPOJO;
    String bookName = "anletest" + Integer.toString(generateRandomNumber());
    int bookId;

    @BeforeMethod
    public void beforeMethod() throws JsonProcessingException {
        login();

        newBookPOJO = new NewBookPOJO();
        newBookPOJO.setName(bookName);
        newBookPOJO.setCategoryId(119);
        newBookPOJO.setPrice(1200);
        newBookPOJO.setReleaseDate("2023/12/29");
        newBookPOJO.setImageIds(new int[] {11,12});
        newBookPOJO.setStatus(true);

        String newBookBody = convertPojoToJsonString(newBookPOJO);
        Response response = ApiKeyword.post(ApiEndPoint.POST_BOOK.getPathString(),newBookBody);

        String bookId = ApiKeyword.getResponseKeyValue(response,"response.id");
        this.bookId = Integer.parseInt(bookId);

    }

    @Test
    public void TC_01_Book_Delete_Valid_Id() {
        Response response = ApiKeyword.delete(ApiEndPoint.DELETE_BOOK_BY_ID.getPathString(bookId));

        Assert.assertEquals(ApiKeyword.getStatusCode(response),200);
        Assert.assertEquals(ApiKeyword.getResponseKeyValue(response,"message"),"Success");
        Assert.assertEquals(ApiKeyword.getResponseKeyValue(response,"response.id"),String.valueOf(bookId));
    }

    @Test
    public void TC_02_Book_Delete_Nonexistent_Returns_404() {
        Response response = ApiKeyword.delete(ApiEndPoint.DELETE_BOOK_BY_ID.getPathString(500000));

        Assert.assertEquals(ApiKeyword.getStatusCode(response),400);
        Assert.assertEquals(ApiKeyword.getResponseKeyValue(response,"message"),"Not found");
        Assert.assertEquals(ApiKeyword.getResponseKeyValue(response,"errors"),"No book found with the submitted id");
    }


    @AfterClass
    public void afterClass() {
        Response response = ApiKeyword.delete(ApiEndPoint.DELETE_BOOK_BY_ID.getPathString(bookId));    }

}
