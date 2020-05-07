import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class Field extends JTextField {
	
	
	public Field(int number){
		this.setText(String.valueOf(number));
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setFont(new Font("Arial", Font.BOLD,20));
		this.setEditable(true);
		this.setEnabled(true);
		
	}
}
