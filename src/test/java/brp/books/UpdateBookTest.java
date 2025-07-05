package brp.books;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UpdateBookTest {
    @BeforeClass
    public void beforeClass() {

    }

    @Test
    public void TC_01_Book_Update_Full_Details_With_PUT() {}

    @Test
    public void TC_02_Book_Partial_Update_With_PATCH() {}

    @Test
    public void TC_03_Book_Update_Nonexistent_Book_Returns_404() {}


    @AfterClass
    public void afterClass() {
        // Implement any cleanup logic if necessary
    }
}
