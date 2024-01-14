package Mvc.Vue.Frames;


import Mvc.Vue.Events.Events;
import Mvc.Vue.Panels.LoginFormPanel;
import Mvc.Vue.Panels.ButtonsPanel;
import Mvc.Vue.Panels.TitlePanel;
import Mvc.Vue.utils.themes.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginView extends JFrame {
    private Container mainContainer;
    private TitlePanel titlePanel;
    private ButtonsPanel buttonsPanel;
    private LoginFormPanel formPanel;

    private void initPanels(){

        Events events = new Events(this);
        titlePanel = new TitlePanel("L O G I N", Theme.titlesTextColor,Theme.titlesFont,Theme.framesBgColor,Theme.icon_logo);
        buttonsPanel = new ButtonsPanel("","",Theme.icon_login,Theme.icon_exit,Theme.titlesTextColor,Theme.textFont,null);
        buttonsPanel.getBtn1().addActionListener(e -> events.login(e,getFormPanel().getTxt_username().toString(),getFormPanel().getTxt_password().toString()));
        buttonsPanel.getBtn2().addActionListener(events::loginCancel);
        formPanel = new LoginFormPanel(Theme.titlesTextColor,Theme.textFont,null,Theme.FormPanelBgColor,"Username : ","Password : ");
        formPanel.setBorder(new EmptyBorder(50, 30, 100, 30));

    }
    private void initContainer(){
        initPanels();
        mainContainer = this.getContentPane();
        mainContainer.setBackground(Theme.framesBgColor);
        mainContainer.setLayout(new BorderLayout());
        mainContainer.add(titlePanel, BorderLayout.NORTH);
        mainContainer.add(buttonsPanel, BorderLayout.SOUTH);
        mainContainer.add(formPanel, BorderLayout.CENTER);
    }

    public LoginView(){

        initContainer();
        setAlwaysOnTop(false);
        setUndecorated(true);
        setSize(400, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }



    public LoginFormPanel getFormPanel() {
        return formPanel;
    }

    public void setFormPanel(LoginFormPanel formPanel) {
        this.formPanel = formPanel;
    }
}
