package brp.books;

import brp.commons.ApiEndPoint;
import brp.commons.ApiKeyword;
import brp.commons.BaseTest;
import brp.model.books.NewBookPOJO;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;

public class CreateBookTest extends BaseTest {
    NewBookPOJO newBookPOJO;
    String bookName = "anletest" + Integer.toString(generateRandomNumber());

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

    }
    
    @Test
    public void TC_01_Create_Valid_New_Book() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        String newBookBody = objectMapper.writeValueAsString(newBookPOJO);
        String newBookBody = convertPojoToJsonString(newBookPOJO);
        Response response = ApiKeyword.post(ApiEndPoint.POST_BOOK.getPathString(), newBookBody);

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("message"), "Success");
    }

    @Test
    public void TC_02_Book_Create_With_Missing_Book_Name() {
        newBookPOJO.setName("");
        String newBookBodyWithMissingTitle = convertPojoToJsonString(newBookPOJO);
        Response response = ApiKeyword.post(ApiEndPoint.POST_BOOK.getPathString(), newBookBodyWithMissingTitle);

        Assert.assertEquals(response.statusCode(), 422);
        Assert.assertTrue(response.jsonPath().getString("message").contains("field is required"));

    }

    @Test
    public void TC_03_Book_Create_With_Duplicate_Returns_422() {
        String newBookBody = convertPojoToJsonString(newBookPOJO);
        Response response = ApiKeyword.post(ApiEndPoint.POST_BOOK.getPathString(), newBookBody);

        newBookPOJO.setName(bookName);
        String newBookBodyWithDuplicateName = convertPojoToJsonString(newBookPOJO);
        Response errorResponse = ApiKeyword.post(ApiEndPoint.POST_BOOK.getPathString(), newBookBodyWithDuplicateName);

        Assert.assertEquals(errorResponse.statusCode(), 422);
        Assert.assertEquals(response.jsonPath().getString("message"),"The name has already been taken.");

    }

    @Test
    public void TC_04_Book_Create_With_Invalid_Format() {
        newBookPOJO.setCategoryId(0);
        String newBookBodyWithInvalidCategoryId = convertPojoToJsonString(newBookPOJO);
        Response errorResponse = ApiKeyword.post(ApiEndPoint.POST_BOOK.getPathString(), newBookBodyWithInvalidCategoryId);

        Assert.assertEquals(errorResponse.statusCode(), 422);
        Assert.assertTrue(errorResponse.jsonPath().getString("errors").contains("The selected category id is invalid."));

    }

    @AfterMethod
    public void afterClass() {

    }
}
