import java.io.*;
import java.lang.instrument.Instrumentation;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;


class ObjectSizeFetcher {
     private static volatile Instrumentation globalInstrumentation;

     public static void premain(final String agentArgs, final Instrumentation inst) {
         globalInstrumentation = inst;
     }

     public static long getObjectSize(final Object object) {
         if (globalInstrumentation == null) {
             throw new IllegalStateException("Agent not initialized.");
         }
         return globalInstrumentation.getObjectSize(object);
     }

}

public class hash {
    public static void main(String[] args) throws IOException {


        HashMap<Long,String> hashMap = new HashMap<>();

        HashSet<Long> hashSet = new HashSet<>();
        for(long i=0;i<10000;i++){
            hashMap.put(i, "111");
            hashSet.add(i);
        }

        System.out.println(hashMap);

        Socket socket = new Socket("localhost",6666);
        System.out.println("sending");


        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(hashMap);
        out.flush();
        out.close();

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.write(byteOut.toByteArray());
        dataOutputStream.flush();
        dataOutputStream.close();

        byte[] pruthvi = byteOut.toByteArray();

        System.out.println(pruthvi.length);

    }
}
