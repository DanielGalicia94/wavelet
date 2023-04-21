import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    String hello = "Welcome to my website for the lab report\n";
    String defaults = "Wlecome to my website\n";
    String string = "";

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return hello + string;
        } else if (url.getPath().equals("/increment")) {
            string = string + string;
            return hello + string;
        } else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add-message")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    string += parameters[1] + "\n";
                    return hello + string;
                }
            }
            return "404 Not Found!";
        }
    }
}

public class StringServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
