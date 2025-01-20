package com.example.bupdashboard.service;

import com.example.bupdashboard.dao.UserDto;
import com.example.bupdashboard.dashboardDb.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();

    void deleteUser(Long id);

    User getUserById(Long id);

    void updateUser(Long id, User user);

    List<User> getUserByUserEmail(String query);
}
