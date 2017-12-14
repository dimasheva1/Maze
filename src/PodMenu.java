
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PodMenu extends JFrame{
	private JButton easy,hard,back,medium;
	private JPanel ChoosePanel,panel;
	private JLabel choose;
	private static  Menu menu = new Menu();
	
	public PodMenu()
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		ChoosePanel=new JPanel();
		panel=new JPanel();
		
		easy = new JButton("Легко",new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("easy.png"))));
		medium = new JButton("Средне",new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("medium.png"))));
		hard = new JButton("Сложно",new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("hard.png"))));
		back = new JButton("Назад");
		
		easy.setAlignmentX(Component.CENTER_ALIGNMENT);
		hard.setAlignmentX(Component.CENTER_ALIGNMENT);
		back.setAlignmentX(Component.CENTER_ALIGNMENT);
		medium.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		choose=new JLabel("Выберите сложность:");
		choose.setFont(new Font("Dialog", Font.ITALIC, 16));
		choose.setHorizontalAlignment(getWidth()/2);
		panel.add(choose);
		
		setLayout(new FlowLayout());
		
		ChoosePanel.setLayout(new BoxLayout(ChoosePanel, BoxLayout.Y_AXIS));
		
		
		ChoosePanel.add(easy);
		ChoosePanel.add(medium);
		ChoosePanel.add(hard);
		ChoosePanel.add(back);
		
		add(panel);
		add(ChoosePanel);
		
		easy.addActionListener(menu);
		hard.addActionListener(menu);
		back.addActionListener(menu);
		medium.addActionListener(menu);
		
	}
	

}
