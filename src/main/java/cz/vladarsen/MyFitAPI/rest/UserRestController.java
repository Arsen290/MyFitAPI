package cz.vladarsen.MyFitAPI.rest;

import cz.vladarsen.MyFitAPI.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    // Endpoint to get the details of the currently authenticated user
    @GetMapping("/profile")
    public ResponseEntity<UserDto> getUserProfile() {
        UserDto userDto = userService.getCurrentUserProfile();
        return ResponseEntity.ok(userDto);
    }

    // Endpoint to update user details
    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUserProfile(@RequestBody UpdateUserRequest updateUserRequest) {
        UserDto updatedUser = userService.updateUserProfile(updateUserRequest);
        return ResponseEntity.ok(updatedUser);
    }

    // Endpoint to change the user's password
    @PostMapping("/change-password")
    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        userService.changeUserPassword(changePasswordRequest);
        return ResponseEntity.ok().build();
    }

    // Endpoint to delete the user's account
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUserAccount() {
        userService.deleteUserAccount();
        return ResponseEntity.ok().build();
    }

}