package Mvc.Vue.Frames;


import Mvc.Vue.Events.Events;
import Mvc.Vue.Panels.ArticleDashboardPanels.TableArticlePanel;
import Mvc.Vue.Panels.FooterPanel;
import Mvc.Vue.Panels.HeaderPanel;
import Mvc.Vue.utils.themes.Theme;
import dao.ArticleDAO;

import javax.swing.*;
import java.awt.*;

public class ArticleDasboardView extends JFrame {
    private Container container;

    private HeaderPanel Header;
    private FooterPanel Footer;

    private TableArticlePanel Center;



    private void initPanels(){
        Events e = new Events(this);
        Center = new TableArticlePanel(new ArticleDAO());
        Header = new HeaderPanel(Theme.FormPanelBgColor);
        Footer = new FooterPanel(Theme.FormPanelBgColor);
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
    public ArticleDasboardView(){

        initContainer();
        setUndecorated(true);
        setTitle(" MY ARTICLES ");
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

    public TableArticlePanel getCenter() {
        return Center;
    }

    public void setCenter(TableArticlePanel center) {
        Center = center;
    }



}
