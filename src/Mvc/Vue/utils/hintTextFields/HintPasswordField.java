package Mvc.Vue.utils.hintTextFields;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class HintPasswordField extends JPasswordField {
    Font gainFont = new Font("Optima", Font.BOLD, 17);
    Font lostFont = new Font("Optima", Font.PLAIN, 14);
    Color gainColor = new Color(186, 85, 211);
    Color lostColor = Color.GRAY;
    String hint;
    JLabel placeholderLabel;

    public HintPasswordField(final String hint) {
        this.hint = hint;
        setFont(lostFont);
        setForeground(lostColor);
        setHorizontalAlignment(JTextField.CENTER);
        setBorder(BorderFactory.createLineBorder(Color.black, 2));
        // Créer un label pour le placeholder
        placeholderLabel = new JLabel(hint);
        placeholderLabel.setFont(lostFont);
        placeholderLabel.setBorder(new EmptyBorder(0,15,0,15));
        placeholderLabel.setForeground(lostColor);
        // Ajouter un écouteur de focus pour afficher/masquer le placeholder
          addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                placeholderLabel.setVisible(false);
                    setText(getText());
                    setFont(gainFont);
                    setForeground(gainColor);
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (getPassword().length == 0)
                    placeholderLabel.setVisible(true);
                else {
                    placeholderLabel.setVisible(false);
                    setText(getText());
                    setFont(gainFont);
                    setForeground(gainColor);
                     }
                                               }});
        // Placer le label à l'intérieur du champ de mot de passe
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(placeholderLabel);
    }
    public JLabel getPlaceholderLabel() {
        return placeholderLabel;
    }

    @Override
    public void setText(String t) {
        setFont(gainFont);
        setForeground(gainColor);
        super.setText(t);
    }
}
