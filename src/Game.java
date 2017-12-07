
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
		private boolean released=true;
		private boolean first=false;
		private Sound sound,sound1;	
		public static File wall = new File("wall.txt");
	    public static File path = new File("path.txt");
		Thread player = new Thread(new Runnable()
	     {public void run()
	     {movePlayer();}
	     });
		
		Thread checkPlayMusic = new Thread(new Runnable()
				{
			      public void run()
			      {
			    	  long time = 40000;
			    	  while(true)
			    	  {
			    	     try 
			    	     {
						    Thread.sleep(time);
					     } catch (InterruptedException e) {} 
			    	   if (!first) 
			    		{
			    		 sound.stop();
			    		 time = 33000;
			    		 first=true;
			    		}
			    	   sound1.setVolume(sound.getVolume());
			    	  sound1.play();
			    	 
			    	  }
			      }
				});
		
		public Game(int size)
		{
			sound = new Sound(new File("1.wav"));
			sound.play();
			sound1= new Sound(new File("2.wav"));
			checkPlayMusic.start();
			map = new Map(size);
			add(map);
			addKeyListener(this);
			player.start();
		}
		
		public Game(File wall,File path)
		{
			sound = new Sound(new File("1.wav"));
			sound.play();
			checkPlayMusic.start();
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
					  if (released) player.wait();	
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
						Thread.sleep(18);
						
					} catch (InterruptedException e) {}
					
					}
				
				}
				catch (ArrayIndexOutOfBoundsException e) {} 
				if (map.checkFinish())
				{
					sound.stop();
					sound1.stop();
					EndGame eg = new EndGame();
					eg.setSize(200, 130);
					eg.setDefaultCloseOperation(EXIT_ON_CLOSE);
				    eg.setLocation((Menu.ScreenSize.width-eg.getWidth())/2, (Menu.ScreenSize.height-eg.getHeight())/2);
				    eg.setVisible(true);
					this.dispose();
					synchronized(player)
					{
					try {
						player.wait();
					} catch (InterruptedException e) {}
					}
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
					released=false;
					synchronized(player)
					{
						player.notify();
					}
				
				}
				if (ke.getKeyCode()==KeyEvent.VK_DOWN)
				{
					k=4;
					working= true;
					released=false;
					synchronized(player)
					{
						player.notify();
					}
				}
				if (ke.getKeyCode()==KeyEvent.VK_LEFT)
				{
					k=2;
					working= true;
					released=false;
					synchronized(player)
					{
						player.notify();
					}
				}
				if (ke.getKeyCode()==KeyEvent.VK_UP)
				{
					k=3;
					working= true;
					released=false;
					synchronized(player)
					{
						player.notify();
					}
				}
				if (ke.getKeyCode()==KeyEvent.VK_ESCAPE)
				{
					map.writefile(wall, path);
					sound.stop();
					sound1.stop();
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
		
		

	}


