package cz.vladarsen.MyFitAPI.DTO;

import cz.vladarsen.MyFitAPI.entity.Role;
import cz.vladarsen.MyFitAPI.entity.RoleName;
import cz.vladarsen.MyFitAPI.entity.User;
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

    public static UserDTO from(User user) {

        return UserDTO.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .updated(user.getUpdated())
                .created(user.getCreated())
                .build();
    }
}
