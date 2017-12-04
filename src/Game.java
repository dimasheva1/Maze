
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
	import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JFrame;


public class Game extends JFrame implements KeyListener{
		private static Map map;
		private volatile int k=0;// 1 - Right; 2 - Left; 3 - Up; 4 - Down
		private boolean working=false;
		public static File wall = new File("wall.txt");
	    public static File path = new File("path.txt");
		Thread player = new Thread(new Runnable()
	     {public void run()
	     {movePlayer();}
	     });
		
		public Game(int size)
		{
			map = new Map(size);
			add(map);
			addKeyListener(this);
			player.start();
		}
		
		public Game(File wall,File path)
		{
			map=new Map(wall,path);
			add(map);
			addKeyListener(this);
			player.start();	
		}
		
		
			
		
		public static void main(String [] args)
		{
			Game a = new Game(10);
			a.setSize(22*16, 23*16+7);
			a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			a.setVisible(true);
			
			
		}
		
		
		
		public void movePlayer()
		{
			
			while(true)
			{
				synchronized(player)
				  {
				  try {
						player.wait();
					} catch (InterruptedException e) {}
				  }
				working = true;
				try
				{
				if (map.ifCan(k))
				
						
			  for (int i = 1;i<9;i++)		
					{
				  if      (k==1) map.goRight();
				  else if (k==2) map.goLeft();
				  else if (k==4) map.goDown();
				  else if (k==3) map.goUp();
					
					map.repaint();
					try {
						Thread.sleep(20);
						
					} catch (InterruptedException e) {}
					
					}
				
				}
				catch (ArrayIndexOutOfBoundsException e) {} 
				if (map.checkFinish())
				{
					EndGame eg = new EndGame();
					eg.setSize(200, 130);
					eg.setDefaultCloseOperation(EXIT_ON_CLOSE);
				    eg.setLocation((Menu.ScreenSize.width-eg.getWidth())/2, (Menu.ScreenSize.height-eg.getHeight())/2);
				    eg.setVisible(true);
					this.dispose();
				}
			  working=false;
			}
			
			
		}
		
		public Dimension getSize()
		{
			return map.getSize();
		}
   
		
		public void keyPressed(KeyEvent ke) {
			if (working==false)
			{
				if (ke.getKeyCode() == KeyEvent.VK_RIGHT)
				{
					k=1;
					working= true;
					synchronized(player)
					{
						player.notify();
					}
				
				}
				if (ke.getKeyCode()==KeyEvent.VK_DOWN)
				{
					k=4;
					synchronized(player)
					{
						player.notify();
					}
				}
				if (ke.getKeyCode()==KeyEvent.VK_LEFT)
				{
					k=2;
					synchronized(player)
					{
						player.notify();
					}
				}
				if (ke.getKeyCode()==KeyEvent.VK_UP)
				{
					k=3;
					synchronized(player)
					{
						player.notify();
					}
				}
				if (ke.getKeyCode()==KeyEvent.VK_ESCAPE)
				{
					map.writefile(wall, path);
					Menu menu = new Menu();
					menu.setSize(290,350);
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
		public void keyReleased(KeyEvent arg0) {}

		@Override
		public void keyTyped(KeyEvent arg0) {}
		
		

	}


