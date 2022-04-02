
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Serversocket {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serversocket = new ServerSocket(6666);
        System.out.println("accepting...");
        Socket s = serversocket.accept();
        System.out.println("accepted");


//        InputStream inputStream = new DataInputStream(s.getInputStream());

        ObjectInputStream objectInputStream = new ObjectInputStream(s.getInputStream());

        Object obj = objectInputStream.readObject();

        System.out.println(obj);

        Map<Long, String> data2 = (Map<Long, String>) obj;

        System.out.println(data2.size());
        System.out.println(data2);



        while (true){

        }


    }
}