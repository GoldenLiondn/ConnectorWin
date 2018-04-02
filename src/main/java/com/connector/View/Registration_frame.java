package com.connector.View;


import com.connector.Controller.Controller;
import com.connector.Model.App;
import com.connector.Model.Strings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registration_frame extends JDialog{

    private Controller controller = App.getController();

    private JButton btn_reg;
    private JLabel lb_newAcc;
    private JLabel lb_name;
    private JLabel lb_phoneNumber;
    private JLabel lb_password;
    private JLabel lb_confirm;
    private JLabel lb_referal;
    private JPasswordField tf_password;
    private JTextField tf_name;
    private JPasswordField tf_confirm;
    private JTextField tf_phoneNumber;
    private JTextField tf_referal;
    static  private JTextField[] textFields;
    private String[] textFieldsValues;
    private Dimension dim = App.getDim();

    public Registration_frame() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setModal(true);
        this.setSize((int) (dim.getWidth() / 2), (int) (dim.getHeight() / 2));
        initComponents();
        this.setLocation((dim.width/2-this.getSize().width/2)+20, (dim.height/2-this.getSize().height/2)+20);
        this.setResizable(false);
    }


    private void initComponents() {
        lb_newAcc = new JLabel();
        lb_phoneNumber = new JLabel();
        lb_name = new JLabel();
        lb_referal = new JLabel();
        lb_password = new JLabel();
        lb_confirm = new JLabel();

        tf_phoneNumber = new JTextField(10);
        tf_name = new JTextField();
        tf_referal = new JTextField();
        tf_password = new JPasswordField();
        tf_confirm = new JPasswordField();
        textFields = new JTextField[]{tf_phoneNumber,tf_name, tf_referal, tf_password, tf_confirm};
        btn_reg = new JButton(Strings.btn_registrationButton);
        btn_reg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //checking empty textfields and focus on it if exists
                textFieldsValues = getTextFeldsValues(textFields);
                controller.registrationRequest(textFieldsValues);

            }
        });


        lb_newAcc.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lb_newAcc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);


        lb_newAcc.setText(Strings.lb_newAcc);
        lb_phoneNumber.setText(Strings.lb_loginLabel);
        lb_name.setText(Strings.lb_name);
        lb_referal.setText(Strings.lb_referal);
        lb_password.setText(Strings.lb_passwordLabel);
        lb_confirm.setText(Strings.lb_confirm);


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(122, 122, 122)
                                                .addComponent(lb_newAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(32, 32, 32)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lb_phoneNumber)
                                                        .addComponent(lb_name)
                                                        .addComponent(lb_referal)
                                                        .addComponent(lb_password)
                                                        .addComponent(lb_confirm))
                                                .addGap(32, 32, 32)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(tf_phoneNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                                                        .addComponent(tf_name)
                                                        .addComponent(tf_referal)
                                                        .addComponent(tf_password)
                                                        .addComponent(tf_confirm)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(157, 157, 157)
                                                .addComponent(btn_reg)))
                                .addContainerGap(107, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lb_newAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tf_phoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lb_phoneNumber))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lb_name)
                                        .addComponent(tf_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lb_referal)
                                        .addComponent(tf_referal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lb_password)
                                        .addComponent(tf_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lb_confirm)
                                        .addComponent(tf_confirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addComponent(btn_reg)
                                .addContainerGap(26, Short.MAX_VALUE))
        );
        pack();

    }

    private String[] getTextFeldsValues(JTextField[] textFields) {
        String[] s = new String[textFields.length];
        for(int i=0;i<s.length;i++){
            s[i] = textFields[i].getText();
        }
        return s;
    }

    public static void requestFocusInReg(int i){

                textFields[i].requestFocus();

 }

    public void setPhoneNumber(String number) {
        this.tf_phoneNumber.setText(number);
    }
}
