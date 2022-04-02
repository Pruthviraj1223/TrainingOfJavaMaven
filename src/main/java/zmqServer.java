
import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class zmqServer
{

    public void pushPull(){

        try (ZContext context = new ZContext()) {
            // Socket to talk to clients
            ZMQ.Socket socket = context.createSocket(SocketType.PUSH);

            socket.bind("tcp://10.20.40.156:5555");

            Scanner scanner = new Scanner(System.in);

            for(int i=1;i<=100;i++)
            {
                String s = String.valueOf(i);
                socket.send(s);
                Thread.sleep(3000);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void reversePushPull(){
        try (ZContext context = new ZContext()) {
            // Socket to talk to clients
            ZMQ.Socket socket = context.createSocket(SocketType.PULL);

            socket.bind("tcp://10.20.40.156:5555");

            Scanner scanner = new Scanner(System.in);

            while(true){
                System.out.println("received = " + socket.recvStr());
            }

        }
    }

    public void pubsub(){

        try (ZContext context = new ZContext()) {
            // Socket to talk to clients
            ZMQ.Socket socket = context.createSocket(SocketType.PUB);

            socket.bind("tcp://192.168.1.8:5555");


            List list= Arrays.asList("Pruthvi","vedant","Sit");


            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(list);
            objectOutputStream.flush();
            objectOutputStream.close();


            for(int i=1;i<=100;i++)
            {
                socket.subscribe("0");
               socket.send(byteArrayOutputStream.toByteArray());
               Thread.sleep(1000);
            }
            while (true){}

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) throws Exception
    {
        zmqServer zmqServer = new zmqServer();
        zmqServer.pubsub();

        }
    }