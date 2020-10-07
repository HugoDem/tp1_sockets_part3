import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class mychat {
    public JTextField tx;
    public JTextArea ta;
    public String login = "Imed";
    BufferedWriter writer;

    public mychat(String l) {
        login = l;

        JFrame f = new JFrame("my chat");
        f.setSize(400, 400);

        JPanel p1 = new JPanel();
        p1.setLayout(new BorderLayout());

        JPanel p2 = new JPanel();
        p2.setLayout(new BorderLayout());

        tx = new JTextField();
        p1.add(tx, BorderLayout.CENTER);

        JButton b1 = new JButton("Send");
        p1.add(b1, BorderLayout.EAST);

        ta = new JTextArea();
        p2.add(ta, BorderLayout.CENTER);
        p2.add(p1, BorderLayout.SOUTH);

        f.setContentPane(p2);

        try {
            Socket socketClient = new Socket("localhost", 5555);
            writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        b1.addActionListener(new ActionListener() {
                                 public void actionPerformed(ActionEvent ev) {
                                     String s = tx.getText();
                                     tx.setText("");
                                     if(s != null && s.length() > 0){
                                         try {
                                             writer.write(login + " : " + s);
                                             writer.write("\r\n");
                                             writer.flush();
                                         } catch (Exception e) {
                                             e.printStackTrace();
                                         }
                                     }
                                 }
                             }
        );

        f.setVisible(true);
    }
}
