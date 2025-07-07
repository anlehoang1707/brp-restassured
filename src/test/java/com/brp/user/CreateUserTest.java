package com.brp.user;

import org.testng.annotations.Test;

public class CreateUserTest {

    @Test
    public void TC_01_User_Create_New_User() {}

    @Test
    public void TC_02_User_Create_User_With_Empty_Username() {}

    @Test
    public void TC_03_User_Create_User_With_Invalid_Email() {}

    @Test
    public void TC_04_User_Create_User_With_Existing_Email_Returns_409() {}

    @Test
    public void TC_05_User_Create_User_With_Missing_Fields() {}

}
