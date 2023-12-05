import java.awt.Graphics;
import java.awt.Graphics2D;
//TODO: otros import



public class InitialNode extends Node{
	private static int number = 1;

	final int  INODE_WIDTH = 20;
	final int  INODE_HEIGTH = 20;
	
	//TODO: otros atributos
		
	public InitialNode() {
		super();
		name = "Initial " + number;
		width = INODE_WIDTH;
		height = INODE_HEIGTH;

		number++;
	}
	
	public void draw(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		//TODO: dibujar el nodo
		g2.fillOval(x, y, width, height);
	}
	
	//TODO: otros métodos
}
