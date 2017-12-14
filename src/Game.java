
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
	import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.Timer;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Game extends JFrame implements KeyListener{
	 	private EndGame eg;
	    private MapPanel mappanel;
	    private TimerLabel timerLabel;
	    private int time;
		private volatile static boolean working;	
		private boolean released=true;
		private boolean first=false;// ��� ������ ������
		private volatile boolean exit;
		private boolean firstcheck;//��� ������� ������
		
		private  Sound sound = new Sound(getClass().getResourceAsStream("1.wav"));
		private  Sound sound1 = new Sound(getClass().getResourceAsStream("2.wav"));	
		public static File wall = new File("wall.txt");
	    public static File path = new File("path.txt");
	    public static File wall1 = new File("wall1.txt");
	    public static File path1 = new File("path1.txt");
	    Thread player = new Thread(new Runnable()
	    {
			public void run()
			{
				while(true)
				{
					synchronized(Thread.currentThread())
					{
					if (exit) 
						{try {
							Thread.currentThread().wait();
					     	} catch (InterruptedException e) {}
						}
					}
				if (!mappanel.movePlayer(released))
				{
					sound.stop();
					sound1.stop();
					exit=true;
					eg = new EndGame(time, timerLabel.getTime()); 
					eg.setDefaultCloseOperation(EXIT_ON_CLOSE);
				    eg.setLocation((Menu.ScreenSize.width-eg.getWidth())/2, (Menu.ScreenSize.height-eg.getHeight())/2);
				    eg.setVisible(true);
				    setVisible(false);
					dispose();	
					pause();
					
				}
			}}
	    }); 
		
		
		Thread checkPlayMusic = new Thread(new Runnable()
				{
			      public void run()
			      {
			    	  long time1 = 40000;
			    	  while(true)
			    	  {
			    	     try 
			    	     {
			    	    	 Thread.sleep(time1);
			    	    	 synchronized(Thread.currentThread())
			    	    	 {
						    if(exit)					// ������ ��������� ������ ����� ������
					    	   { 
						          Thread.currentThread().wait();
					    	   }
			    	    	 }
					     } catch (InterruptedException e) {} 
			    	   if (!first) 
			    		{
			    		 sound.stop();
			    		 time1 = 33000;
			    		 first=true;
			    		}
			    	     
			    	  sound1.setVolume(sound.getVolume());
			    	  sound1.play();
			    	   }  	  
			      }
				});
		
		Thread checkLose = new Thread(new Runnable()
				{
					public void run()
					{
						try {
							Thread.sleep(time*10);
						} catch (InterruptedException e) {}
						if(!exit)
						{
						lose();
						timerLabel.stop();
						}
					}
				}
				);
		
		public Game(int size)
		{
			exit=false;
			firstcheck=false;
			working=false;
			startMusic();
			mappanel = new MapPanel(size);
			if (size==10) time = mappanel.getKolCell()*22;
			else if(size==15) time = mappanel.getKolCell()*25;
			else if (size==20) time = mappanel.getKolCell()*28;
			timerLabel=new TimerLabel(new Timer(),time);
			
			timerLabel.setFont(new Font(timerLabel.getFont().getFontName(), timerLabel.getFont().getStyle(), 20));
			timerLabel.setText("�������� ������� - " + String.format("%02d:%02d", time/100, time%100));
				
			JPanel panel1 = new JPanel();
			panel1.setBackground(Color.LIGHT_GRAY);
			panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
			panel1.add(mappanel);
			panel1.add(timerLabel);
			
			System.out.println("12");
			add(panel1);
			addKeyListener(this);
			
			
			
			player.start();
			
		}
		
		public Game(File wall,File path)
		{
			working=false;
			exit=false;
			firstcheck=false;
			sound.setVolume((float) 0.7);
			
			sound.play();
			checkPlayMusic.start();
			mappanel=new MapPanel(wall,path);
			if (mappanel.getSizeMap()==21)
				{
				time = mappanel.getKolCell()*22;
				setLocation((Menu.ScreenSize.width-getSize().width)/2, (Menu.ScreenSize.height-getSize().height)/2);
				}
			else if(mappanel.getSizeMap()==31)
				{
				time = mappanel.getKolCell()*25;
				setLocation((Menu.ScreenSize.width-getSize().width)/2, (Menu.ScreenSize.height-getSize().height)/2);
				}
			else if (mappanel.getSizeMap()==41)
				{
				time = mappanel.getKolCell()*28;
				setLocation((Menu.ScreenSize.width-getSize().width)/2, 15);
				}
			timerLabel=new TimerLabel(new Timer(),time);
			
			timerLabel.setFont(new Font(timerLabel.getFont().getFontName(), timerLabel.getFont().getStyle(), 20));
			timerLabel.setText("�������� ������� - " + String.format("%02d:%02d", time/100, time%100));
				
			JPanel panel1 = new JPanel();
			panel1.setBackground(Color.LIGHT_GRAY);
			panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
			panel1.add(mappanel);
			panel1.add(timerLabel);
			
			
			add(panel1);
			addKeyListener(this);
			
			
			player.start();
		}
		
		
		
		
		public Dimension getSize()
		{
			return mappanel.getSize();
		}
   
		
		public void keyPressed(KeyEvent ke) {
			if (working==false)
			{
				
				
				if (ke.getKeyCode() == KeyEvent.VK_RIGHT)
				{
					mappanel.setK(1);
					released=false;
					synchronized(player)
					{
						player.notify();
					}
				
				}
				if (ke.getKeyCode()==KeyEvent.VK_DOWN)
				{
					System.out.println("11");
					if (!firstcheck)
					{	
					checkLose.start();
					timerLabel.start();
					firstcheck=true;
					}
					mappanel.setK(4);					
					released=false;
					synchronized(player)
					{
						
						player.notify();
					}		
				}
				if (ke.getKeyCode()==KeyEvent.VK_LEFT)
				{
					mappanel.setK(2);
					released=false;
					synchronized(player)
					{
						
						player.notify();
					}
				}
				if (ke.getKeyCode()==KeyEvent.VK_UP)
				{
					mappanel.setK(3);
					released=false;
					synchronized(player)
					{
						
						player.notify();
					}
				}
				if (ke.getKeyCode()==KeyEvent.VK_ESCAPE)
				{
					mappanel.writeFile(wall, path);
					exit=true;
					sound.stop();
					sound1.stop();
					timerLabel.stop();
					Menu menu = new Menu();
					menu.setSize(250,370);
					menu.setResizable(false);
					menu.setDefaultCloseOperation(EXIT_ON_CLOSE);
					menu.setLocation((Menu.ScreenSize.width-menu.getWidth())/2, (Menu.ScreenSize.height-menu.getHeight())/2);
					menu.setVisible(true);
					setVisible(false);
					dispose();
                    
				}
			}
		}


		@Override
		public void keyReleased(KeyEvent ke) 
		{
			if (ke.getKeyCode() == KeyEvent.VK_SHIFT) 
				if (first) sound1.setVolume((float)(sound1.getVolume()+0.1));
				else sound.setVolume((float)(sound.getVolume()+0.1));
			if (ke.getKeyCode() == KeyEvent.VK_ALT)
				if (first) sound1.setVolume((float)(sound1.getVolume()-0.1));
				else sound.setVolume((float)(sound.getVolume()-0.1));
			if (ke.getKeyCode() == KeyEvent.VK_RIGHT) released=true;
			if (ke.getKeyCode()==KeyEvent.VK_DOWN) released=true;
			if (ke.getKeyCode()==KeyEvent.VK_LEFT) released=true;
			if (ke.getKeyCode()==KeyEvent.VK_UP) released=true;
		}

		@Override
		public void keyTyped(KeyEvent arg0) {}
		
		public synchronized void pause()
		{
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		
		public static void setWorking(boolean w)
		{
			working=w;
		}
		
		
		public void startMusic()
		{
			sound.setVolume((float) 0.7);
			sound.play();
			synchronized(this)
			{
				this.notify();
			}
		}
		
		public void lose()
		{
			setVisible(false);
			
			sound.stop();
			sound1.stop();
			exit=true;
			mappanel.writeFile(wall1, path1);
			dispose();
			eg = new EndGame(wall1,path1); 
			eg.setDefaultCloseOperation(EXIT_ON_CLOSE);
		    eg.setLocation((Menu.ScreenSize.width-eg.getWidth())/2, (Menu.ScreenSize.height-eg.getHeight())/2);
		    eg.setResizable(false);
		    eg.setVisible(true);
		    
		}
		
		

	}


