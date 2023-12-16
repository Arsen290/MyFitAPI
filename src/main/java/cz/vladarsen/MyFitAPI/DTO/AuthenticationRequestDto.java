package cz.vladarsen.MyFitAPI.DTO;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String username;
    private String password;
}
