package brp.user;

import org.testng.annotations.Test;

public class DeleteUserTest {

    @Test
    public void TC_01_User_Delete_Valid_User() {}

    @Test
    public void TC_02_User_Delete_Nonexistent_User_Returns_404() {}

    @Test
    public void TC_03_User_Delete_Without_Auth_Returns_401() {}

}
