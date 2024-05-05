package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window {

    public static void showLoginMenu(JFrame frame, Font font){
        JPanel dataPlanel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(dataPlanel, BoxLayout.Y_AXIS);
        dataPlanel.setLayout(boxlayout);

        JPanel loginPanel = new JPanel();
        JPanel passwordPanel = new JPanel();

        JLabel loginPrm = new JLabel("Login: ");
        loginPrm.setFont(font);
        JTextField login = new JTextField(10);
        JLabel passwordPrm = new JLabel("Password: ");
        passwordPrm.setFont(font);
        JPasswordField password  = new JPasswordField(10);

        loginPanel.add(loginPrm);
        loginPanel.add(login);
        passwordPanel.add(passwordPrm);
        passwordPanel.add(password);

        dataPlanel.add(loginPanel);
        dataPlanel.add(passwordPanel);

        JButton send = new JButton("Send");
        send.setFont(font);
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println(login.getText() + password.getText());
            }
        });

        dataPlanel.add(send);


        frame.getContentPane().add(BorderLayout.NORTH, dataPlanel);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Task Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);

        Font font = new Font("Times New Roman", Font.TYPE1_FONT, 16);

        showLoginMenu(frame, font);

        frame.setVisible(true);
    }
}