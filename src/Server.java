
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Server {

    // a shared area where we get the POST data and then use it in the other handler
    static String sharedResponse = "";
    static boolean gotMessageFlag = false;

    public static void main(String[] args) throws Exception {
    	new MainDirectory();
        // set up a simple HTTP server on our local host
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // create a context to get the request to display the results
        server.createContext("/displayresults", new DisplayHandler());

        // create a context to get the request for the POST
        server.createContext("/sendresults",new PostHandler());
        server.setExecutor(null); // creates a default executor

        // get it going
        System.out.println("Starting Server...");
        server.start();
    }
    

    static class DisplayHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            String response = "Begin of response\n";
			Gson g = new Gson();
			// set up the header
            System.out.println(response);
            if(sharedResponse.equals("PRINT")){
            	response +=  MainDirectory.getAllEmployees();
            	response += "End of response\n";
            }
            sharedResponse = "";
            System.out.println(response);
            // write out the response
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class PostHandler implements HttpHandler {
        public void handle(HttpExchange transmission) throws IOException {

            //  shared data that is used with other handlers
            sharedResponse = "";

            // set up a stream to read the body of the request
            InputStream inputStr = transmission.getRequestBody();
     
            // set up a stream to write out the body of the response
            OutputStream outputStream = transmission.getResponseBody();

            // string to hold the result of reading in the request
            StringBuilder sb = new StringBuilder();
            String postResponse = "notWorking";
            // read the characters from the request byte by byte and build up the sharedResponse
            int nextChar = inputStr.read();
            while (nextChar > -1) {
                sb=sb.append((char)nextChar);
                nextChar=inputStr.read();
            }
            String x = sb.toString();
            String[] R = x.split(" ");
            if(R[0].equals("ADD")){
            	/*StringBuilder employeeToAdd = new StringBuilder(); 
            	while (nextChar > -1) {
                     employeeToAdd=employeeToAdd.append((char)nextChar);
                     nextChar=inputStr.read();
                 }*/
            	postResponse = "ROGER JSON RECEIVED";
            	MainDirectory.add(R[1]);//should be JSON employee
            	
            }
            /*
            while(count <= 5){
            	sb=sb.append((char)nextChar);
            	nextChar = inputStr.read();
            	count++;
            	//if(sb.toString().equals("PRINT") || sb.toString().equals("CLEAR")){break;}
            }*/
            if(R[0].equals("PRINT")){
            	postResponse = "Printing";
            }
            else if(R[0].equals("CLEAR")){
            	MainDirectory.clear();
            	postResponse = "Clearing";
            }

            // create our response String to use in other handler
            sharedResponse = sharedResponse+sb.toString();

            // respond to the POST with ROGER

            System.out.println("response: " + sharedResponse);

            //Desktop dt = Desktop.getDesktop();
            //dt.open(new File("raceresults.html"));

            // assume that stuff works all the time
            transmission.sendResponseHeaders(300, postResponse.length());

            // write it and return it
            outputStream.write(postResponse.getBytes());

            outputStream.close();
        }
    }

}