import javax.swing.*;
import java.awt.*;


public class Application {

	//metodos
	private static void createAndShowGUI() {			
		//creamos el JFrame
		JFrame.setDefaultLookAndFeelDecorated(true); 
		JFrame frame = new JFrame("Práctica Diagrama de Actividades");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//creamos la ventana y sus componentes 	
		Window window = new Window();
		Component components = window.createComponents();
		
		//anyadimos los componentes al JFrame
		frame.getContentPane().add(components, BorderLayout.CENTER);
		frame.pack();

		//establecemos los atributos del JFrame
		frame.setSize(800, 600);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {createAndShowGUI();}
		});
	}
}
