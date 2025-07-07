package brp.book;

import brp.commons.ApiEndPoint;
import brp.commons.ApiKeyword;
import brp.commons.BaseTest;
import brp.model.book.NewBookPOJO;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;

public class CreateBookTest extends BaseTest {
    NewBookPOJO newBookPOJO;
    String bookName;
    int bookId;
    Response response;

    @BeforeMethod
    public void beforeMethod() throws JsonProcessingException {
        login();
        bookName = "An Le Test Book" + Integer.toString(generateRandomNumber());
        newBookPOJO = new NewBookPOJO();
        newBookPOJO.setName(bookName);
        newBookPOJO.setCategoryId(119);
        newBookPOJO.setPrice(1200);
        newBookPOJO.setReleaseDate("2023/12/29");
        newBookPOJO.setImageIds(new int[]{11, 12});
        newBookPOJO.setStatus(true);

    }

    @Test
    public void TC_01_Book_Create_Valid_New_Book() {

        String newBookBody = convertPojoToJsonString(newBookPOJO);
        Response response = ApiKeyword.post(ApiEndPoint.POST_BOOK.getPathString(), newBookBody);

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("message"), "Success");

        this.bookId = Integer.parseInt(ApiKeyword.getResponseKeyValue(response, "response.id"));

    }

    @Test
    public void TC_02_Book_Create_With_Missing_Book_Name() {
        newBookPOJO.setName("");
        String newBookBodyWithMissingTitle = convertPojoToJsonString(newBookPOJO);
        Response errorResponse = ApiKeyword.post(ApiEndPoint.POST_BOOK.getPathString(), newBookBodyWithMissingTitle);

        Assert.assertEquals(ApiKeyword.getStatusCode(errorResponse), 422);
        Assert.assertTrue(ApiKeyword.getResponseKeyValue(errorResponse, "message").contains("field is required"));

    }

    @Test
    public void TC_03_Book_Create_With_Duplicate_Returns_422() {
        String newBookBody = convertPojoToJsonString(newBookPOJO);
        Response response = ApiKeyword.post(ApiEndPoint.POST_BOOK.getPathString(), newBookBody);

        Response errorResponse = ApiKeyword.post(ApiEndPoint.POST_BOOK.getPathString(), newBookPOJO);

        Assert.assertEquals(ApiKeyword.getStatusCode(errorResponse), 422);
        Assert.assertEquals(ApiKeyword.getResponseKeyValue(errorResponse, "message"), "The name has already been taken.");

        this.bookId = Integer.parseInt(ApiKeyword.getResponseKeyValue(response, "response.id"));
    }

    @Test
    public void TC_04_Book_Create_With_Invalid_Format() {
        newBookPOJO.setCategoryId(0);
        String newBookBodyWithInvalidCategoryId = convertPojoToJsonString(newBookPOJO);
        Response errorResponse = ApiKeyword.post(ApiEndPoint.POST_BOOK.getPathString(), newBookBodyWithInvalidCategoryId);

        Assert.assertEquals(ApiKeyword.getStatusCode(errorResponse), 422);
        Assert.assertTrue(ApiKeyword.getResponseKeyValue(errorResponse, "errors.category_id").contains("The selected category id is invalid."));

    }

    @AfterMethod
    public void afterMethod() {
        if (bookId != 0) {
            ApiKeyword.delete(ApiEndPoint.DELETE_BOOK_BY_ID.getPathString(bookId));
        }
    }
}
