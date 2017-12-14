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
		setSize(200, 180);
		this.setTitle("You Win");
		winOrlose = new JLabel("�� ��������!!!");
		JLabel win1 = new JLabel("���� ����� - " + String.format("%02d:%02d", (time-time1) / 100, (time-time1) % 100));
		JLabel win2 = new JLabel("����� �� ������� - " + String.format("%02d:%02d", time / 100, time % 100));
		choose = new JLabel("�������� ��������:");
		nextLevel = new JButton("��������� �������");
		backToMenu = new JButton("����� � ����");
		setLayout(new FlowLayout());
		
		add(winOrlose);
		add(win1);
		add(win2);
		add(choose);
		add(nextLevel);
		add(backToMenu);
		
		nextLevel.addActionListener(this);
		backToMenu.addActionListener(this);
		
		
		
		this.setResizable(false);
	}
	
	public EndGame(File wall1,File path1)
	{
		setSize(180, 180);
		//this.game=game;
		this.setTitle("You Lose");
		winOrlose = new JLabel("�� ��������� =( =( =(");
		choose = new JLabel("�������� ��������:     ");
		nextLevel = new JButton("��������� �������");
		backToMenu = new JButton("����� � ����");
		JButton restart = new JButton("����������");
		setLayout(new FlowLayout());
		
		add(winOrlose);
		add(choose);
		add(restart);
		add(nextLevel);
		add(backToMenu);
		
		nextLevel.addActionListener(this);
		backToMenu.addActionListener(this);
		restart.addActionListener(this);
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getActionCommand()=="��������� �������")
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
		if (e.getActionCommand()=="����� � ����")
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
		if (e.getActionCommand()=="����������")
		{
			game = new Game(Game.wall1,Game.path1);
			game.setResizable(false);
			game.setSize(game.getSize());
			game.setDefaultCloseOperation(EXIT_ON_CLOSE);
			game.setVisible(true);
			this.setVisible(false);
			this.dispose();
		}
	}

}
