package services;

import entities.User;

import java.util.List;

public interface UserManagementService {
    String registerUser(User user);

    List<User> getUsers();

    User getUserByEmail(String email);
}
