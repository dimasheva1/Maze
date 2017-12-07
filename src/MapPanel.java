import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MapPanel extends JPanel{
	private Map map;
	
	
	
	public MapPanel(int size)
	{
		map = new Map(size);
	}
	
	public MapPanel(File wall,File path)
	{
		map = new Map(wall,path);
	}
	
		
	
	public void paintComponent(Graphics g)
	{
		
		for(int i =0;i<map.getList().length;i++)
			for (int j=0;j<map.getList()[0].length;j++)
			{
				g.drawImage(map.getList()[i][j].getImg(),map.getList()[i][j].getPosX()*Map.cellSize , map.getList()[i][j].getPosY()*Map.cellSize, 16, 16, this);
			}
		g.drawImage(new ImageIcon("img/finish.png").getImage(), (map.findOutput())*Map.cellSize, (map.getList().length-1)*Map.cellSize, this);
		g.drawImage(new ImageIcon("img/start.png").getImage(), (map.findInput()+1)*Map.cellSize, 0, this);
		
		paintPlayer(g);
	}
	
	
	public void paintPlayer(Graphics g)
	{
		g.drawImage(map.getPlayer().getImg(),map.getPlayer().getPosX(),map.getPlayer().getPosY(),16,16,this);
	}
	
	public Dimension getSize()
	{
		return map.getSize();
	}
	
	public Map getMap()
	{
		return map;
	}
	

}
