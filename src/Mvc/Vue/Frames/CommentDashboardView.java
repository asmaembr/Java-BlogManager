package Mvc.Vue.Frames;

import Mvc.Vue.Events.Events;
import Mvc.Vue.Panels.CommentPanels.TableCommentPanel;
import Mvc.Vue.Panels.FooterPanel;
import Mvc.Vue.Panels.HeaderPanel;
import Mvc.Vue.utils.themes.Theme;
import dao.CommentaireDAO;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CommentDashboardView extends JFrame {
    private Container container;

    private HeaderPanel Header;
    private FooterPanel Footer;

    private TableCommentPanel Center;


    private void initPanels() {
        Events e = new Events(this);
        Header = new HeaderPanel(Theme.FormPanelBgColor);
        Footer = new FooterPanel(Theme.FormPanelBgColor);
        Center = new TableCommentPanel(new CommentaireDAO());
        Header.getBtn1().addActionListener(e::BlogDasboard);
        Header.getBtn2().addActionListener(e::ArticleView);
        Header.getBtn3().addActionListener(e::CommentDashboard);
        Footer.getRetour().addActionListener(e::loginCancel);

    }

    private void initContainer() {
        initPanels();
        container = this.getContentPane();
        container.setBackground(Theme.FormPanelBgColor);
        container.setLayout(new BorderLayout());
        container.add(Header, BorderLayout.NORTH);
        container.add(Footer, BorderLayout.SOUTH);
        container.add(Center, BorderLayout.CENTER);
    }

    public CommentDashboardView() {
        setUndecorated(true);
        initContainer();
        setTitle(" MY COMMENTS ");
        setLocation(0, 0);
        setSize(1000, 700);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public HeaderPanel getHeader() {
        return Header;
    }

    public void setHeader(HeaderPanel header) {
        Header = header;
    }

    public FooterPanel getFooter() {
        return Footer;
    }

    public void setFooter(FooterPanel footer) {
        Footer = footer;
    }

    public TableCommentPanel getCenter() {
        return Center;
    }

    public void setCenter(TableCommentPanel center) {
        Center = center;
    }
}