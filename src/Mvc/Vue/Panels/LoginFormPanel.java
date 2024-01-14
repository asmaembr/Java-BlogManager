package Mvc.Vue.Panels;

import javax.swing.*;
import java.awt.*;

public class LoginFormPanel extends JPanel {

    private JLabel lbl_username, lbl_password;
    private JTextField txt_username;
    private JPasswordField txt_password;

    private void initTextFields(Color textColor, Font font, Color color){

        txt_username = new JTextField();
        txt_username.setForeground(textColor);
        txt_username.setFont(font);
        txt_username.setBackground(color);
        txt_username.setHorizontalAlignment(JTextField.CENTER);
        txt_username.setBorder(null);

        txt_password = new JPasswordField();
        txt_password.setForeground(textColor);
        txt_password.setFont(font);
        txt_password.setBackground(color);
        txt_password.setHorizontalAlignment(JTextField.CENTER);
        txt_password.setBorder(null);


    }
    private void initLabels(Color textColor , Font font ,String lbl_text1, String lbl_text2){

        lbl_username = new JLabel();
        lbl_username.setText(lbl_text1);
        lbl_username.setFont(font);
        lbl_username.setForeground(textColor);
        lbl_username.setHorizontalAlignment(JLabel.LEFT);


        lbl_password = new JLabel();
        lbl_password.setText(lbl_text2);
        lbl_password.setFont(font);
        lbl_password.setForeground(textColor);
        lbl_password.setHorizontalAlignment(JLabel.LEFT);

    }


    public LoginFormPanel(Color textColor, Font font, Color bgColor, Color colorTxtFields, String lbl_text1, String lbl_text2)

      {
          setLayout(new GridLayout(4,1));
          initTextFields(textColor, font,colorTxtFields);
          initLabels(textColor,font,lbl_text1,lbl_text2);
          setBackground(bgColor);
          add(lbl_username);
          add(txt_username);
          add(lbl_password);
          add(txt_password);

       }

    public JLabel getLbl_username() {
        return lbl_username;
    }

    public void setLbl_username(JLabel lbl_username) {
        this.lbl_username = lbl_username;
    }

    public JLabel getLbl_password() {
        return lbl_password;
    }

    public void setLbl_password(JLabel lbl_password) {
        this.lbl_password = lbl_password;
    }

    public String getTxt_username() {
        return txt_username.getText().toString();
    }

    public void setTxt_username(JTextField txt_username) {
        this.txt_username = txt_username;
    }

    public String getTxt_password() {
        return txt_password.getText().toString();
    }

    public void setTxt_password(JPasswordField txt_password) {
        this.txt_password = txt_password;
    }
}
