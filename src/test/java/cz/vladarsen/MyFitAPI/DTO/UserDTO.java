package cz.vladarsen.MyFitAPI.DTO;

import cz.vladarsen.MyFitAPI.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

}
