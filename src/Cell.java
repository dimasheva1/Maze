import java.awt.Image;


import javax.swing.ImageIcon;

 class Cell {
	
private int posX;
private int posY;
private transient Image img;
private boolean goTo;
private char flag;

public Cell(int posX,int posY,boolean goTo,ImageIcon img,char flag)
{
	this.setPosX(posX);
	this.setPosY(posY);
	this.setGoTo(goTo);
	this.setImg(img.getImage());
	this.setFlag(flag);
	
}

public int getPosX() 
{
	return posX;
}

public void setPosX(int posX) 
{
	this.posX = posX;
}

public int getPosY() 
{
	return posY;
}

public void setPosY(int posY) 
{
	this.posY = posY;
}
	
public boolean getGoTo()
{
	return goTo;
}



public void setGoTo(boolean goTo)
{
	this.goTo = goTo;
}


public Image getImg() {
	return img;
}

public void setImg(Image img) {
	this.img = img;
}

public char getFlag() {
	return flag;
}

public void setFlag(char flag) {
	this.flag = flag;
}
	
	
	
	

}
