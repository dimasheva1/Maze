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
	

}
