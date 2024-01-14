package Mvc.Vue.Panels.CommentPanels;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CRUDCommentPanel extends JPanel  {
    private JButton btn_newElement, btn_update, btn_delete, btn_search;
    private JPanel centerPanel, eastPanel;
    private JTextField txt_search;

    private void initComponents(String newElementTxt, String updatelTxt,
                             String deleteTxt, String searchTxt,
                                ImageIcon saveIcon, ImageIcon updateIcon,
                                ImageIcon deleteIcon,ImageIcon searchIcon,
                                ImageIcon saveIconH, ImageIcon updateIconH,
                                ImageIcon deleteIconH, ImageIcon searchIconH,
                                Color textColor, Font font, Color bgColor){
        btn_newElement = new JButton(newElementTxt, saveIcon);
        btn_newElement.setHorizontalTextPosition(JButton.RIGHT);
        btn_newElement.setFont(font);
        btn_newElement.setHorizontalAlignment(JButton.CENTER);
        btn_newElement.setForeground(new Color(45, 83, 187));
        btn_newElement.setBorderPainted(false);
        btn_newElement.setBackground(null);
        btn_newElement.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn_newElement.setIcon(saveIconH);
                btn_newElement.setForeground(new Color(126, 154, 223));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn_newElement.setIcon(saveIcon);
                btn_newElement.setForeground(new Color(45, 83, 187));
                btn_newElement.setFocusable(false);
            }
        });


        btn_update = new JButton(updatelTxt, updateIcon);
        btn_update.setHorizontalTextPosition(JButton.RIGHT);
        btn_update.setFont(font);
        btn_update.setBackground(null);
        btn_update.setHorizontalAlignment(JButton.CENTER);
        btn_update.setForeground(new Color(65, 132, 19));
        btn_update.setBorderPainted(false);
        btn_update.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn_update.setIcon(updateIconH);
                btn_update.setForeground(new Color(100, 163, 59));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn_update.setIcon(updateIcon);
                btn_update.setForeground(new Color(65, 132, 19));
                btn_update.setFocusable(false);
            }
        });


        btn_delete = new JButton(deleteTxt, deleteIcon);
        btn_delete.setHorizontalTextPosition(JButton.RIGHT);
        btn_delete.setFont(font);
        btn_delete.setBackground(null);
        btn_delete.setHorizontalAlignment(JButton.CENTER);
        btn_delete.setForeground(new Color(248, 17, 60));
        btn_delete.setBorderPainted(false);
        btn_delete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn_delete.setIcon(deleteIconH);
                btn_delete.setForeground(new Color(247, 79, 112));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn_delete.setIcon(deleteIcon);
                btn_delete.setForeground(new Color(248, 17, 60));
                btn_delete.setFocusable(false);
            }
        });


        btn_search = new JButton(searchTxt, searchIcon);
        btn_search.setHorizontalTextPosition(JButton.RIGHT);
        btn_search.setFont(font);
        btn_search.setBackground(null);
        btn_search.setHorizontalAlignment(JButton.CENTER);
        btn_search.setForeground(new Color(137, 128, 128));
        btn_search.setBorderPainted(false);
        btn_search.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn_search.setIcon(searchIconH);
                btn_search.setForeground(new Color(223, 214, 214));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn_search.setIcon(searchIcon);
                btn_search.setForeground(new Color(137, 128, 128));
                btn_search.setFocusable(false);
            }
        });



        txt_search = new JTextField();
        txt_search.setForeground(textColor);
        txt_search.setFont(font);
        txt_search.setHorizontalAlignment(JTextField.CENTER);
        txt_search.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        txt_search.setPreferredSize(new Dimension(150, 50));

        centerPanel = new JPanel();
        centerPanel.setBackground(bgColor);
        centerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        centerPanel.add(btn_newElement);
        centerPanel.add(btn_update);
        centerPanel.add(btn_delete);


        eastPanel = new JPanel();
        eastPanel.setBackground(bgColor);
        eastPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        eastPanel.add(txt_search);
        eastPanel.add(btn_search);

    }


    public CRUDCommentPanel(String newElementTxt, String updatelTxt,
                            String deleteTxt, String searchTxt,
                            ImageIcon saveIcon, ImageIcon updateIcon,
                            ImageIcon deleteIcon, ImageIcon searchIcon,
                            ImageIcon saveIconH, ImageIcon updateIconH,
                            ImageIcon deleteIconH, ImageIcon searchIconH,
                            Color textColor, Font font, Color bgColor){
        initComponents(newElementTxt, updatelTxt, deleteTxt, searchTxt,
                saveIcon, updateIcon, deleteIcon, searchIcon,
                saveIconH, updateIconH, deleteIconH, searchIconH,
                textColor, font, bgColor);
        setBackground(bgColor);
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(15, 15, 15, 15));
        add(centerPanel, BorderLayout.WEST);
        add(eastPanel, BorderLayout.EAST);
    }


    public JButton getBtn_newElement() {
        return btn_newElement;
    }

    public void setBtn_newElement(JButton btn_newElement) {
        this.btn_newElement = btn_newElement;
    }

    public JButton getBtn_update() {
        return btn_update;
    }

    public void setBtn_update(JButton btn_update) {
        this.btn_update = btn_update;
    }

    public JButton getBtn_delete() {
        return btn_delete;
    }

    public void setBtn_delete(JButton btn_delete) {
        this.btn_delete = btn_delete;
    }

    public JButton getBtn_search() {
        return btn_search;
    }

    public void setBtn_search(JButton btn_search) {
        this.btn_search = btn_search;
    }

    public JTextField getTxt_search() {
        return txt_search;
    }

    public void setTxt_search(JTextField txt_search) {
        this.txt_search = txt_search;
    }
}
