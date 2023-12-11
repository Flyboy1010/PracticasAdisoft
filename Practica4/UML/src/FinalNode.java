import java.awt.*;
import java.awt.geom.Ellipse2D;

public class FinalNode extends Node{
	private static int number = 1;

	private static final int FNODE_WIDTH = 20;
	private static final int FNODE_HEIGHT= 20;

	public FinalNode() {
		super();
		name = "Final " + number;
		width = FNODE_WIDTH;
		height = FNODE_HEIGHT;

		number++;
	}
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		Ellipse2D circulo = new Ellipse2D.Double(x, y, width, height);
		// Para suavizar los bordes
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (isSelected) {
			// Primero dibujo el circulo blanco correspondiente al anillo que rodea al final node
			g2.setColor(Color.WHITE);
			Ellipse2D innerCircle = new Ellipse2D.Double(x + 3, y + 3, width - 6, height - 6);
			g2.setStroke(new BasicStroke(1.0f));
			g2.draw(innerCircle);
			// Relleno el interior
			g2.setColor(Color.CYAN);
			g2.fill(innerCircle);
			// DIbujo el borde exterior
			g2.setColor(Color.CYAN);
			g2.setStroke(new BasicStroke(1.0f));

		} else {
			// Primero dibujo el circulo blanco correspondiente al anillo que rodea al final node
			g2.setColor(Color.WHITE);
			Ellipse2D innerCircle = new Ellipse2D.Double(x + 3, y + 3, width - 6, height - 6);
			g2.setStroke(new BasicStroke(1.0f));
			g2.draw(innerCircle);
			// Relleno el interior
			g2.setColor(Color.BLACK);
			g2.fill(innerCircle);
			// DIbujo el borde exterior
			g2.setColor(Color.BLACK);
			g2.setStroke(new BasicStroke(1.0f));
		}
		g2.draw(circulo);
	}
}
