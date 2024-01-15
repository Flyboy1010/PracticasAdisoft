import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateDialog extends JDialog {
    private JTextField idField, titleField, authorField, priceField;

    public UpdateDialog(JFrame parent) {
        super(parent, "Modificar Libro", true);
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

        JButton updateButton = new JButton("Modificar");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBook();
            }
        });
        panel.add(updateButton);

        getContentPane().add(panel);
    }

    private void enableComponentsEditable(boolean editable)
    {
        titleField.setEditable(editable);
        authorField.setEditable(editable);
        priceField.setEditable(editable);
    }

    private void searchBook() {
        try {
            int id = Integer.parseInt(idField.getText());
            Book foundBook = BookManager.getInstance().findBookById(id);

            if (foundBook != null) {
                // Rellenar los campos de texto con los valores actuales del libro
                titleField.setText(foundBook.getTitle());
                authorField.setText(foundBook.getAuthor());
                priceField.setText(String.valueOf(foundBook.getPrice()));

                // set editable components to true
                enableComponentsEditable(true);
            } else {
                JOptionPane.showMessageDialog(this, "Libro no encontrado", "Error", JOptionPane.ERROR_MESSAGE);

                // set editable components to false
                enableComponentsEditable(false);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un identificador válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateBook() {
        try {
            int id = Integer.parseInt(idField.getText());

            if (BookManager.getInstance().findBookById(id) != null) {

                Book book = new Book(id, titleField.getText(), authorField.getText(), Float.parseFloat(priceField.getText()));
                BookManager.getInstance().updateBook(book);

                JOptionPane.showMessageDialog(this, "Libro modificado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();

            } else {
                JOptionPane.showMessageDialog(this, "Libro no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            }

            dispose();  // Cierra el diálogo después de la modificación exitosa
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese valores válidos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
