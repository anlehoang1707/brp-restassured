package brp.books;

import brp.commons.BaseTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BookPageTest extends BaseTest {
    @BeforeClass
    public void beforeClass() throws JsonProcessingException {
        login();

    }

    @Test
    public void TC_01_Book_Get_All_With_Pagination() {
    }

    @Test
    public void TC_02_Book_Get_By_Id() {}

    @Test
    public void TC_03_Book_Search_By_Title() {}

    @Test
    public void TC_04_Book_Filter_By_Category() {}

    @Test
    public void TC_05_Book_Sort_By_Release_Date() {}

    @AfterClass
    public void afterClass() {
        // Implement any cleanup logic if necessary
    }
}
