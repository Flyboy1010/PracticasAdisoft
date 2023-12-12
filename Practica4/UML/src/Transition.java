import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import java.awt.geom.AffineTransform;
import java.lang.Math;
import java.awt.geom.Point2D;

public class Transition {
	
	private static final int ARROW_WIDTH = 7;
	private static final int ARROW_HEIGHT= 14;

	public Node from;
	public Node to;

	public int targetX = 0;
	public int targetY = 0;
	
	public Transition(Node from){
		this.from = from;
	}

	public void setTarget(int x, int y) {
		targetX = x;
		targetY = y;
	}

	public void setTargetNode(Node target) {
		to = target;
	}

	private void drawArrow(Graphics2D g2, double x1, double y1, double x2, double y2) {
		double angle = Math.atan2(y1 - y2, x1 - x2);
		int arrowSize = 10;

		g2.drawLine((int) x1, (int) y1, (int) x2, (int) y2);

		g2.drawLine((int) x2, (int) y2, (int) (x2 + arrowSize * Math.cos(angle - Math.PI / 6)),
				(int) (y2 + arrowSize * Math.sin(angle - Math.PI / 6)));

		g2.drawLine((int) x2, (int) y2, (int) (x2 + arrowSize * Math.cos(angle + Math.PI / 6)),
				(int) (y2 + arrowSize * Math.sin(angle + Math.PI / 6)));
	}

	// draw
	public void draw(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		
		Line2D.Double line = new Line2D.Double();

		//TODO: Dibujar
		g2.setColor(Color.black);

		// check if the to node is null
		if (to == null) {
			// transition to the targetx & targety position
			line = new Line2D.Double(from.getX() + from.getWidth() / 2.0f, from.getY() + from.getHeight() / 2.0f, targetX, targetY);
			g2.draw(line);
			drawArrow(g2, from.getX() + from.getWidth() / 2.0, from.getY() + from.getHeight() / 2.0,
					targetX, targetY);
		} else {
			if (from == to) {
				// auto transition
				int x1a = from.getX();
				int y1a = from.getY();

				// Dibujar la línea de la flecha, 4 segmentos
				int xmenos=x1a-from.getWidth()/2;
				int ymenos=y1a-from.getHeight()/2;
				int xmas=x1a+from.getWidth()/2;
				int ymas=y1a+from.getHeight()/2;

				g2.setColor(Color.BLACK);
				g2.draw(new Line2D.Double(xmas, y1a, xmas, ymenos));
				g2.draw(new Line2D.Double(xmas, ymenos, xmenos, ymenos));
				g2.draw(new Line2D.Double(xmenos, ymenos, xmenos, ymas));
				g2.draw(new Line2D.Double(xmenos, ymas, x1a, ymas));

			} else {
				// transition to the to node
				line = new Line2D.Double(from.getX() + from.getWidth() / 2.0f, from.getY() + from.getHeight() / 2.0f, to.getX() + to.getWidth() / 2.0f, to.getY() + to.getHeight() / 2.0f);
				g2.draw(line);
				drawArrow(g2, from.getX() + from.getWidth() / 2.0, from.getY() + from.getHeight() / 2.0,
						to.getX() + to.getWidth() / 2.0, to.getY() + to.getHeight() / 2.0);
			}
		}
	}
	
}
