import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class Console{
	private JFrame frame;
	private JTextArea console;
	private JScrollPane scrollbar;
	
	public Console(){
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(500,500);
		frame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - frame.getWidth(), 0);
		console = new JTextArea();
		console.setEditable(false);
		console.setLineWrap(true);
		console.setWrapStyleWord(true);
		scrollbar = new JScrollPane(console);
		frame.add(scrollbar);
	}
	
	public void setText(String text){
		console.append(text + "\n");
	}
}
