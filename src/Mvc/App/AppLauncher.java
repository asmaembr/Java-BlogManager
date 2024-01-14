package Mvc.App;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import Mvc.Vue.Frames.LoginView;
import models.*;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class AppLauncher {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginView::new);
    }





}
