import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Map{
	protected static final  int cellSize=16;
	private Cell [][] list;
	private volatile Cell p ; 
	private Solver solve;
	private MapMaker mm;
	private int k=0;
	private int size;

	
	
		
	
	
	public Map(int size)
	{
	    mm = new MapMaker(size,size);
		mm.makeMaze();
		list = mm.getMaze();
		solve= new Solver(list);
		p = new Cell(findInput()*cellSize,0,true,new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("player.png"))),' ');
		
		
	
	}
	
	public Map(File wall,File path)
	{
		readFile(wall,path);
		solve= new Solver(list);
		p = new Cell(findInput()*cellSize,0,true,new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("player.png"))),' ');
	}
	
	
	
	
	
	
	public void goRight() 
	{
		p.setPosX(p.getPosX()+2);
	}
	
	public void goLeft()
	{
		p.setPosX(p.getPosX()-2);
	}
	
	public void goUp()
	{
		p.setPosY(p.getPosY()-2);
	}
	
	public void goDown()
	{
		p.setPosY(p.getPosY()+2);
	}
	
	public boolean ifCan(int k) ///1 - Right; 2 - Left; 3 - Up; 4 - Down
	{
		if (k==1)
			if(list[p.getPosX()/cellSize+1][p.getPosY()/cellSize].getGoTo()) return true;
		if (k==2)
			if(list[p.getPosX()/cellSize-1][p.getPosY()/cellSize].getGoTo()) return true;
		if (k==3)
			if(list[p.getPosX()/cellSize][p.getPosY()/cellSize-1].getGoTo()) return true;
		if (k==4)
			if(list[p.getPosX()/cellSize][p.getPosY()/cellSize+1].getGoTo()) return true;
		return false;
	}
	
	public Dimension getSize()
	{
		return (new Dimension((list.length)*cellSize+6,(list[0].length+1)*cellSize+13+27));
	}
	
	public boolean checkFinish()
	{
		if (p.getPosY()/16+1==list[0].length) return true;
		else return false;
		
	}
		
	public int findInput()
    {
    	for (int i =0;i<list.length;i++)
    		if (list[i][0].getGoTo()==true) return i;
    	return 0;
    }
	
	public int findOutput()
    {
    	for (int i =0;i<list.length;i++)
    		if (list[i][list.length-1].getGoTo()==true) return i;
    	return 0;
    }
	
	public void writeFile(File wall,File path)
	{
		try(FileWriter w = new FileWriter(wall,false);
			FileWriter w1 = new FileWriter(path,false);)
		{
		
		
	        String s = " " + list.length;
	        s=s.substring(1, 3);
            w.write(s);
            w.write("\r\n");
            for(int i =0;i<list.length;i++){
            	for (int j=0;j<list[0].length;j++)
            	{
            		if (list[i][j].getGoTo()==false)
            		{
                        w.write(list[i][j].getPosX() + " " + list[i][j].getPosY());
                        w.write("\r\n");
            		}
            		else
            	    {
            			w1.write(list[i][j].getPosX() + " " + list[i][j].getPosY());
            			w1.append("\r\n");
            		}
            	}
            } 	
        }
        catch (IOException e){}		
	}
	
	public void readFile(File wall,File path)
	{
		Scanner in;
		Scanner in1;
		try {
		in = new Scanner(wall);
		in1 = new Scanner(path);
		size = in.nextInt();
		list = new Cell [size][size];
		while(in.hasNext())
		{
			int posX=in.nextInt();
			int posY=in.nextInt();
			list[posX][posY]=new Wall(posX,posY,new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("bricks.png"))));
		}
		while(in1.hasNext())
		{
			int posX=in1.nextInt();
			int posY=in1.nextInt();
			list[posX][posY]=new Path(posX,posY,new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("planks.png"))));
		}
		in.close();
		in1.close();
		} catch (FileNotFoundException e) {}
	}
	
	
	public void paintMap(Graphics g)
	{		
			for(int i =0;i<list.length;i++)
				for (int j=0;j<list[0].length;j++)
				{
					g.drawImage(list[i][j].getImg(),list[i][j].getPosX()*cellSize , list[i][j].getPosY()*cellSize, 16, 16, null);
				}
			g.drawImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("finish.png")), (findOutput())*cellSize, (list.length-1)*cellSize, null);
			g.drawImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("start.png")), (findInput()+1)*cellSize, 0, null);
			
			g.drawImage(p.getImg(),p.getPosX(),p.getPosY(),16,16,null);
	}
		
		
    public int getKolCell()
    {
    	solve.solveMaze();
    	return solve.getKolCell();
    }
	
    
    public boolean movePlayer(boolean released, MapPanel mappanel)
	{
    	synchronized(Thread.currentThread())
		  {
		  try {
			  if (released) Thread.currentThread().wait();	
			} catch (InterruptedException e) {}
		  }
		
			Game.setWorking(true);
			try
			{
			if (ifCan(k))
			
					
		  for (int i = 1;i<9;i++)		
				{
			  if      (k==1) goRight();
			  else if (k==2) goLeft();
			  else if (k==4) goDown();
			  else if (k==3) goUp();	
				mappanel.repaint();
				try {
					Thread.sleep(18);
				} catch (InterruptedException e) {}
				
				}
			
			
			}
			catch (ArrayIndexOutOfBoundsException e) {} 
			if (checkFinish()) return false;
		  Game.setWorking(false);
		  return true;
		  
		}
    
    public void setK(int k)  // указание напрвления движения
    {
    	this.k=k;
    }
		
		
	
    public int getSizeMap()//число передаваемое в мапмейкер
	{
		return size;
	}
	
	}
 		
 	
	
	
	

	
	
	


