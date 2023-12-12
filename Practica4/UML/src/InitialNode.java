import java.awt.*;


public class InitialNode extends Node{
	private static int number = 1;

	private static final int INODE_WIDTH = 20;
	private static final int INODE_HEIGTH = 20;
	
	//TODO: otros atributos
		
	public InitialNode() {
		super();
		name = "Initial " + number;
		width = INODE_WIDTH;
		height = INODE_HEIGTH;

		number++;
	}

	@Override
	public void draw(Graphics g){
		Graphics2D g2 = (Graphics2D)g;

		// check if selected

		if (isSelected) {
			g2.setColor(Color.cyan);
		} else {
			g2.setColor(Color.black);
		}

		// dibujar el nodo como un circulo
		g2.fillOval(x, y, width, height);
	}
	
	@Override
	public boolean canBeEndOfTransition() {
		return false;
	}
}
