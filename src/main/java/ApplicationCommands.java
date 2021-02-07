public interface ApplicationCommands {

    String SOCIAL_MEDIA_STEGANOGRAPHY = "sms";
    String SOCIAL_MEDIA = "sm";
    String STEGANOGRAPHY = "steg";

    String TUMBLR = "tumblr";

    public void printUsage();

    public void printUsage(String command);

    public void runApp();
}
