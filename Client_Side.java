import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream; 
import java.io.InputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client_Side{
    public static void main(String[] args){
        try{
            Socket s = new Socket("localhost", 50000);

            InputStream dis = new DataInputStream(s.getInputStream());
            OutputStream dout = new DataOutputStream(s.getOutputStream());

            //Make a string, convert it to a byte array then snd to a server 
            String myString = "HELO";
            dout.write(myString.getBytes());
            dout.flush();

            System.out.println("Client has sent \"" + myString + "\" to the server......" );

            //Read the byte stream from the server 
            byte[] byteArray = new byte[dis.available()];
            dis.read(byteArray);

            //Make a string using the recieved byte and print the line
            myString = new String(byteArray, StandardCharsets.UTF_8);
            System.out.println(myString + "recieved from server");


        } catch(Exception e) {System.out.println(e);}
    }


}