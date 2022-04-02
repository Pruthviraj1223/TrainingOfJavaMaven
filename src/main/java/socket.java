
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class socket {
    public static void main(String[] args) throws IOException, ClassNotFoundException {


        Socket socket = new Socket("localhost",5555);
        System.out.println("sending");


        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);


        HashMap<Long,String> hashMap = new HashMap<>();
        for(long i=0;i<1000;i++){
            hashMap.put(i,"any");
        }

        bufferedWriter.write("Hello");
        bufferedWriter.flush();
        bufferedWriter.close();


        }
}
