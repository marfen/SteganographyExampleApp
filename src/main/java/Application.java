import apis.MediaType;
import apis.SocialMedia;
import apis.models.APINames;
import apis.models.Token;
import apis.tumblr.Tumblr;
import socialmediasteganography.SocialMediaSteganography;
import socialmediasteganography.SocialMediaSteganographyImpl;
import steganography.Steganography;
import steganography.exceptions.*;
import steganography.util.ByteArrayUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Application {

    SocialMedia socialMedia;
    SocialMediaSteganography sms;
    Token token;

    public Application(){
        Tumblr.setApiKey("OfpsSPZAf9mClIvgVAKY3Hhg63Y09riZ9AMmbbI0hQVMdS4uYR");
        Tumblr.setApiSecret("H2yGuhhwd7g6eXIYE0OHpkL7fEd9laDWPHArjipezGyq9dFheF");
        socialMedia = new Tumblr();
        sms = new SocialMediaSteganographyImpl();

    }

    public void encodeAndPost(String keyword, String path, byte[] payload, MediaType mediaType){
        try {
            sms.encodeAndPost(APINames.TUMBLR, keyword, path, payload, mediaType);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedMediaTypeException e) {
            e.printStackTrace();
        } catch (MediaNotFoundException e) {
            e.printStackTrace();
        } catch (MediaReassemblingException e) {
            e.printStackTrace();
        } catch (MediaCapacityException e) {
            e.printStackTrace();
        }
    }

    public void safeToken(String accessToken, String accessTokenSecret){
        token = new Token(accessToken, accessTokenSecret);
    }

    public void encodeAndPostWithToken(String keyword, String path, byte[] payload, MediaType mediaType, String blogname) throws IOException {
        File file = new File(path);
        byte[] carrier = ByteArrayUtils.read(file);

        try {
            sms.encodeAndPost(APINames.TUMBLR, keyword, carrier, payload, mediaType, token, blogname);
        } catch (UnsupportedMediaTypeException e) {
            e.printStackTrace();
        } catch (MediaNotFoundException e) {
            e.printStackTrace();
        } catch (MediaReassemblingException e) {
            e.printStackTrace();
        } catch (MediaCapacityException e) {
            e.printStackTrace();
        }
    }

    public void safeAndEncode(String path, byte[] payload, MediaType mediaType, String savePath) {

        try {
            sms.saveEncodePicture(path, mediaType, payload, savePath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedMediaTypeException e) {
            e.printStackTrace();
        } catch (MediaNotFoundException e) {
            e.printStackTrace();
        } catch (MediaReassemblingException e) {
            e.printStackTrace();
        } catch (MediaCapacityException e) {
            e.printStackTrace();
        }
    }

    public String decodeCarrier(MediaType mediaType, String path){
        File file = new File(path);
        byte[] carrier = new byte[0];
        byte[] message = null;
        String hiddenMessage;
        try {
            carrier = ByteArrayUtils.read(file);
            message = sms.decodeCarrier(mediaType, carrier);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnknownStegFormatException e) {
            e.printStackTrace();
        } catch (UnsupportedMediaTypeException e) {
            e.printStackTrace();
        } catch (MediaNotFoundException e) {
            e.printStackTrace();
        }
        return hiddenMessage = new String(message, StandardCharsets.UTF_8);

    }

}
