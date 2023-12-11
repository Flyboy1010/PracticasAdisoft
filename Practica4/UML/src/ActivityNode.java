import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D ;

public class ActivityNode extends Node{
	private static int number = 1;

	private int arcwidth, archeight;
		
	public ActivityNode() {
		super();
		name = "Activity" + number;
		width = 110;
		height = 90;	
		arcwidth  = 15;
		archeight  = 15;

		number++;
	}

	@Override
	public void draw(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		//Dibujamos el Nodo
		RoundRectangle2D rect = new RoundRectangle2D.Double(x, y, width, height, arcwidth, archeight);
		//La siguiente línea es para suavizar los bordes al dibujar los nodos
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// Definimos el grosor del borde
		g2.setStroke(new BasicStroke(1));

		if (isSelected){
			// Cambiamos el color del rectangulo a cyan cuando el nodo está seleccionado
			g2.setColor(Color.CYAN);
			g2.fill(rect);
		}
		else{
			// Cambiamos el color del rectangulo normal
			g2.setColor(new Color(102, 178, 255));
			g2.fill(rect);

		}
		// Color del borde
		g2.setColor(Color.BLACK);
		g2.draw(rect);

		// Escribimos el "name" en el nodo
		// Obtenemos las medidas de la fuente
		FontMetrics fontMetrics = g2.getFontMetrics(g2.getFont());

		// Calculamos las coordenadas para centrar el texto en la parte superior
		int xName = x + (width - fontMetrics.stringWidth(name)) / 2;
		int yName = y + fontMetrics.getHeight();
		g2.drawString(name, xName, yName);
	}

}
