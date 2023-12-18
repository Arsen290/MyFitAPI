package cz.vladarsen.MyFitAPI.service;

import cz.vladarsen.MyFitAPI.DTO.UserDTO;
import cz.vladarsen.MyFitAPI.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO registerUser(UserDTO userDTO);
    UserDTO updateUser(Long userId, UserDTO userDTO);
    void deleteUser(Long userId);
    List<User> getAll();
    Optional<User> findByUsername(String username);
}
