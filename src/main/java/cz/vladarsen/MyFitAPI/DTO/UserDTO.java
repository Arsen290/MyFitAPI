package cz.vladarsen.MyFitAPI.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
}
