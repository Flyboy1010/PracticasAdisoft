import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Diagram extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

    //atributos
    private Window window;
    public Node node;

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
        //TODO: Añade un nodo inicial
        nodes.add(new InitialNode());
    }

    public void addFinalNode() {
        //TODO: Añade un nodo final
        nodes.add(new FinalNode());
    }

    public void addActivity() {
        //TODO: Añade un nodo intermedio
        nodes.add(new ActivityNode());
    }


    public int getNNodes(){
        //TODO: Devuelve el número de nodos
        return nodes.size();
    }

    public int getNTransitions(){
        //TODO: Devuelve el número de transiciones
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
        //TODO: ...
        repaint(); // call repaint() method
    }

    public void mouseReleased(MouseEvent e) {
        //TODO: ...

    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    /********************************************/
    /** MÈtodos de MouseMotionListener         **/
    /********************************************/

    public void mouseMoved(MouseEvent e) {
        //TODO: ...
    }

    public void mouseDragged(MouseEvent e) {//Cuando se arrastra el ratón
        //TODO: ...
    }

    /********************************************/
    /** MÈtodos de KeyListener                 **/
    /********************************************/
    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        //TODO: ...
    }

    public void keyReleased(KeyEvent e) {
    }
}
