
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
	private JButton play,exit,contin;
	private Game game;
	private JPanel MainPanel;
	public final static Dimension ScreenSize= Toolkit.getDefaultToolkit ().getScreenSize ();
    static PodMenu podmenu;

	
	
	public Menu()
	{
		play = new JButton("Play");
		exit = new JButton("Exit ");
		contin = new JButton("Continue");
		MainPanel= new JPanel();
		
		play.addActionListener(this);
		exit.addActionListener(this);
		contin.addActionListener(this);
		
		play.setAlignmentX(Component.CENTER_ALIGNMENT);
		exit.setAlignmentX(Component.CENTER_ALIGNMENT);
		contin.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		setLayout(new FlowLayout());
		
		JLabel picture = new JLabel("",new ImageIcon("images.jpg"),JLabel.CENTER);
		
		
		MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));
		MainPanel.add(play);
		//JLabel a= new JLabel(" ");
		//MainPanel.add(a);
		MainPanel.add(contin);
		//MainPanel.add(a);
		MainPanel.add(exit);
		
		add(picture);
		add(MainPanel);
		
		//MainPanel.setVisible(true);
		/*ChoosePanel=new JPanel(new GridLayout(4,1));
		easy = new JButton("Easy");
		hard = new JButton("Hard");
		back = new JButton("Back");
		choose=new JLabel("Choose complexity:");
		choose.setHorizontalAlignment(getWidth()/2);
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel5 = new JPanel();
		panel3.add(easy);
		panel4.add(hard);
		panel5.add(back);
		ChoosePanel.add(choose);
		ChoosePanel.add(panel3);
		ChoosePanel.add(panel4);
		ChoosePanel.add(panel5);
		add(ChoosePanel,BorderLayout.CENTER);
		//ChoosePanel.setVisible(false);*/
		
		
		
		
	}
	
	public static void main(String args[])
	{
		Menu menu = new Menu();
		menu.setSize(290,350);
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
			/*game = new Game();
			game.setSize(game.getSize());
			game.setLocation((ScreenSize.width-game.getWidth())/2, (ScreenSize.height-game.getHeight())/2);
			game.setVisible(true);
			game.setResizable(false);*/
		    podmenu = new PodMenu();
		    podmenu.setSize(200, 300);
		    podmenu.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
			game.setLocation((ScreenSize.width-game.getWidth())/2, (ScreenSize.height-game.getHeight())/2);
			game.setDefaultCloseOperation(EXIT_ON_CLOSE);
			game.setVisible(true);
			game.setResizable(false);
			podmenu.setVisible(false);
		}
		if (e.getActionCommand()=="Back")
		{
			podmenu.setVisible(false);
			this.setVisible(true);
			this.setSize(290,350);
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
		}
		
	}
	
	public static PodMenu getPodMenu()
	{
		return podmenu;
	}
	

}
