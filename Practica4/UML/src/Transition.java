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
	
	final int  ARROW_WIDTH = 7;
	final int  ARROW_HEIGHT= 14;

	public Node from;
	public Node to;

	//TODO: Otros atributos que necesites
	
	public Transition(Node s, int x1, int y1){
		//TODO: ...
	}

	
	//TODO: otros métodos que necesites

	public void draw(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		
		Line2D.Double line = new Line2D.Double();

		//TODO: Dibujar	
	}
	
}
