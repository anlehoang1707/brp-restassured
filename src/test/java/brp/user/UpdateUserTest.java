package brp.user;

import org.testng.annotations.Test;

public class UpdateUserTest {

    @Test
    public void TC_01_User_Update_User_FullInfo_With_PUT() {}

    @Test
    public void TC_02_User_Partial_Update_With_PATCH() {}

    @Test
    public void TC_03_User_Update_Nonexistent_User_Returns_404() {}

    @Test
    public void TC_04_User_Update_With_Invalid_Email_Format() {}

    @Test
    public void TC_05_User_Update_Without_Auth_Returns_401() {}

}
