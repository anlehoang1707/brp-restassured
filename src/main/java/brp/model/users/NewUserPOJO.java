package brp.model.users;

import lombok.Data;

@Data
public class NewUserPOJO {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;
}
