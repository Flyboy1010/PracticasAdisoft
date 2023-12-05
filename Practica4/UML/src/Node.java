import java.awt.Graphics;


public class Node {
	
	//Atributos
	protected int x, y;
	protected int width, height;
	protected String name;
	
	public Node() {
		//TODO: ...
	}
	
	public void draw(Graphics g){
	}

	public boolean isFocused(int mx, int my) {
		return ((mx >= x) && (mx < x + width)) && ((my >= y) && (my < y + height));
	}
	
	//Otros metodos

	public void SetPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int GetX() { return x; }
	public int GetY() { return y; }

	public String GetName() { return name; }
}
