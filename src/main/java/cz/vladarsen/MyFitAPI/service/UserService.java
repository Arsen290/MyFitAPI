package cz.vladarsen.MyFitAPI.service;

import cz.vladarsen.MyFitAPI.DTO.UserDTO;
import cz.vladarsen.MyFitAPI.entity.User;

import java.util.List;

public interface UserService {
    UserDTO registerUser(UserDTO userDTO);
    UserDTO updateUser(Long userId, UserDTO userDTO);
    void deleteUser(Long userId);
    String loginUser(UserDTO userDTO);
    List<User> getAll();
    User findByUsername(String username);
}
