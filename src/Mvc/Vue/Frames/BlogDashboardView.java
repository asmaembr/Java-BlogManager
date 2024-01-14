package Mvc.Vue.Frames;

import Mvc.Vue.Events.Events;
import Mvc.Vue.Panels.*;
import Mvc.Vue.Panels.BlogDashboardPanels.TableBlogPanel;
import Mvc.Vue.utils.themes.Theme;
import dao.BlogDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlogDashboardView extends JFrame {
    private Container container;

    private HeaderPanel Header;
    private FooterPanel Footer;

    private TableBlogPanel Center;



    private void initPanels(){
        Events e = new Events(this);
    Header = new HeaderPanel(Theme.FormPanelBgColor);
    Footer = new FooterPanel(Theme.FormPanelBgColor);
    Center = new TableBlogPanel(new BlogDAO());
        Header.getBtn1().addActionListener(e::BlogDasboard);
        Header.getBtn2().addActionListener(e::ArticleView);
        Header.getBtn3().addActionListener(e::CommentDashboard);
        Footer.getRetour().addActionListener(e::loginCancel);

    }

    private void initContainer(){
        initPanels();
        container = this.getContentPane();
        container.setBackground(Theme.FormPanelBgColor);
        container.setLayout(new BorderLayout());
        container.add(Header,BorderLayout.NORTH);
        container.add(Footer,BorderLayout.SOUTH);
        container.add(Center,BorderLayout.CENTER);
    }
    public BlogDashboardView(){

        setUndecorated(true);
        initContainer();
        setTitle(" MY BLOGS ");
        setLocation(0,0);
        setSize(1000,700);
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

    public TableBlogPanel getCenter() {
        return Center;
    }

    public void setCenter(TableBlogPanel center) {
        Center = center;
    }


}
