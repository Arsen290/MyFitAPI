package cz.vladarsen.MyFitAPI.DTO;

import lombok.Data;

@Data
public class AuthenticationRequestDTO {
    private String username;
    private String password;
}
