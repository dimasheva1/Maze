
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	
	
	public Menu()
	{
		
		play = new JButton("Play",new ImageIcon("img/play.png"));
		exit = new JButton("Exit ",new ImageIcon("img/exit.png"));
		contin = new JButton("Continue",new ImageIcon("img/continue.png"));
		about = new JButton("About",new ImageIcon("img/about.png"));
		MainPanel= new JPanel();
		
		play.addActionListener(this);
		exit.addActionListener(this);
		contin.addActionListener(this);
		
		play.setAlignmentX(Component.CENTER_ALIGNMENT);
		exit.setAlignmentX(Component.CENTER_ALIGNMENT);
		contin.setAlignmentX(Component.CENTER_ALIGNMENT);
		about.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		setLayout(new FlowLayout());
		
		JLabel picture = new JLabel("",new ImageIcon("img/logo.png"),JLabel.CENTER);
		
		
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
		menu.setSize(250,370);
		menu.setDefaultCloseOperation(EXIT_ON_CLOSE);
		menu.setResizable(false);
		menu.setVisible(true);
		
		menu.setLocation((ScreenSize.width-menu.getWidth())/2, (ScreenSize.height-menu.getHeight())/2);
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand()=="Play")
		{
		    podmenu = new PodMenu();
		    podmenu.setSize(200, 230);
		    podmenu.setDefaultCloseOperation(EXIT_ON_CLOSE);
		    podmenu.setResizable(false);
		    podmenu.setVisible(true);
		    podmenu.setLocation((ScreenSize.width-podmenu.getWidth())/2, (ScreenSize.height-podmenu.getHeight())/2);
		    this.setVisible(false);
			
			
		}
		if (e.getActionCommand()=="Exit ")
		{
			System.exit(0);
		}
		if (e.getActionCommand()=="Easy")
		{
			game = new Game(10);
			game.setSize(game.getSize());
			game.setLocation((ScreenSize.width-game.getWidth())/2, (ScreenSize.height-game.getHeight())/2);
			game.setDefaultCloseOperation(EXIT_ON_CLOSE);
			game.setVisible(true);
			game.setResizable(false);
			podmenu.setVisible(false);
		}
		if (e.getActionCommand()=="Hard")
		{
			game = new Game(20);
			game.setSize(game.getSize());
			game.setLocation((ScreenSize.width-game.getWidth())/2, 15);
			game.setDefaultCloseOperation(EXIT_ON_CLOSE);
			game.setVisible(true);
			game.setResizable(false);
			podmenu.setVisible(false);
		}
		if (e.getActionCommand()=="Back")
		{
			if (podmenu!=null) podmenu.setVisible(false);
			this.setVisible(true);
			this.setSize(250,370);
			this.setResizable(false);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setLocation((ScreenSize.width-getWidth())/2, (ScreenSize.height-getHeight())/2);
			
		}
		if (e.getActionCommand()=="Medium")
		{
			game = new Game(15);
			game.setSize(game.getSize());
			game.setLocation((ScreenSize.width-game.getWidth())/2, (ScreenSize.height-game.getHeight())/2);
			game.setDefaultCloseOperation(EXIT_ON_CLOSE);
			game.setVisible(true);
			game.setResizable(false);
			podmenu.setVisible(false);
		}
		if (e.getActionCommand()=="Continue")
		{
			game = new Game(Game.wall,Game.path);
			game.setSize(game.getSize());
			game.setLocation((ScreenSize.width-game.getWidth())/2, (ScreenSize.height-game.getHeight())/2);
			game.setDefaultCloseOperation(EXIT_ON_CLOSE);
			game.setVisible(true);
			game.setResizable(false);
			this.setVisible(false);
			this.dispose();
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
