package brp.books;

import brp.commons.ApiEndPoint;
import brp.commons.ApiKeyword;
import brp.commons.BaseTest;
import brp.helpers.TestDataHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BookPageTest extends BaseTest {
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

    @AfterClass
    public void afterClass() {
        // Implement any cleanup logic if necessary
    }
}
