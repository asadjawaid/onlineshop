package entities.impl;

import entities.User;

public class DefaultUser implements User {
    // Properties of a User
    private String firstName, lastName, password, email;
    // Unique id for each DefaultUser instance created.
    private int userId;
    // this field is used for each unique User that gets created
    private static int userIdCounter = 0;

    public DefaultUser() {}

    /**
     * This constructor creates a new DefaultUser object and increments the userId.
     * @param firstName first name of the user
     * @param lastName last name of the user
     * @param password password for this user
     * @param email used for this user
     */
    public DefaultUser(String firstName, String lastName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        userIdCounter++;
        this.userId = userIdCounter;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public void setPassword(String newPassword) {
        password = newPassword;
    }

    @Override
    public void setEmail(String newEmail) {
        email = newEmail;
    }

    // Package access
    void clearState() {
        this.firstName = null;
        this.lastName = null;
        this.email = null;
        this.password = null;
        this.userId = 0;
   }

    /**
     *
     * @param obj is the object that is passed to compare to another DefaultUser Object
     * @return true if the calling object is the same as the passed object and vice versa.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof DefaultUser)) {
            return false;
        }

        DefaultUser defaultUser = (DefaultUser) obj;

        // This is to ensure that each user has different email addresses.
        return this.email.equalsIgnoreCase(defaultUser.getEmail());
    }

    @Override
    public String toString() {
        return "DefaultUser{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", userId=" + userId +
                '}';
    }
}
