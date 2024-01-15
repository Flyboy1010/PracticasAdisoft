import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteDialog extends JDialog {
    private JTextField idField, titleField, authorField, priceField;

    public DeleteDialog(JFrame parent) {
        super(parent, "Borrar Libro", true);
        initializeComponents();
        setSize(300, 200);
        setLocationRelativeTo(parent);
    }

    private void initializeComponents() {
        JPanel panel = new JPanel(new GridLayout(5, 2));

        panel.add(new JLabel("ID:"));
        idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("Título:"));
        titleField = new JTextField();
        titleField.setEditable(false);
        panel.add(titleField);

        panel.add(new JLabel("Autor:"));
        authorField = new JTextField();
        authorField.setEditable(false);
        panel.add(authorField);

        panel.add(new JLabel("Precio:"));
        priceField = new JTextField();
        priceField.setEditable(false);
        panel.add(priceField);

        JButton searchButton = new JButton("Buscar");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBook();
            }
        });
        panel.add(searchButton);

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
                titleField.setText(foundBook.getTitle());
                authorField.setText(foundBook.getAuthor());
                priceField.setText(Float.toString(foundBook.getPrice()));
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
            Book foundBook = BookManager.getInstance().findBookById(id);

            if (foundBook != null) {
                BookManager.getInstance().removeBook(foundBook.getId());
                JOptionPane.showMessageDialog(this, "Libro borrado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Libro no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un identificador válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

