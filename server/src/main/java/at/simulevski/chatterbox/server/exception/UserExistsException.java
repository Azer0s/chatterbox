package at.simulevski.chatterbox.server.exception;

public class UserExistsException extends RuntimeException {
    public UserExistsException(String username) {
        super("User with username " + username + " already exists");
    }
}
