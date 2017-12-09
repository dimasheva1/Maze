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
		setSize(getSize());
	}
	
	public MapPanel(File wall,File path)
	{
		map = new Map(wall,path);
	}
	
		
	
	public void paintComponent(Graphics g)
	{
		
		map.paintMap(g);
	}
	
	
	public Dimension getSize()
	{
		return map.getSize();
	}
	
	public Map getMap()
	{
		return map;
	}
	
	public int getKolCell()
	{
		return map.getKolCell();
	}
	
	public void notifyPlayer()
	{
		map.notifyPlayer();
	}
	
	public boolean movePlayer(boolean released)
	{
		 return map.movePlayer(released, this);
	}
	
	public void setK(int k)
	{
		map.setK(k);
	}
	
	public void writeFile(File wall, File path)
	{
		map.writeFile(wall, path);
	}
	
	public void playerOnStart()
	{
		map.playerOnStart();
	}
	
	public int getSizeMap()
	{
		return map.getSizeMap();
	}
	

}
