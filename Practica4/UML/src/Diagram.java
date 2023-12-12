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

    private Transition currentTransition = null;

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

    public Node getFocusedNode(int x, int y) {
        for (Node node : nodes) {
            if (node.isFocused(x, y)) {
                return node;
            }
        }

        return null;
    }

    public Vector<Transition> getTransitionsThatHaveThisNode(Node node) {
        Vector<Transition> tt = new Vector<>();

        for (Transition t : transitions) {
            if (t.from == node || t.to == node) {
                tt.add(t);
            }
        }

        return tt;
    }

    public void updateMousePosition(int x, int y) {
        mouseX = x;
        mouseY = y;
    }

    public void paint(Graphics g) {
        super.paint(g);
        //Dibuja el diagrama de actividades
        for (Node node : nodes) {
            node.draw(g);
        }

        // draw transitions
        for (Transition t : transitions) {
            t.draw(g);
        }
    }

    /********************************************/
    /** MÈtodos de MouseListener               **/
    /********************************************/

    public void mousePressed(MouseEvent e) {
        // update mouse position
        updateMousePosition(e.getX(), e.getY());

        // check if the left mouse button was pressed
        if (e.getButton() == MouseEvent.BUTTON1) {
            // check if there is no selected node
            if (clickedNode == null) {
                // get mouse x & y pos
                int x = e.getX();
                int y = e.getY();

                // try to find if the mouse is overlapping a node
                clickedNode = getFocusedNode(x, y);

                // if succesfully clicked the node
                if (clickedNode != null) {
                    // print node name
                    System.out.println("Clicked node: " + clickedNode.getName());

                    // bring the node to the front
                    nodes.remove(clickedNode);
                    nodes.add(clickedNode);

                    // check if it is the same node as the selected one
                    if (selectedNode == clickedNode) {
                        // check if the selected node can be the origin of a transition
                        if (selectedNode.canBeOriginOfTransition()) {
                            // create a new transition with the from node to be the selected node
                            currentTransition = new Transition(selectedNode);
                            currentTransition.setTarget(x, y);

                            // add the transition to the list
                            transitions.add(currentTransition);

                            // reset clicked node
                            clickedNode = null;
                            selectedNode = null;
                        }
                    }
                }
            }
        }
        else if (e.getButton() == MouseEvent.BUTTON3) {
            // if mouse button right is pressed then delete the clicked node
            int x = e.getX();
            int y = e.getY();

            // try to find if the mouse is overlapping a node
            Node nodeToBeDeleted = getFocusedNode(x, y);

            if (nodeToBeDeleted != null) {
                nodes.remove(nodeToBeDeleted);

                // loop through all transitions that contain that node
                Vector<Transition> tt = getTransitionsThatHaveThisNode(nodeToBeDeleted);

                // delete all of those transitions
                transitions.removeAll(tt);
            }
        }

        // update transitions
        window.updateNNodes();
        window.updateNTransitions();
        repaint(); // call repaint() method
    }

    public void mouseReleased(MouseEvent e) {
        // update mouse position
        updateMousePosition(e.getX(), e.getY());

        // check for current transition
        if (currentTransition != null) {
            // find node focused
            Node nodeFocused = getFocusedNode(e.getX(), e.getY());

            // check if you drop it to a node
            if (nodeFocused != null) {
                // check if the node allows end transitions
                if (nodeFocused.canBeEndOfTransition()) {
                    currentTransition.setTargetNode(nodeFocused);
                } else {
                    transitions.remove(currentTransition);
                }
            } else {
                // remove the transition
                transitions.remove(currentTransition);
            }

            currentTransition = null;

            // deselect nodes
            for (Node node : nodes) {
                node.deselect();
            }

        } else {
            // deselect the node
            if (clickedNode != null) {
                clickedNode = null;
            }
        }

        // update transitions
        window.updateNNodes();
        window.updateNTransitions();
        repaint(); // call repaint() method
    }

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}

    /********************************************/
    /** Metodos de MouseMotionListener         **/
    /********************************************/

    public void mouseMoved(MouseEvent e) {
        // actualizamos las coordenadas del mouse
        updateMousePosition(e.getX(), e.getY());
    }

    public void mouseDragged(MouseEvent e) {
        // update mouse position
        updateMousePosition(e.getX(), e.getY());

        // if there is a transition in course
        if (currentTransition != null) {
            currentTransition.setTarget(e.getX(), e.getY());

            // check if there is a node
            for (Node node : nodes) {
                node.deselect();
            }

            Node node = getFocusedNode(e.getX(), e.getY());

            if (node != null && node.canBeEndOfTransition()) {
                node.select();
            }

        } else {
            // if a node is selected then move it
            if (clickedNode != null) {
                // get the x & y
                int x = e.getX();
                int y = e.getY();

                // move
                clickedNode.setPosition(x - clickedNode.getWidth() / 2, y - clickedNode.getHeight() / 2);
            }
        }

        // update transitions
        window.updateNNodes();
        window.updateNTransitions();
        repaint(); // call repaint() method
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
            selectedNode = getFocusedNode(mouseX, mouseY);

            if (selectedNode != null) {
                selectedNode.select();
            }

            // repaint
            repaint();
        }
    }

    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
}
