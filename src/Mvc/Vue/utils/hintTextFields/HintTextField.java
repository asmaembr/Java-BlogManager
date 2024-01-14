package Mvc.Vue.utils.hintTextFields;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class HintTextField extends JTextField {
	Font gainFont = new Font("Optima", Font.BOLD, 17);
	Font lostFont = new Font("Optima", Font.PLAIN, 14);
	Color gainColor = new Color(186, 85, 211);
	Color lostColor = Color.GRAY;
	String hint;
	   
	   public HintTextField(final String hint) {
		   this.hint = hint;
		   setText(hint);
		   setFont(lostFont);
		   setForeground(lostColor);
		   setHorizontalAlignment(JTextField.CENTER);
		   setBorder(BorderFactory.createLineBorder(Color.black, 2));

		   addFocusListener(new FocusAdapter() {
			   @Override
			   public void focusGained(FocusEvent e) {
				   if (getText().equals(hint)) {
					   setText("");
					   setFont(gainFont);
					   setForeground(lostColor);
				   } else {
					   setText(getText());
					   setFont(gainFont);
					   setForeground(gainColor);
				   }
			   }

			   @Override
			   public void focusLost(FocusEvent e) {
				   if (getText().equals(hint)|| getText().length()==0) {
					   setText(hint);
					   setFont(lostFont);
					   setForeground(lostColor);
				   } else {
					   setText(getText());
					   setFont(gainFont);
					   setForeground(gainColor);
				   }
			   }
		   });


	   
	   }

	@Override
	public void setFocusable(boolean focusable) {
		if (getText().equals(hint)|| getText().trim().length()==0) {
			setText(hint);
			setFont(lostFont);
			//setForeground(Color.GRAY);
			setForeground(new Color(186, 85, 211));
		}
		super.setFocusable(focusable);
	}

	@Override
	public void setText(String t) {
		setFont(gainFont);
		setForeground(gainColor);
		super.setText(t);
	}
}
