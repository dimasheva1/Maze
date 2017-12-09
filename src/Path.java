

import javax.swing.ImageIcon;

public class Path extends Cell {
	static ImageIcon img = new ImageIcon("img/planks.png");

	public Path(int posX, int posY) {
		super(posX, posY, true, img,' ');
	}
	
}
