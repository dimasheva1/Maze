
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PodMenu extends JFrame{
	private JButton easy,hard,back,medium;
	private JPanel ChoosePanel;
	private JLabel choose;
	private static  Menu menu = new Menu();
	
	public PodMenu()
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		ChoosePanel=new JPanel(new GridLayout(4,1));
		
		easy = new JButton("Easy");
		medium = new JButton("Medium");
		hard = new JButton("Hard");
		back = new JButton("Back");
		
		easy.setAlignmentX(Component.CENTER_ALIGNMENT);
		hard.setAlignmentX(Component.CENTER_ALIGNMENT);
		back.setAlignmentX(Component.CENTER_ALIGNMENT);
		medium.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		choose=new JLabel("Choose complexity:");
		choose.setHorizontalAlignment(getWidth()/2);
		
		setLayout(new GridLayout(2,1));
		
		ChoosePanel.setLayout(new BoxLayout(ChoosePanel, BoxLayout.Y_AXIS));
		
		
		ChoosePanel.add(easy);
		ChoosePanel.add(medium);
		ChoosePanel.add(hard);
		ChoosePanel.add(back);
		
		add(choose);
		add(ChoosePanel);
		
		easy.addActionListener(menu);
		hard.addActionListener(menu);
		back.addActionListener(menu);
		medium.addActionListener(menu);
		
	}
	

}
