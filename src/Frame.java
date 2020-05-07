
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Frame extends JFrame {
	private ArrayList<Square> list;
	private JButton button_1, button_2;
	private JPanel panel_1, panel_2;
	
	public Frame(){
		list = new ArrayList<Square>();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		panel_1 = new JPanel();
		panel_2 = new JPanel();
		panel_1.setLayout(new GridLayout(3,3));
		
		button_1 = new JButton("Lösen");
		button_2 = new JButton("Reset");
		
		panel_2.add(button_1);
		panel_2.add(button_2);
		
		Square square;
		for(int i = 0; i < 9; i++){
			square = new Square();
			panel_1.add(square);
			list.add(square);
		}
		
		this.add(panel_1, BorderLayout.CENTER);
		this.add(panel_2, BorderLayout.PAGE_END);
		this.setVisible(true);
		this.setResizable(false);
		this.setSize(500, 500);
	}

	public ArrayList<Square> getList() {
		return list;
	}

	public JButton getButton_1() {
		return button_1;
	}

	public JButton getButton_2() {
		return button_2;
	}
	
	
	
}
