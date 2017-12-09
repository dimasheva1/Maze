import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class EndGame extends JFrame implements ActionListener{
	private JLabel winOrlose,choose;
	private JButton nextLevel,backToMenu;
	private static PodMenu podmenu;
	private static Menu menu;
	private Game game;
	
	public EndGame(int time, int time1) 
	{
		//if (checkWin)
		//{
		setSize(200, 180);
		this.setTitle("You Win");
		winOrlose = new JLabel("You are Win!!!");
		JLabel win1 = new JLabel("Ваше время - " + String.format("%02d:%02d", (time-time1) / 100, (time-time1) % 100));
		JLabel win2 = new JLabel("Время на уровень - " + String.format("%02d:%02d", time / 100, time % 100));
		choose = new JLabel("Choose action:");
		nextLevel = new JButton("Go next level");
		backToMenu = new JButton("Back to menu");
		setLayout(new FlowLayout());
		
		add(winOrlose);
		add(win1);
		add(win2);
		add(choose);
		add(nextLevel);
		add(backToMenu);
		
		nextLevel.addActionListener(this);
		backToMenu.addActionListener(this);
		//}
		//else
		
		
		
		this.setResizable(false);
	}
	
	public EndGame(File wall1,File path1)
	{
		setSize(200, 200);
		//this.game=game;
		this.setTitle("You Lose");
		winOrlose = new JLabel("You are Lose =( =( =(");
		choose = new JLabel("Choose action:     ");
		nextLevel = new JButton("Go next level");
		backToMenu = new JButton("Back to menu");
		JButton restart = new JButton("Restart");
		setLayout(new FlowLayout());
		
		add(winOrlose);
		add(choose);
		add(restart);
		add(nextLevel);
		add(backToMenu);
		
		nextLevel.addActionListener(this);
		backToMenu.addActionListener(this);
		restart.addActionListener(this);
		
		//this.wall1=wall1;
		//this.path1=path1;
		
		//game.prepareToStart();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getActionCommand()=="Go next level")
		{  
			if (Menu.getPodMenu()!=null) podmenu = Menu.getPodMenu();
			else {
				Menu.setPodMenu(new PodMenu());
				podmenu=Menu.getPodMenu();
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
		if (e.getActionCommand()=="Restart")
		{
			game = new Game(Game.wall1,Game.path1);
			game.setResizable(false);
			game.setSize(game.getSize());
			game.setDefaultCloseOperation(EXIT_ON_CLOSE);
			game.setLocation((Menu.ScreenSize.width-game.getWidth())/2, (Menu.ScreenSize.height-game.getHeight())/2);
			game.setVisible(true);
			this.setVisible(false);
			this.dispose();
		}
	}

}
