
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JFrame implements ActionListener{
	private JButton play,exit,contin,about;
	private Game game;
	private JPanel MainPanel;
	public final static Dimension ScreenSize= Toolkit.getDefaultToolkit ().getScreenSize ();
    private static PodMenu podmenu;
    private static About a;

	
	
	public Menu()
	{
		
		play = new JButton("Играть",new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("play.png"))));
		exit = new JButton("Выход ",new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("exit.png"))));
		contin = new JButton("Продлжить",new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("continue.png"))));
		about = new JButton("Про игру",new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("about.png"))));
		MainPanel= new JPanel();
		
		play.addActionListener(this);
		exit.addActionListener(this);
		contin.addActionListener(this);
		about.addActionListener(this);
		
		play.setAlignmentX(Component.CENTER_ALIGNMENT);
		exit.setAlignmentX(Component.CENTER_ALIGNMENT);
		contin.setAlignmentX(Component.CENTER_ALIGNMENT);
		about.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		setLayout(new FlowLayout());
		
		JLabel picture = new JLabel("",new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png"))),JLabel.CENTER);
		
		
		MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));
		MainPanel.add(play);
		MainPanel.add(contin);
		MainPanel.add(about);
		MainPanel.add(exit);
		
		add(picture);
		add(MainPanel);
	}
	
	public static void main(String args[])
	{
		Menu menu = new Menu();
		menu.setTitle("Maze");
		menu.setSize(250,370);
		menu.setDefaultCloseOperation(EXIT_ON_CLOSE);
		menu.setResizable(false);
		menu.setVisible(true);
		
		menu.setLocation((ScreenSize.width-menu.getWidth())/2, (ScreenSize.height-menu.getHeight())/2);
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand()=="Играть")
		{
		    podmenu = new PodMenu();
		    podmenu.setSize(200, 230);
		    podmenu.setDefaultCloseOperation(EXIT_ON_CLOSE);
		    podmenu.setResizable(false);
		    podmenu.setVisible(true);
		    podmenu.setLocation((ScreenSize.width-podmenu.getWidth())/2, (ScreenSize.height-podmenu.getHeight())/2);
		    this.setVisible(false);
			
			
		}
		if (e.getActionCommand()=="Выход ")
		{
			System.exit(0);
		}
		if (e.getActionCommand()=="Легко")
		{
			game = new Game(10);
			game.setSize(game.getSize());
			game.setLocation((ScreenSize.width-game.getWidth())/2, (ScreenSize.height-game.getHeight())/2);
			game.setDefaultCloseOperation(EXIT_ON_CLOSE);
			game.setVisible(true);
			game.setResizable(false);
			podmenu.setVisible(false);
		}
		if (e.getActionCommand()=="Сложно")
		{
			game = new Game(20);
			game.setSize(game.getSize());
			game.setLocation((ScreenSize.width-game.getWidth())/2, 15);
			game.setDefaultCloseOperation(EXIT_ON_CLOSE);
			game.setVisible(true);
			game.setResizable(false);
			podmenu.setVisible(false);
		}
		if (e.getActionCommand()=="Назад")
		{
			if (podmenu!=null) podmenu.setVisible(false);
			this.setVisible(true);
			this.setSize(250,370);
			this.setResizable(false);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setLocation((ScreenSize.width-getWidth())/2, (ScreenSize.height-getHeight())/2);
			
		}
		if (e.getActionCommand()=="Назад ")
		{
			a.setVisible(false);
			this.setVisible(true);
			this.setSize(250,370);
			this.setResizable(false);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setLocation((ScreenSize.width-getWidth())/2, (ScreenSize.height-getHeight())/2);
			a.dispose();
			
		}
		if (e.getActionCommand()=="Средне")
		{
			game = new Game(15);
			game.setSize(game.getSize());
			game.setLocation((ScreenSize.width-game.getWidth())/2, (ScreenSize.height-game.getHeight())/2);
			game.setDefaultCloseOperation(EXIT_ON_CLOSE);
			game.setVisible(true);
			game.setResizable(false);
			podmenu.setVisible(false);
		}
		if (e.getActionCommand()=="Продлжить")
		{
			game = new Game(Game.wall,Game.path);
			game.setSize(game.getSize());
			game.setDefaultCloseOperation(EXIT_ON_CLOSE);
			game.setVisible(true);
			game.setResizable(false);
			this.setVisible(false);
			this.dispose();
		}
		if (e.getActionCommand()=="Про игру")
		{
			a = new About();
			a.setSize(500, 300);
			a.setDefaultCloseOperation(EXIT_ON_CLOSE);
			a.setResizable(false);
			a.setLocation((ScreenSize.width-a.getWidth())/2, (ScreenSize.height-a.getHeight())/2);
			a.setVisible(true);
			this.setVisible(false);
		}
		
	}
	
	public static PodMenu getPodMenu()
	{
		if (podmenu==null) return null;
		else return podmenu;
	}
	
	public static void setPodMenu(PodMenu pm)
	{
		podmenu = pm;
	}
	

}
