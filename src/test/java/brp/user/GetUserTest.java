package brp.user;

import org.testng.annotations.Test;

public class GetUserTest {

    @Test
    public void TC_01_User_Get_All_Users_With_Pagination() {}

    @Test
    public void TC_02_User_Get_User_By_Id() {}

    @Test
    public void TC_03_User_Search_User_By_Username() {}

    @Test
    public void TC_04_User_Get_Nonexistent_User_Returns_404() {}

    @Test
    public void TC_05_User_Get_With_Invalid_Id_Returns_400() {}

}
