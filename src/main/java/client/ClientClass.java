package client;

import java.io.*;
import java.net.*;

public class ClientClass {
    public static void main(String[] args) throws IOException {
        Socket socketClient = new Socket("localhost", 5555);
        try {
            System.out.println("Client: Connection Established");

            byte[] byteArray = new byte[91];
            InputStream is = socketClient.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            BufferedWriter writer= new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));

            FileOutputStream fos = new FileOutputStream("C:\\Users\\hugod\\OneDrive\\Desktop\\Cours\\II 3502\\tp1_sockets\\src\\main\\java\\client\\myOtherFile.txt");

            String line;
            while((line = reader.readLine()) != null && line.length() > 0){
                System.out.println(line);
                fos.write(line.getBytes());
            }
        } finally {
            socketClient.close();
        }
    }
}
