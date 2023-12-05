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
	
	public void draw(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		
		//Dibujamos el Nodo
	        RoundRectangle2D rect = new RoundRectangle2D.Double(x, y, width, height, arcwidth, archeight);
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g2.setStroke(new BasicStroke(1));

			/*
	        if (selected)
        			TODO: ...
        		else{
			TODO: ...
		}*/

        
		//TODO: Escribimos el texto en el Nodo
  

	}


}
