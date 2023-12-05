import java.awt.Graphics;
import java.awt.Graphics2D;
//TODO: otros import


public class FinalNode extends Node{
	private static int number = 1;

	final int  FNODE_WIDTH = 20;
	final int  FNODE_HEIGHT= 20;
	
	//TODO: otros atributos
		
	public FinalNode() {
		super();
		name = "Final " + number;
		width = FNODE_WIDTH;
		height = FNODE_HEIGHT;

		number++;
	}
	
	public void draw(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		//TODO: dibujar el nodo

	}
	
	//TODO: otros métodos
}
