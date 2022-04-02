
import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class zmqClient2 {

    public void pubsub(){
        try (ZContext context = new ZContext()) {
            // Socket to talk to clients
            ZMQ.Socket socket = context.createSocket(SocketType.SUB);


            socket.connect("tcp://192.168.1.8:5555");;
            Scanner scanner = new Scanner(System.in);
            socket.subscribe("1");

            while (true){
                System.out.println("received = " + socket.recvStr());
            }
        }
    }

    public static void main(String[] args) {


        zmqClient2 zmqClient2 = new zmqClient2();
        zmqClient2.pubsub();

    }

}