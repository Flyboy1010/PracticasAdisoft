import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window implements ActionListener {

	//atributos
	private Diagram diagram; //superficie de dibujo
	private JPanel panel; 
	private GridBagConstraints c;
	private JButton buttonIniNode; //para añadir nodos iniciales
	private JButton buttonFinalNode; //para añadir nodos finales	
	private JButton buttonActivity; //para añadir actividades
	private JLabel labelNNodes; //Etiqueta con número de nodos
	private JLabel labelNTransitions; //Etiqueta con número de transiciones
	

	//metodos
	public Window() {
		super();
		//creamos el panel y el grid
		panel = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
	}
	
	public Component createComponents() {
		//creamos los botones y los ponemos en el panel
		buttonIniNode = new JButton("Initial Node");
		buttonFinalNode = new JButton("Final Node");
		buttonActivity = new JButton("Activity Node");				
		
		//anyadimos los ActionListener
		//buttonIniState.setMnemonic(KeyEvent.VK_I); 
		buttonIniNode.addActionListener(this);
		//buttonFinState.setMnemonic(KeyEvent.VK_I); 
		buttonFinalNode.addActionListener(this);
		//buttonInterState.setMnemonic(KeyEvent.VK_I); 
		buttonActivity.addActionListener(this);
		
 		setGridProperties(0,0,1,1,0,0,GridBagConstraints.NONE);
		    panel.add(buttonIniNode, c); 
	    
	    setGridProperties(c.RELATIVE,0,1,1,0,0,GridBagConstraints.NONE);
	    panel.add(buttonFinalNode, c); 
	    
	    setGridProperties(c.RELATIVE,0,1,1,0,0,GridBagConstraints.NONE);
	    panel.add(buttonActivity, c);

		//creamos el diagrama y lo ponemos en el panel
		diagram = new Diagram(this);
		setGridProperties(0,1,4,3,1.0,1.0,GridBagConstraints.BOTH);
      	panel.add(diagram, c);
  
		//creamos la etiqueta para contar nodos y la ponemos en el panel 
	    labelNNodes = new JLabel("Nodes: " + diagram.getNNodes());
		setGridProperties(0,4,2,1,0,0,GridBagConstraints.HORIZONTAL);
        panel.add(labelNNodes, c);
        
		//creamos la etiqueta para contar transiciones y la ponemos en el panel 
	    labelNTransitions = new JLabel("Transitions: " + diagram.getNTransitions());
		setGridProperties(2,4,2,1,0,0,GridBagConstraints.HORIZONTAL);
        panel.add(labelNTransitions, c);
		
		//ultimos ajustes del panel
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		return panel;
	}
	
	private void setGridProperties(int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int fill)
	{
     	c.gridx = gridx;
        c.gridy = gridy;
        c.gridwidth = gridwidth;
        c.gridheight = gridheight;
        c.weightx = weightx;
        c.weighty = weighty;
		c.fill = fill;
	}

	public void actionPerformed(ActionEvent e){
		switch(e.getActionCommand()) {
			case "Initial Node":
					diagram.addIniNode();
					break;
			case "Final Node":
					diagram.addFinalNode();
					break;
			case "Activity Node":
					diagram.addActivity();
					break;
		}
		updateNNodes();
		diagram.requestFocusInWindow();
		diagram.repaint();
	}
	
	public void updateNNodes(){
		labelNNodes.setText("Nodes: " + diagram.getNNodes());
	}
	public void updateNTransitions(){
		labelNTransitions.setText("Transitions: " + diagram.getNTransitions());
	}
}
