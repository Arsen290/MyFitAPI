package cz.vladarsen.MyFitAPI.rest;

import cz.vladarsen.MyFitAPI.DTO.UserDTO;
import cz.vladarsen.MyFitAPI.config.JwtService;
import cz.vladarsen.MyFitAPI.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserRestController {

    private final UserService userService;

    // Endpoint to get the details of the currently authenticated user
    @GetMapping("/profile")
    @ResponseBody
    public ResponseEntity<UserDTO> getUserProfile(Authentication authentication) {
        String username = authentication.getName();
        log.info("User {} is getting his profile", username);
        UserDTO userDto = userService.getCurrentUserProfile(username);
        log.info("UserDTO contains: {}", userDto);
        return ResponseEntity.ok(userDto);
    }

    // Endpoint to update user details
//    @PutMapping("/update")
//    public ResponseEntity<UserDto> updateUserProfile(@RequestBody UpdateUserRequest updateUserRequest) {
//        UserDto updatedUser = userService.updateUserProfile(updateUserRequest);
//        return ResponseEntity.ok(updatedUser);
//    }
//
//    // Endpoint to change the user's password
//    @PostMapping("/change-password")
//    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
//        userService.changeUserPassword(changePasswordRequest);
//        return ResponseEntity.ok().build();
//    }
//
//    // Endpoint to delete the user's account
//    @DeleteMapping("/delete")
//    public ResponseEntity<Void> deleteUserAccount() {
//        userService.deleteUserAccount();
//        return ResponseEntity.ok().build();
//    }

}