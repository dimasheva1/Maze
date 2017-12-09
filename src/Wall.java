

import javax.swing.ImageIcon;

public class Wall extends Cell {
	static ImageIcon img = new ImageIcon("img/bricks.png");

	public Wall(int posX, int posY) {
		super(posX, posY, false, img,'%');
	}
	
	

}
