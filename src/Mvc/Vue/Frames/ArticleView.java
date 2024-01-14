package Mvc.Vue.Frames;


import Mvc.Vue.Events.Events;
import Mvc.Vue.Panels.ArticlePanels.ArticleCardsPanel;
import Mvc.Vue.Panels.ButtonsPanel;
import Mvc.Vue.Panels.FooterPanel;
import Mvc.Vue.Panels.HeaderPanel;
import Mvc.Vue.utils.themes.Theme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArticleView extends JFrame {
    private Container container;

    private HeaderPanel Header;

private FooterPanel Footer;
    private ArticleCardsPanel Center;
    private ButtonsPanel Cards;




    private void initPanels(){
        Events events = new Events(this);
        Center = new ArticleCardsPanel(Theme.framesBgColor);
        Center.getPanels().forEach(f->f.getManage().addActionListener(events::ArticleDashboardView));
        Header = new HeaderPanel(Theme.FormPanelBgColor);
        Footer = new FooterPanel(Theme.FormPanelBgColor);
        Footer.getRetour().addActionListener(events::loginCancel);
        Header.getBtn1().addActionListener(events::BlogDasboard);
        Header.getBtn2().addActionListener(events::ArticleView);
        Header.getBtn3().addActionListener(events::CommentDashboard);
        Cards = new ButtonsPanel("","",Theme.icon_back,Theme.icon_next,Theme.FormPanelBgColor,Theme.textFont,Theme.framesBgColor);
        Cards.setLayout(new GridLayout(1,3));
        Cards.getBtn1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Center.getCardLayout().previous(Center);
            }
        });

        Cards.getBtn2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Center.getCardLayout().next(Center);
            }
        });

    }
    private  void initbuttons(){
        Events e=new Events(this);
        initPanels();
        Cards.getBtn1().setFocusable(false);
        Cards.getBtn2().setFocusable(false);
    }

    private JPanel panelCenter(){
        var panel= new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBackground(Theme.FormPanelBgColor);
        panel.add(Center);
        panel.add(Cards);
        return panel;
    }
    private void initContainer(){
        initbuttons();
        container = this.getContentPane();
        container.setBackground(Theme.FormPanelBgColor);
        container.setLayout(new BorderLayout());
        container.add(Header,BorderLayout.NORTH);
        container.add(panelCenter(),BorderLayout.CENTER);
        container.add(Footer,BorderLayout.SOUTH);

    }
    public ArticleView(){
        initContainer();
        setUndecorated(true);
        setTitle(" MY ARTICLES ");
        setLocation(0,0);
        setSize(1000,700);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);



    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ArticleView::new);
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

    public ButtonsPanel getFooter() {
        return Cards;
    }

    public void setFooter(ButtonsPanel footer) {
        Cards = footer;
    }

    public void setCenter(ArticleCardsPanel center) {
        Center = center;
    }

    public ArticleCardsPanel getCenter() {
        return Center;
    }
}
