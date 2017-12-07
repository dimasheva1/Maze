import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class EndGame extends JFrame implements ActionListener{
	private JLabel win,choose;
	private JButton nextLevel,backToMenu;
	private static PodMenu podmenu;
	private static Menu menu;
	
	public EndGame()
	{
		this.setTitle("You Win");
		win = new JLabel("You are Win!!!");
		choose = new JLabel("Choose action:");
		nextLevel = new JButton("Go next level");
		backToMenu = new JButton("Back to menu");
		setLayout(new FlowLayout());
		add(win);
		add(choose);
		add(nextLevel);
		add(backToMenu);
		nextLevel.addActionListener(this);
		backToMenu.addActionListener(this);
		this.setResizable(false);
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getActionCommand()=="Go next level")
		{  
			if (menu.getPodMenu()!=null) podmenu = menu.getPodMenu();
			else {
				menu.setPodMenu(new PodMenu());
				podmenu=menu.getPodMenu();
			}
		    podmenu.setSize(200, 230);
		    podmenu.setResizable(false);
		    podmenu.setVisible(true);
		    podmenu.setDefaultCloseOperation(EXIT_ON_CLOSE);
		    podmenu.setLocation((Menu.ScreenSize.width-podmenu.getWidth())/2, (Menu.ScreenSize.height-podmenu.getHeight())/2);
		    this.setVisible(false);
		    this.dispose();
		}
		if (e.getActionCommand()=="Back to menu")
		{
			menu = new Menu();
			menu.setSize(250,370);
			menu.setResizable(false);
			menu.setDefaultCloseOperation(EXIT_ON_CLOSE);
			menu.setLocation((Menu.ScreenSize.width-menu.getWidth())/2, (Menu.ScreenSize.height-menu.getHeight())/2);
			menu.setVisible(true);
			this.setVisible(false);
			this.dispose();
		}
	}

}
