package com.ormexample.demo.service;

import com.ormexample.demo.dto.UserDTO;
import com.ormexample.demo.model.Role;
import com.ormexample.demo.model.User;
import com.ormexample.demo.dto.UserCreateDTO;
import com.ormexample.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.ormexample.demo.exception.UserExceptions;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO saveUser(UserCreateDTO userCreateDTO) {
        if (userRepository.existsByEmail(userCreateDTO.getEmail())) {
            throw new UserExceptions.EmailAlreadyExistsException(userCreateDTO.getEmail());
        }

        User user = new User();
        user.setName(userCreateDTO.getName());
        user.setEmail(userCreateDTO.getEmail());
        user.setPassword(userCreateDTO.getPassword());
        user.setRole(Role.valueOf(userCreateDTO.getRole()));

        User savedUser = userRepository.save(user);

        return new UserDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRole()
        );
    }

    public UserDTO getUserById(Long id) {
        User found = userRepository.findById(id)
                .orElseThrow(() -> new UserExceptions.UserNotFoundException(id));
        return new UserDTO(
                found.getId(),
                found.getName(),
                found.getEmail(),
                found.getRole()
        );
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> usersDTO = userRepository.findAll().stream()
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getRole()
                ))
                .collect(Collectors.toList());
        return usersDTO;
    }

    public UserDTO updateUser(Long id, UserCreateDTO userUpdateDTO) {
        if (!userRepository.existsById(id)) {
            throw new UserExceptions.UserNotFoundException(id);
        }

        User user = new User();
        user.setId(id);
        user.setName(userUpdateDTO.getName());
        user.setEmail(userUpdateDTO.getEmail());
        user.setPassword(userUpdateDTO.getPassword());
        user.setRole(Role.valueOf(userUpdateDTO.getRole()));

        User updated = userRepository.save(user);
        return new UserDTO(
                updated.getId(),
                updated.getName(),
                updated.getEmail(),
                updated.getRole()
        );
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserExceptions.UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }
}
