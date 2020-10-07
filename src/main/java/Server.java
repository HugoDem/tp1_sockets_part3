import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server implements Runnable {
    private static ServerSocket mySocket;
    private static final String[] loginList = {"igoTrip", "Pipou79", "Bawawa", "PhsychedLego", "TTT","OtherMan","TheBean", "ILikeTrains","oopsIdidItAgain","French79","BillyTheKid"};
    private static List<mychat> chatList = new ArrayList<mychat>();

    public static void main(String[] args) throws IOException {

        mySocket = new ServerSocket(5555);
        System.out.println(" Server is Running ");

        Scanner scan = new Scanner(System.in);
        System.out.println("How many participants in the group chat ?");
        int nb = scan.nextInt();

        for(int _ = 0; _ < nb; _++) {
            Server serv = new Server();
            Thread serverThread = new Thread(serv);
            serverThread.start();
        }
    }

    public Server(){}

    public void run(){

            try {
                mychat chat = new mychat(loginList[(int)(Math.random() * loginList.length)]);
                chatList.add(chat);

                Socket connectionSocket = mySocket.accept();
                InputStream is = connectionSocket.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

                while(true){
                    String line = reader.readLine();
                    if(line != null) updateChat(line);
                }
            } catch ( IOException e ){ e.printStackTrace(); }

    }

    public void updateChat(String s){
        for(mychat chat : chatList){
            if(chat == null) { System.out.println("chat null"); }
            if(chat.ta.getText() != null){
                chat.ta.setText(chat.ta.getText()+"\n"+s);
            } else {
                chat.ta.setText(s);
            }
        }
    }

}