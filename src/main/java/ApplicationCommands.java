public interface ApplicationCommands {

    String SOCIAL_MEDIA_STEGANOGRAPHY = "sms";
    String SOCIAL_MEDIA = "sm";
    String STEGANOGRAPHY = "steg";

    String ENCODE_AND_POST = "encodePost";
    String SET_TOKEN = "setToken";
    String ENCODE_AND_POST_TOKEN = "encodePostToken";
    String ENCODE_AND_SAVE = "encodeSave";
    String DECODE_CARRIER = "decode";
    String POST_PNG_TUMBLR = "pngTumblr";
    String POST_MP3_TUMBLR = "mp3Tumblr";
    String SEARCH_KEYWORD = "search";
    String EXIT = "exit";

    String TUMBLR = "tumblr";

    public void printUsage();

    public void printUsage(String command);

    public void runApp();


}
