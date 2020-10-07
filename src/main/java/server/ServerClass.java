package server;

import java.io.*;
import java.net.*;

public class ServerClass {

    public static void main(String[] args) throws IOException {
        ServerSocket mySocket = new ServerSocket(5555);
        try {
            while (true) {
                System.out.println("Waiting...");

                Socket connectionSocket = mySocket.accept();
                System.out.println("Client connected");

                InputStream stream = ServerClass.class.getResourceAsStream("myFile.txt");
                File f = new File("C:\\Users\\hugod\\OneDrive\\Desktop\\Cours\\II 3502\\tp1_sockets\\src\\main\\java\\server\\myFile.txt");

                byte[] bArray = new byte[(int) f.length()];

                FileInputStream fis = new FileInputStream(f);
                BufferedInputStream bis = new BufferedInputStream(fis);

                OutputStream os = connectionSocket.getOutputStream();
                System.out.println("Sending myFile.txt (" + bArray.length + " bytes)");

                os.write(bArray, 0, bArray.length);
                os.flush();

                System.out.println("Done.");

            }
        } finally {
            mySocket.close();
        }
    }
}