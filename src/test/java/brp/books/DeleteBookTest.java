package brp.books;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DeleteBookTest {
    @BeforeClass
    public void beforeClass() {

    }

    @Test
    public void TC_01_Book_Delete_Valid_Id() {}

    @Test
    public void TC_02_Book_Delete_Nonexistent_Returns_404() {}

    @AfterClass
    public void afterClass() {
        // Implement any cleanup logic if necessary
    }
}
