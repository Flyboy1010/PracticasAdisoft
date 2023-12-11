import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.lang.Math;
import java.awt.geom.Point2D;
import java.awt.Point;
import java.awt.Rectangle;

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

	// draw
	public void draw(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		
		Line2D.Double line = new Line2D.Double();

		// check if the to node is null
		if (to == null) {
			line = new Line2D.Double(from.getX() + from.getWidth() / 2.0f, from.getY() + from.getHeight() / 2.0f, targetX, targetY);
		} else {
			if (from == to) {

			} else {
				line = new Line2D.Double(from.getX() + from.getWidth() / 2.0f, from.getY() + from.getHeight() / 2.0f, to.getX() + to.getWidth() / 2.0f, to.getY() + to.getHeight() / 2.0f);
			}
		}

		//TODO: Dibujar
		g2.draw(line);
	}
	
}
