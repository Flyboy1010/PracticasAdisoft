import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteDialog extends JDialog {
    private JTextField idField;
    private JTextArea bookInfoArea;

    public DeleteDialog(JFrame parent) {
        super(parent, "Borrar Libro", true);
        initializeComponents();
        setSize(300, 200);
        setLocationRelativeTo(parent);
    }

    private void initializeComponents() {
        JPanel panel = new JPanel(new GridLayout(3, 2));

        panel.add(new JLabel("Identificador:"));
        idField = new JTextField();
        panel.add(idField);

        JButton searchButton = new JButton("Buscar");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBook();
            }
        });
        panel.add(searchButton);

        bookInfoArea = new JTextArea();
        bookInfoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(bookInfoArea);
        panel.add(scrollPane);

        JButton deleteButton = new JButton("Borrar");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBook();
            }
        });
        panel.add(deleteButton);

        getContentPane().add(panel);
    }

    private void searchBook() {
        try {
            int id = Integer.parseInt(idField.getText());
            Book foundBook = BookManager.getInstance().findBookById(id);

            if (foundBook != null) {
                // Mostrar la información del libro encontrado
                bookInfoArea.setText(foundBook.toString());
            } else {
                JOptionPane.showMessageDialog(this, "Libro no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un identificador válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteBook() {
        try {
            int id = Integer.parseInt(idField.getText());
            BookManager.getInstance().removeBook(id);

            JOptionPane.showMessageDialog(this, "Libro borrado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();  // Cierra el diálogo después del borrado exitoso
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un identificador válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

