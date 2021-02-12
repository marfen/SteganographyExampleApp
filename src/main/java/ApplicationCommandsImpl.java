import apis.MediaType;
import steganography.exceptions.MediaCapacityException;
import steganography.exceptions.MediaNotFoundException;
import steganography.exceptions.MediaReassemblingException;
import steganography.exceptions.UnsupportedMediaTypeException;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ApplicationCommandsImpl implements ApplicationCommands{

    private final Application application;
    private final PrintStream consoleOutput;
    private final BufferedReader userinput;



    public ApplicationCommandsImpl(Application app, PrintStream os, InputStream is){
        this.application = app;
        this.consoleOutput = os;
        this.userinput = new BufferedReader(new InputStreamReader(is));
    }



    @Override
    public void printUsage() {

        StringBuilder b = new StringBuilder();

        b.append("\n");
        b.append("commands:");
        b.append("\n");
        b.append("\n");
        b.append(ENCODE_AND_POST);
        b.append("... takes a keyword, filepath, payload and mediatype to encode and post directly");
        b.append("\n");
        b.append(SET_TOKEN);
        b.append("... takes in exist accessToken and AccessTokenSecret as String to post without logging in ");
        b.append("\n");
        b.append(ENCODE_AND_POST_TOKEN);
        b.append("... same as encodePost but with already existing oAuth1 Token");
        b.append("\n");
        b.append(ENCODE_AND_SAVE);
        b.append("... takes filepath, payload, mediaType and savepath to encode and safe media locally");
        b.append("\n");
        b.append(DECODE_CARRIER);
        b.append("... takes mediaType and filepath to decode the hidden message");
        b.append("\n");
        b.append(EXIT);
        b.append("... exit");
        b.append("\n");

        this.consoleOutput.println(b.toString());

    }

    @Override
    public void printUsage(String command) {

    }

    @Override
    public void runApp() {

        boolean again = true;
        while(again){

            printUsage();

            try {
                //read user input
                String cmdLineString = userinput.readLine();

                //finish if no userinput
                if(cmdLineString == null) break;

                switch(cmdLineString){
                    case ENCODE_AND_POST:
                        this.encodeAndPost();
                        break;
                    case SET_TOKEN:
                        this.safeToken();
                        break;
                    case ENCODE_AND_POST_TOKEN:
                        this.encodeAndPostWithToken();
                        break;
                    case ENCODE_AND_SAVE:
                        this.safeAndEncode();
                        break;
                    case DECODE_CARRIER:
                        this.decodeCarrier();
                        break;
                    case EXIT:
                        again = false;
                        break;
                    default:
                        this.consoleOutput.println("unknown command:" + cmdLineString);
                        this.printUsage();
                        break;


                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    public void encodeAndPost() throws IOException {
        String keyword;
        String path;
        byte[] payload;
        MediaType mediaType = null;

        this.consoleOutput.println("please enter a keyword for the post");
        keyword = userinput.readLine();

        this.consoleOutput.println("please enter the path of the file to encode and post");
        path = userinput.readLine();

        this.consoleOutput.println("please enter the message you want to encode in your media");
        payload = userinput.readLine().getBytes(StandardCharsets.UTF_8);

        this.consoleOutput.println("Choose media you want to encode and post ... png/mp3");
        String media = userinput.readLine();

        switch(media){
            case "png":
                mediaType = MediaType.PNG;
                break;
            case "mp3":
                mediaType = MediaType.MP3;
                break;
        }
        this.application.encodeAndPost(keyword, path, payload, mediaType);
    }

    public void safeToken() throws IOException {
        String accessToken;
        String accessTokensecret;

        this.consoleOutput.println("please enter the accessToken");
        accessToken = userinput.readLine();

        this.consoleOutput.println("please enter the accessTokenSecret");
        accessTokensecret = userinput.readLine();
        this.application.safeToken(accessToken, accessTokensecret);
    }

    public void encodeAndPostWithToken() throws IOException {
        String keyword;
        String path;
        byte[] payload;
        MediaType mediaType = null;
        String blogName;

        this.consoleOutput.println("please enter a keyword for the post");
        keyword = userinput.readLine();

        this.consoleOutput.println("please enter the path of the file to encode and post");
        path = userinput.readLine();

        this.consoleOutput.println("please enter the message you want to encode in your media");
        payload = userinput.readLine().getBytes(StandardCharsets.UTF_8);

        this.consoleOutput.println("Choose media you want to encode and post ... png/mp3");
        String media = userinput.readLine();

        this.consoleOutput.println("please enter the name of the blog you want to post to");
        blogName = userinput.readLine();

        switch(media){
            case "png":
                mediaType = MediaType.PNG;
                break;
            case "mp3":
                mediaType = MediaType.MP3;
                break;
        }
        this.application.encodeAndPostWithToken(keyword, path, payload, mediaType, blogName);
    }

    public void safeAndEncode() throws IOException {
        String path;
        byte[] payload;
        MediaType mediaType = null;
        String savePath;

        this.consoleOutput.println("please enter the path of the file to encode");
        path = userinput.readLine();

        this.consoleOutput.println("please enter the message you want to encode in your media");
        payload = userinput.readLine().getBytes(StandardCharsets.UTF_8);

        this.consoleOutput.println("Choose media you want to encode and post ... png/mp3");
        String media = userinput.readLine();

        switch(media){
            case "png":
                mediaType = MediaType.PNG;
                break;
            case "mp3":
                mediaType = MediaType.MP3;
                break;
        }

        this.consoleOutput.println("please enter the path where the encoded File should be stored");
        savePath = userinput.readLine();

        this.application.safeAndEncode(path, payload, mediaType, savePath);
    }

    public void decodeCarrier() throws IOException {
        String path;
        MediaType mediaType = null;
        String message;

        this.consoleOutput.println("please enter the path of the file to decode");
        path = userinput.readLine();

        this.consoleOutput.println("Choose media you want to decode ... png/mp3");
        String media = userinput.readLine();

        switch(media){
            case "png":
                mediaType = MediaType.PNG;
                break;
            case "mp3":
                mediaType = MediaType.MP3;
                break;
        }

        message = this.application.decodeCarrier(mediaType, path);
        this.consoleOutput.println("\n");
        this.consoleOutput.println("hidden message in this Media was:   " + message);
    }


}

