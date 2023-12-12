import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Diagram extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

    //atributos
    private Window window;
    private Node clickedNode = null;
    private Node selectedNode = null;
    private int mouseX = 0, mouseY = 0;

    private Vector<Node> nodes = new Vector<Node>(); //los nodos que crea el usuario
    private Vector<Transition> transitions = new Vector<Transition>(); // las transiciones que crea el usuario

    //TODO: ... (otros posibles atributos que necesites)

    //metodos
    public Diagram(Window w) {

        window = w;

        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);

        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void addIniNode() {
        nodes.add(new InitialNode());
    }

    public void addFinalNode() {
        nodes.add(new FinalNode());
    }

    public void addActivity() {
        nodes.add(new ActivityNode());
    }


    public int getNNodes(){
        return nodes.size();
    }

    public int getNTransitions(){
        return transitions.size();
    }

    public void paint(Graphics g) {
        super.paint(g);
        //Dibuja el diagrama de actividades
        for (Node node : nodes) {
            node.draw(g);
        }
    }

    /********************************************/
    /** MÈtodos de MouseListener               **/
    /********************************************/

    public void mousePressed(MouseEvent e) {
        // check if the left mouse button was pressed
        if (e.getButton() == MouseEvent.BUTTON1) {
            // check if there is no selected node
            if (clickedNode == null) {
                // get mouse x & y pos
                int x = e.getX();
                int y = e.getY();

                // try to find if the mouse is overlapping a node
                for (Node node : nodes) {
                    if (node.isFocused(x, y)) {
                        clickedNode = node;
                        System.out.println(clickedNode.getName());
                        break;
                    }
                }

                // bring the node to the front
                if (clickedNode != null) {
                    nodes.remove(clickedNode);
                    nodes.add(clickedNode);
                }
            }
        }
        else if (e.getButton() == MouseEvent.BUTTON3) {
            // if mouse button right is pressed then delete the clicked node
            int x = e.getX();
            int y = e.getY();

            // try to find if the mouse is overlapping a node
            Node nodeToBeDeleted = null;
            for (Node node : nodes) {
                if (node.isFocused(x, y)) {
                    nodeToBeDeleted = node;
                    break;
                }
            }

            if (nodeToBeDeleted != null) {
                nodes.remove(nodeToBeDeleted);
            }

            // update number of nodes
            window.updateNNodes();
        }

        repaint(); // call repaint() method
    }

    public void mouseReleased(MouseEvent e) {
        // check for drag & drop transitions

        // deselect the node
        if (clickedNode != null) {
            clickedNode = null;

            // repaint
            repaint();
        }
    }

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}

    /********************************************/
    /** Metodos de MouseMotionListener         **/
    /********************************************/

    public void mouseMoved(MouseEvent e) {
        // actualizamos las coordenadas del mouse
        mouseX = e.getX();
        mouseY = e.getY();
    }

    public void mouseDragged(MouseEvent e) {//Cuando se arrastra el ratón
        // if a node is selected then move it
        if (clickedNode != null) {
            // get the x & y
            int x = e.getX();
            int y = e.getY();

            // move
            clickedNode.setPosition(x - clickedNode.getWidth() / 2, y - clickedNode.getHeight() / 2);

            // repaint
            repaint();
        }
    }

    /********************************************/
    /** Metodos de KeyListener                 **/
    /********************************************/
    public void keyPressed(KeyEvent e) {
        // when the s key gets pressed
        if (e.getKeyChar() == 's') {
            // if there is a node already selected deselect it
            if (selectedNode != null) {
                selectedNode.deselect();
                selectedNode = null;
            }

            // check if there is a node to be selected
            for (Node node : nodes) {
                if (node.isFocused(mouseX, mouseY)) {
                    node.select();
                    selectedNode = node;
                    break;
                }
            }

            // repaint
            repaint();
        }
    }

    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
}
