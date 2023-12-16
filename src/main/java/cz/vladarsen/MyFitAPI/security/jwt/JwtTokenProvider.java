package cz.vladarsen.MyFitAPI.security.jwt;

import cz.vladarsen.MyFitAPI.entity.Role;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JwtTokenProvider {
    public String createToken(String username, List<Role> roles) {
        return null;
    }
    public Authentication getAuthentication(String token) {
        return null;
    }

    public String getUsername(String token) {
        return null;
    }

    public boolean validateToken(String token) {
        return false;
    }

    private List<String> getRoles(List<Role> userRoles) {
        List<String> result = new ArrayList<>();

        userRoles.forEach(role -> {
            result.add(role.getRoleName().name());
        });

        return result;
    }
}
