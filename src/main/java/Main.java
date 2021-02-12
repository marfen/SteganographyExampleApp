import java.io.PrintStream;

public class Main {

    public static void main(String[] args){
        Application application = new Application();
        PrintStream os = System.out;

        os.println("Welcome to SocialMedia-Steganography");
        ApplicationCommandsImpl userCmd = new ApplicationCommandsImpl(application, os, System.in);

        userCmd.runApp();
    }
}
