import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MapPanel extends JPanel{
	private Map map;
	//private JButton start;
	
	
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
	
	
	public int getSizeMap()
	{
		return map.getSizeMap();
	}
	

}
