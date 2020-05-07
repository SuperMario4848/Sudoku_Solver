import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


public class Square extends JPanel {
	private ArrayList<Field> list;

	public Square(){
		list = new ArrayList<Field>();
		this.setLayout(new GridLayout(3,3));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		Field field;
		for(int i = 0; i < 9; i++){
			field = new Field(0);
			this.add(field);
			list.add(field);		
		}
	}
	
	public ArrayList<Field> getList() {
		return list;
	}
	
}
