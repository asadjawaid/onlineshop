package entities;

/**
 * Contains behaviours for a user
 */
public interface User {
    String getFirstName();
    String getLastName();
    String getPassword();
    String getEmail();
    int getUserId();

    void setPassword(String newPassword);
    void setEmail(String newEmail);
}
