package com.connector.View;

import com.connector.Controller.Controller;
import com.connector.Model.App;
import com.connector.Model.Strings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



/**
 * @author Maks Ovcharenko
 */

public class Login_frame extends JFrame {
    private static Registration_frame registration_frame;

    private Controller controller = App.getController();
    private JPanel labels;
    private JPanel logPass;
    private JPanel buttons;

    private JLabel lb_greeting1Str;
    private JLabel lb_greeting2Str;
    private JLabel lb_loginLabel;
    private JLabel lb_passwordLabel;
    private JTextField tf_loginField;
    private JPasswordField tf_passwordField;
    private JButton btn_loginButton;
    private JButton btn_registrationButton;
    private JButton btn_exitButton;

    private static JTextField[] textFields;

    private Dimension dim = App.getDim();

    public Login_frame() {
        super(Strings.lb_auth);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();

        this.setSize((int) (dim.getWidth() / 2), (int) (dim.getHeight() / 2));
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setResizable(false);
        setVisible(true);
        registration_frame = new Registration_frame();
    }

    private void initComponents() {
        Font mainFont = new Font("Verdana", Font.PLAIN, 15);
        lb_greeting1Str = new JLabel(Strings.lb_greeting1Str);
        lb_greeting1Str.setFont(new Font("Verdana", Font.BOLD, 15));
        lb_greeting1Str.setForeground(Color.RED);
        lb_greeting2Str = new JLabel(Strings.lb_greeting2Str);
        lb_greeting2Str.setFont(mainFont);
        lb_loginLabel = new JLabel(Strings.lb_loginLabel);
        lb_passwordLabel = new JLabel(Strings.lb_passwordLabel);
        tf_loginField = new JTextField(10);
        tf_loginField.setFont(mainFont);
        tf_loginField.setMaximumSize(new Dimension((int)(dim.getWidth() / 6), 40));
        tf_loginField.setToolTipText(Strings.lb_loginLabel_toolTip);
        tf_passwordField = new JPasswordField();
        tf_passwordField.setFont(mainFont);
        tf_passwordField.setMaximumSize(new Dimension((int)(dim.getWidth() / 6), 40));
        tf_passwordField.setToolTipText(Strings.lb_passwordLabel_toolTip);
        btn_loginButton = new JButton(Strings.btn_loginButton);
        btn_loginButton.setPreferredSize(new Dimension((int)(dim.getWidth() / 10), 25));
        btn_registrationButton = new JButton(Strings.btn_registrationButton);
        btn_registrationButton.setPreferredSize(new Dimension((int)(dim.getWidth() / 10), 25));
        btn_exitButton = new JButton(Strings.btn_exitButton);

        textFields = new JTextField[]{tf_loginField, tf_passwordField};

        getContentPane().setLayout(new BorderLayout());
        labels = new JPanel();
        labels.setLayout(new BoxLayout(labels, BoxLayout.Y_AXIS));
        lb_greeting1Str.setAlignmentX(Component.CENTER_ALIGNMENT);
        lb_greeting2Str.setAlignmentX(Component.CENTER_ALIGNMENT);
        labels.add(lb_greeting1Str);
        labels.add(lb_greeting2Str);
        this.add(labels, "North");

        logPass = new JPanel();
        logPass.setLayout(new BoxLayout(logPass, BoxLayout.Y_AXIS));
        lb_loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        logPass.add(Box.createVerticalGlue());
        logPass.add(lb_loginLabel);
        tf_loginField.setAlignmentX(Component.CENTER_ALIGNMENT);
        logPass.add(tf_loginField, getContentPane());
        lb_passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        logPass.add(lb_passwordLabel, getContentPane());
        tf_passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        logPass.add(tf_passwordField, getContentPane());
        logPass.add(Box.createVerticalGlue());
        this.add(logPass, "Center");

        //!!!!!!!!!!!!!!!HardCODE!!!!!!!!!!!!!!!!!!!!!!!!!!
        tf_loginField.setText("0663895768");
        tf_passwordField.setText("222");



        buttons = new JPanel();
        buttons.setLayout(new GridLayout(0, 1));
        JPanel logRegButtonsPanel = new JPanel();
        logRegButtonsPanel.setLayout(new FlowLayout());
        logRegButtonsPanel.add(btn_loginButton);
        logRegButtonsPanel.add(btn_registrationButton);
        buttons.add(logRegButtonsPanel);
        buttons.add(btn_exitButton);
        this.add(buttons, "South");
        pack();

        btn_loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String login = tf_loginField.getText();
                String password = String.valueOf(tf_passwordField.getPassword());
                controller.loginRequest(new String[]{login,password});

            }
        });

        btn_registrationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                registration_frame.setPhoneNumber(tf_loginField.getText());
                registration_frame.setVisible(true);

            }
        });

        btn_exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.exit(0);
            }
        });

    }

    public static void requestFocusInLogin(int i){
        textFields[i].requestFocus();
    }


    public static Registration_frame getRegistration_frame() {

        return registration_frame;
    }
}
