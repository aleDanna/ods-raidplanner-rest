package ods.raidplanner.exceptions;

public class IncorrectLoginException extends ODSException {

    public IncorrectLoginException(String username, String password) {
        super("Invalid credentials: username '" + username + "', password '" + password + "'");
    }
}
