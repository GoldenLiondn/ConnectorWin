package com.connector.Controller;

import com.connector.View.Login_frame;
import com.connector.View.Registration_frame;
import com.connector.Model.App;
import com.connector.Model.Strings;

import javax.swing.*;

public class Controller {
    private App app = new App();

    public boolean callRequest(String number) {

        if (number.length() != 7) {
            JOptionPane.showMessageDialog(null, Strings.msg_invalidPhoneNumberQuant);
            return false;
        }

        if (!isOnlyNumbers(number)) {
            JOptionPane.showMessageDialog(null, Strings.msg_invalidPhoneNumberSimbols);
            return false;
        }

        app.callRequest(number);


        return true;
    }

    public void callEndRequest(){
        app.callEndRequest();
    }

    public void loginRequest(String[] textFields) {
        if (textFields[0].equalsIgnoreCase("")) {
            Login_frame.requestFocusInLogin(0);
            return;
        }
        if (textFields[1].equalsIgnoreCase("")) {
            Login_frame.requestFocusInLogin(1);
            return;
        }

        if (textFields[0].length() != 10) {
            JOptionPane.showMessageDialog(null, Strings.msg_invalidPhoneNumberQuant10);
            return;
        }

        if (!isOnlyNumbers(textFields[0])) {
            JOptionPane.showMessageDialog(null, Strings.msg_invalidPhoneNumberSimbols);
            return;
        }

        app.login(textFields);

    }

    /**
     * com.connector.Controller receives list of Strings that containes values of textfields in Registration frame and check the validation of neccesary fields.
     * Values:
     * textFields[0] - telephone number of new client
     * textFields[1] - name of new client
     * textFields[2] - referal
     * textFields[3] - password of new client
     * textFields[4] - password confirmation of new client
     *
     * @param textFields
     */
    public void registrationRequest(String[] textFields) {
        for (int i = 0; i < textFields.length; i++) {
            if (textFields[i].equalsIgnoreCase("")) {
                Registration_frame.requestFocusInReg(i);
                return;
            }
        }
        if (textFields[0].length() != 10) {
            JOptionPane.showMessageDialog(null, Strings.msg_invalidPhoneNumberQuant10);
            return;
        }

        if (!isOnlyNumbers(textFields[0])) {
            JOptionPane.showMessageDialog(null, Strings.msg_invalidPhoneNumberSimbols);
            return;
        }

        if (!String.valueOf(textFields[3]).equalsIgnoreCase(String.valueOf(textFields[4]))) {
            JOptionPane.showMessageDialog(null, Strings.msg_invalidConfirm);
            return;
        }

        app.registration(textFields);

    }

    private boolean isOnlyNumbers(String text) {
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] < 48 || chars[i] > 58)
                return false;
        }
        return true;
    }
}
