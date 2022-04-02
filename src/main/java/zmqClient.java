
import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class zmqClient {


    public void pushPullReceive(){
        try (ZContext context = new ZContext()) {
            // Socket to talk to clients
            ZMQ.Socket socket = context.createSocket(SocketType.PULL);

            socket.connect("tcp://10.20.40.156:5555");
            Scanner scanner = new Scanner(System.in);

            while (true){
                System.out.println("Server : " + socket.recvStr());
            }

        }
    }

    public void reversePushPullReceive(){
        try (ZContext context = new ZContext()) {
            // Socket to talk to clients
            ZMQ.Socket socket = context.createSocket(SocketType.PUSH);

            socket.connect("tcp://10.20.40.156:5555");
            Scanner scanner = new Scanner(System.in);

            for(int i=1;i<=100;i++)
            {
                String s = String.valueOf(i);
                socket.send(s + "");
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pubsub(){
        try (ZContext context = new ZContext()) {
            // Socket to talk to clients
            ZMQ.Socket socket = context.createSocket(SocketType.SUB);

            socket.connect("tcp://192.168.1.8:5555");
            Scanner scanner = new Scanner(System.in);

            socket.subscribe("0");

            while (true){
                System.out.println("received = " + Arrays.toString(socket.recv()));
            }

        }
    }

    public static void main(String[] args) {

        zmqClient zmqClient = new zmqClient();
        zmqClient.pubsub();

    }

}