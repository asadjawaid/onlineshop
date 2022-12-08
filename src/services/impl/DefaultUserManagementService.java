package services.impl;

import entities.User;
import services.UserManagementService;

import java.util.List;

public class DefaultUserManagementService implements UserManagementService {
    private static final String NOT_UNIQUE_EMAIL_ERROR_MESSAGE = "This email is already used by another user. Please, use another email";
    private static final String EMPTY_EMAIL_ERROR_MESSAGE = "You have to input email to register. Please, try one more time";
    private static final String NO_ERROR_MESSAGE = "";

    private static DefaultUserManagementService instance = null;

    private List<User> users;

    /**
     *
     * @return a single instance of DefaultUserManagementService
     */
    public static UserManagementService getInstance() {
        if (instance == null) instance = new DefaultUserManagementService();

        return instance;
    }

    /**
     * This method takes a user objects and adds it to the list of all users.
     * @param user that needs to be created
     * @return a message indicating whether or not the user was successfully registered
     */
    @Override
    public String registerUser(User user) {
        if (user == null) {
            return NO_ERROR_MESSAGE;
        }

        // We except an empty message, which will indicate that the email is valid.
        String emailValidationMessage = checkUniqueEmail(user.getEmail());

        // Email is not unique or maybe empty
        if (emailValidationMessage != null && !emailValidationMessage.isEmpty()) {
            return emailValidationMessage;
        }

        // add the user to the list of users
        this.users.add(user);
        System.out.println("New user created");

        return NO_ERROR_MESSAGE;
    }

    /**
     * This is a helper method which is used to validate and check if an email is unique.
     * @param email is the email that is will be validated.
     * @return a message that indicates whether an email is unique or not.
     */
    private String checkUniqueEmail(String email) {
        if (email == null || email.isEmpty()) {
            return EMPTY_EMAIL_ERROR_MESSAGE;
        }

        for (User user : users) {
            if (user != null && user.getEmail() != null && user.getEmail().equalsIgnoreCase(email)) {
                return NOT_UNIQUE_EMAIL_ERROR_MESSAGE;
            }
        }

        return NO_ERROR_MESSAGE;
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public User getUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }

        return null;
    }

    void clearServiceState() {
        this.users.clear();
    }
}
