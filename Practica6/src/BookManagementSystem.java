import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookManagementSystem {
    private static JFrame frame = new JFrame("Book Management System");

    public static void main(String[] args) {
        // Crear algunos libros de ejemplo
        BookManager.getInstance();
        //BookManager.getInstance().addBook(new Book(1, "Book1", "Author1", 20.0f));
        //BookManager.getInstance().addBook(new Book(2, "Book2", "Author2", 25.0f));

        // Crear y configurar la ventana principal

        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear un panel para contener los botones
        JPanel panel = new JPanel(new GridLayout(4, 1));

        // Crear botones para Listar, Crear, Borrar y Modificar
        JButton listButton = new JButton("Listar");
        JButton createButton = new JButton("Crear");
        JButton deleteButton = new JButton("Borrar");
        JButton updateButton = new JButton("Modificar");

        // Agregar ActionListener a cada botón
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBooksList();
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCreateDialog();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDeleteDialog();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showUpdateDialog();
            }
        });

        // Agregar botones al panel
        panel.add(listButton);
        panel.add(createButton);
        panel.add(deleteButton);
        panel.add(updateButton);

        // Agregar panel a la ventana principal
        frame.getContentPane().add(panel);

        // Hacer visible la ventana principal
        frame.setVisible(true);
    }

    private static void showBooksList() {
        ListDialog listDialog = new ListDialog(frame);
        listDialog.setVisible(true);
    }

    private static void showCreateDialog() {
        CreateDialog createDialog = new CreateDialog(frame);
        createDialog.setVisible(true);
    }

    private static void showDeleteDialog() {
        DeleteDialog deleteDialog = new DeleteDialog(frame);
        deleteDialog.setVisible(true);
    }

    private static void showUpdateDialog() {
        UpdateDialog updateDialog = new UpdateDialog(frame);
        updateDialog.setVisible(true);
    }
}
