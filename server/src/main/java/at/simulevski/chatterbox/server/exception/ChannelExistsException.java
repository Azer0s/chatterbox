package at.simulevski.chatterbox.server.exception;

public class ChannelExistsException extends RuntimeException {
    public ChannelExistsException(String channelName) {
        super("Channel with name " + channelName + " already exists");
    }
}
