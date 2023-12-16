package cz.vladarsen.MyFitAPI.service.impl;

import cz.vladarsen.MyFitAPI.DTO.UserDTO;
import cz.vladarsen.MyFitAPI.entity.Role;
import cz.vladarsen.MyFitAPI.entity.RoleName;
import cz.vladarsen.MyFitAPI.entity.User;
import cz.vladarsen.MyFitAPI.repository.RoleRepository;
import cz.vladarsen.MyFitAPI.repository.UserRepository;
import cz.vladarsen.MyFitAPI.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    private List<Role> getRoleUser() {
        Role userRole = roleRepository.findByRoleName(RoleName.USER)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        return Collections.singletonList(userRole);
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        // Check,validation and others

        List<Role> roleUser = getRoleUser();

        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        newUser.setEmail(userDTO.getEmail());
        newUser.setFirstName(userDTO.getFirstName());
        newUser.setLastName(userDTO.getLastName());
        newUser.setRoles(roleUser);
        newUser = userRepository.save(newUser);
        log.info("IN register - user: {} with role : {} successfully registered", newUser, roleUser);

        return userDTO;
    }
    //Change logic
    @Override
    public UserDTO updateUser(Long userId, UserDTO userDTO){
        List<Role> roleUser = getRoleUser();

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPassword(userDTO.getPassword());//existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        existingUser.setFirstName(userDTO.getFirstName());
        existingUser.setLastName(userDTO.getLastName());
        existingUser.setRoles(roleUser);
        existingUser = userRepository.save(existingUser);
        return new UserDTO(existingUser.getUsername(), existingUser.getPassword(), existingUser.getEmail(), existingUser.getFirstName(), existingUser.getLastName());
    };
    @Override
    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    };

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }
    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }



}
