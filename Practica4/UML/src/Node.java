import java.awt.Graphics;


public class Node {
	
	//Atributos
	protected int x, y;
	protected int width, height;
	protected String name;
	protected boolean isSelected = false;
	
	public Node() {
		//TODO: ...
	}
	
	public void draw(Graphics g){
	}

	public boolean isFocused(int mx, int my) {
		return ((mx >= x) && (mx < x + width)) && ((my >= y) && (my < y + height));
	}
	
	//Otros metodos

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getWidth() { return width; }
	public int getHeight() { return height; }

	public int getX() { return x; }
	public int getY() { return y; }

	public void select() { isSelected = true; }
	public void deselect() { isSelected = false; }

	public String getName() { return name; }

	public boolean canBeOriginOfTransition() {
		return true;
	}

	public boolean canBeEndOfTransition() {
		return true;
	}
}
