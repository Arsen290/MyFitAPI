package cz.vladarsen.MyFitAPI.service;

import cz.vladarsen.MyFitAPI.DTO.UserDTO;
import cz.vladarsen.MyFitAPI.entity.User;
import cz.vladarsen.MyFitAPI.repository.UserRepository;
import cz.vladarsen.MyFitAPI.request.ChangePasswordRequest;
import cz.vladarsen.MyFitAPI.request.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //Get current user profile
    public UserDTO getCurrentUserProfile(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return UserDTO.from(user);
    }
    //Delete user account
    public void deleteUserAccount(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        userRepository.delete(user);
        log.info("IN delete - user with username: {} successfully deleted",username);
    }
    //Update user profile
    public UserDTO updateUserProfile(String username, UpdateUserRequest updateUserRequest) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        // Update only if the field in the request is not null
        if (updateUserRequest.getFirstName() != null) {
            user.setFirstName(updateUserRequest.getFirstName());
        }
        if (updateUserRequest.getLastName() != null) {
            user.setLastName(updateUserRequest.getLastName());
        }
        if (updateUserRequest.getEmail() != null) {
            user.setEmail(updateUserRequest.getEmail());
        }
        // Update date when updated entity in db
        LocalDateTime localDateTime = LocalDateTime.now();
        Date currentDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        user.setUpdated(currentDate);

        userRepository.save(user);
        log.info("IN update - user with username: {} successfully updated at {}",username, currentDate);
        return UserDTO.from(user);
    }
    //Change user password
    public void changeUserPassword(String username, ChangePasswordRequest changePasswordRequest) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));


        // Check if the old password matches the current password
        if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())) {
            log.warn("Old password provided does not match the current password.");
            throw new BadCredentialsException("Old password is incorrect");        }


        // Update the password
        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));

        // Update date
        LocalDateTime localDateTime = LocalDateTime.now();
        Date currentDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        user.setUpdated(currentDate);

        userRepository.save(user);


        log.info("IN changeUserPassword - user with username: {} successfully changed password at {}" ,username,currentDate);
    }
}
