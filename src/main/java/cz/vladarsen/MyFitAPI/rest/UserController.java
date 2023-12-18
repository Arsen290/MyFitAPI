package cz.vladarsen.MyFitAPI.rest;

import cz.vladarsen.MyFitAPI.request.RegisterRequest;
import cz.vladarsen.MyFitAPI.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

//@RestController
//@RequestMapping("/api/v1/users")
//@Slf4j
//@RequiredArgsConstructor
//public class UserController {
//    private final UserService userService;
//
////    @PostMapping("/register")
////    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
////        UserDTO registeredUser = userService.registerUser(userDTO);
////        return ResponseEntity.ok(registeredUser);
////    }
//
//    @PutMapping("/update/{userId}")
//    public ResponseEntity<RegisterRequest> updateUser(@PathVariable Long userId, @RequestBody RegisterRequest userDTO) {
//        RegisterRequest updatedUser = userService.updateUser(userId, userDTO);
//        return ResponseEntity.ok(updatedUser);
//    }
//
//    @DeleteMapping("/delete/{userId}")
//    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
//        userService.deleteUser(userId);
//        return ResponseEntity.ok("User deleted successfully");
//    }
//
//    @GetMapping("/all")
//    public ResponseEntity<List<User>> getAllUsers() {
//        List<User> users = userService.getAll();
//        return ResponseEntity.ok(users);
//    }
//
//    @GetMapping("/{username}")
//    public ResponseEntity getUserByUsername(@PathVariable String username) {
//        Optional<User> user = userService.findByUsername(username);
//        return ResponseEntity.ok(user);
//    }
//}