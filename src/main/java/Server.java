import java.io.*;
import java.net.*;

public class Server implements Runnable {
    private static Socket connectionSocket;

    public static void main (String[] args) throws Exception {
        System.out.println(" Server is Running ");
        ServerSocket mySocket = new ServerSocket(5555);

        while (true) {
            connectionSocket = mySocket.accept();
            Server serv = new Server(connectionSocket);
            Thread serverThread = new Thread(serv);
            serverThread.start();
        }
    }

    public Server(Socket s) {
    }

    public void run() {
        while(true){

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream()));

                writer.write("*** Welcome to the Calculation Server (Addition Only) ***\r\n");
                writer.write("*** Please type in the first number and press Enter : \n");

                writer.flush();
                String data1 = reader.readLine().trim();

                writer.write("*** Please type in the second number and press Enter :\n");

                writer.flush();
                String data2 = reader.readLine().trim();

                int num1 = Integer.parseInt(data1);
                int num2 = Integer.parseInt(data2);
                int result = num1 + num2;

                System.out.println("Addition operation done ");
                writer.write("\r\n=== Result is : \n" + result + "\n");
                writer.flush();

                //connectionSocket.close();

            } catch ( Exception e ){ e.printStackTrace(); }
        }
    }
}