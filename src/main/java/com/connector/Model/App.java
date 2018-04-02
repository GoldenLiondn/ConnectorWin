package com.connector.Model;

import com.connector.Controller.Controller;
import com.connector.View.Login_frame;
import com.connector.View.Main_frame;
import com.connector.View.Registration_frame;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Hello world!
 */
public class App {

    public static final String SERVER_IP = "localhost";
    public static final int SERVER_PORT = 1010;

    public static boolean calling = false;

    public static Socket socket;
    ObjectInputStream ois;
    ObjectOutputStream oos;

    Thread statusThread;
    Call call;

    static Login_frame login_frame;
    static Main_frame main_frame;
    static Registration_frame registration_frame;

    private static Controller controller = new Controller();
    private static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    public static void main(String[] args) {
        App application = new App();
        login_frame = new Login_frame();

    }


    public void login(String[] data) {

        try {
            if (socket == null) {
                initSocket();
            }
            TransferedData td = serverRequest("login", data);
            if (td.action.equals("Success")) {
                login_frame.dispose();
                main_frame = new Main_frame();
                statusThread = new Thread(new Status());
                statusThread.start();

            }
            if (td.action.equals("Fail")) {
                JOptionPane.showMessageDialog(null, td.data[0]);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, Strings.msg_netFail);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, Strings.msg_netFail);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, Strings.msg_netFail);
        }

    }


    public void registration(String[] data) {
        try {
            if (socket == null) {
                initSocket();
            }

            TransferedData td = serverRequest("registration", data);
            if (td.action.equals("Success")) {
                registration_frame = Login_frame.getRegistration_frame();
                registration_frame.dispose();
                JOptionPane.showMessageDialog(null, Strings.msg_regSuccess);

            } else if (td.action.equals("Fail")) {
                JOptionPane.showMessageDialog(null, Strings.msg_regFail + "\n" + td.data[0]);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, Strings.msg_netFail);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, Strings.msg_netFail);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null,  Strings.msg_netFail);
        }

    }

    public void callRequest(String number) {
        try {
            if (socket == null) {
                System.out.println("null socket");
                initSocket();
            }

            TransferedData td = serverRequest("request_call", new String[]{number});
            if (td.action.equals("Fail")) {
                JOptionPane.showMessageDialog(null, td.data[0]);
                Main_frame.btn_callEnd.setEnabled(false);
                Main_frame.btn_callEstablish.setEnabled(true);
            } else if (td.action.equals("Success")) {
          //      statusThread.interrupt();
                Main_frame.setStatus("Вызываем...");
                Main_frame.tf_phonenumber.setEditable(false);
                calling = true;
                call = new Call();
                call.init_audio();
                call.init_player();
                try {
                    call.init_recorder(InetAddress.getByName(SERVER_IP), call.portToSend);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                    App.calling = false;
                    call.datagramSocket.close();
                    call.datagramSocket = null;
                    System.out.println("Socket closed");
         //           statusThread = new Thread(new Status());
         //           statusThread.start();
                }
            }


        } catch (IOException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void callEndRequest() {
        try {
            serverRequest("end_call", new String[]{"end"});
            Main_frame.tf_phonenumber.setEditable(true);
            calling = false;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            Main_frame.tf_phonenumber.setEditable(true);
            calling = false;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


    public static Controller getController() {
        return controller;
    }

    public static Dimension getDim() {
        return dim;
    }

    private void initSocket() {
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            System.out.println("Socket created");
        } catch (IOException e) {
            System.out.println("Socket error in App");
        }
    }

    public synchronized TransferedData serverRequest(String request, String[] data) throws IOException, ClassNotFoundException {
        oos.writeObject(new TransferedData(request, data));
        oos.flush();
        return (TransferedData) ois.readObject();
    }

    class Status implements Runnable {

        @Override
        public void run() {
            TransferedData td = null;
            while (true) {
                try {
                    while (true) {
                        System.out.println("обновление статуса");
                        td = serverRequest("request_status", new String[]{});
                        App.calling = td.calling;
                        if(App.calling){
                            Main_frame.btn_callEstablish.setEnabled(false);
                        }else {
                            Main_frame.btn_callEstablish.setEnabled(true);
                            Main_frame.btn_callEnd.setEnabled(false);
                            Main_frame.tf_phonenumber.setEditable(true);
                        }
                        Main_frame.setStatus(td.action);
                        if (td.action.equals("Линия занята")||td.action.equals("Ошибка сервера")) {
                            Main_frame.btn_callEstablish.setEnabled(false);
                        } else {
                            Main_frame.btn_callEstablish.setEnabled(true);
                        }
                        Thread.sleep(1000);
                    }

                } catch (IOException e) {
                    Main_frame.btn_callEstablish.setEnabled(false);
                    Main_frame.setStatus(Strings.msg_netFail);
                    td = null;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    System.out.println("поток остановлен");
                    break;
                }

                try {
                    System.out.println("обновление статуса/нет сети..................................................");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (td == null) initSocket();

            }
        }
    }

}
