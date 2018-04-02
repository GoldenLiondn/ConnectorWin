package com.connector.View;

import com.connector.Controller.Controller;
import com.connector.Model.App;
import com.connector.Model.Strings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_frame extends JFrame{

    Controller controller = App.getController();

    private JLabel lb_enterNumber;
    private JLabel lb_071;
    private JLabel lb_statusLabel;
    private static JLabel lb_status;
    public static JTextField tf_phonenumber;
    public static JButton btn_callEstablish;
    public static JButton btn_callEnd;
    private Dimension dim = App.getDim();

    JPanel buttons;
    JPanel number;
    JPanel statusPanel;


    public Main_frame() {
        super(Strings.lb_app);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();

        this.setSize((int) (dim.getWidth() / 4), (int) (dim.getHeight() / 3));
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setResizable(false);
        setVisible(true);
    }

    private void initComponents() {
        lb_enterNumber = new JLabel(Strings.lb_phoneNumber);
        lb_enterNumber.setAlignmentX(Component.CENTER_ALIGNMENT);
        lb_071 = new JLabel(Strings.lb_operatorCode);
        lb_071.setFont(new Font("Verdana", Font.BOLD, 15));
        tf_phonenumber = new JTextField();
        tf_phonenumber.setFont(new Font("Verdana", Font.BOLD, 15));
        tf_phonenumber.setMaximumSize(new Dimension(200, 40));
        tf_phonenumber.setToolTipText(Strings.lb_loginLabel_toolTip);
        btn_callEnd = new JButton("Завершить");
        btn_callEnd.setIcon        (new ImageIcon(getClass().getClassLoader().getResource("images/end.png")));
        btn_callEnd.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("images/end2.png")));
        btn_callEnd.setPressedIcon (new ImageIcon(getClass().getClassLoader().getResource("images/end3.png")));
        btn_callEnd.setBorderPainted(false);
        btn_callEnd.setFocusPainted(false);
        btn_callEnd.setContentAreaFilled(false);
        btn_callEnd.setEnabled(false);
        btn_callEnd.setPreferredSize(new Dimension(100, 100));
        btn_callEstablish = new JButton("Вызов");

        btn_callEstablish.setIcon        (new ImageIcon(getClass().getClassLoader().getResource("images/call.png")));
        btn_callEstablish.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("images/call2.png")));
        btn_callEstablish.setPressedIcon (new ImageIcon(getClass().getClassLoader().getResource("images/call3.png")));
        btn_callEstablish.setBorderPainted(false);
        btn_callEstablish.setFocusPainted(false);
        btn_callEstablish.setContentAreaFilled(false);
        btn_callEstablish.setPreferredSize(new Dimension(100, 100));
        lb_status = new JLabel("Статус");
        lb_status.setAlignmentX(Component.CENTER_ALIGNMENT);
        lb_statusLabel = new JLabel("Статус: ");

        buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        buttons.add(btn_callEstablish);
        buttons.add(btn_callEnd);

        number = new JPanel();
        number.setLayout(new BoxLayout(number, BoxLayout.X_AXIS));
        number.add(lb_071);
        number.add(tf_phonenumber);

        statusPanel = new JPanel();
        statusPanel.setLayout(new FlowLayout());
        statusPanel.add(lb_statusLabel);
        statusPanel.add(lb_status);

        getContentPane().setLayout((new BoxLayout( getContentPane(), BoxLayout.Y_AXIS)));
        this.add(lb_enterNumber);
        this.add(Box.createVerticalGlue());
        this.add(number);
        this.add(Box.createVerticalGlue());
        this.add(buttons);
        this.add(statusPanel);


        //!!!!!!!!!!!!!!!HardCODE!!!!!!!!!!!!!!!!!!!!!!!!!!
        tf_phonenumber.setText("3464436");

        btn_callEstablish.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btn_callEnd.setEnabled(true);
                btn_callEstablish.setEnabled(false);
               if(! controller.callRequest(tf_phonenumber.getText())){
                   btn_callEnd.setEnabled(false);
                   btn_callEstablish.setEnabled(true);
               }

            }
        });

        btn_callEnd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controller.callEndRequest();
                btn_callEnd.setEnabled(false);
                btn_callEstablish.setEnabled(true);

            }
        });

        pack();
    }

    public static void setStatus(String status) {

        synchronized(lb_status)
        {
            lb_status.setText(status);
        }

    }
}
