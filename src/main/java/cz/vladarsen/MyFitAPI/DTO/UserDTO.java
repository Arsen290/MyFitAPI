package cz.vladarsen.MyFitAPI.DTO;

import cz.vladarsen.MyFitAPI.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private List<Role> role;
    private Date updated;
    private Date created;

}
