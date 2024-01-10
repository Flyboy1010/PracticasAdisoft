import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateDialog extends JDialog {
    private JTextField idField, titleField, authorField, priceField;
    private JTextArea bookInfoArea;

    public UpdateDialog(JFrame parent) {
        super(parent, "Modificar Libro", true);
        initializeComponents();
        setSize(400, 300);
        setLocationRelativeTo(parent);
    }

    private void initializeComponents() {
        JPanel panel = new JPanel(new GridLayout(6, 3));

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

        panel.add(new JLabel("Título:"));
        titleField = new JTextField();
        panel.add(titleField);

        panel.add(new JLabel("Autor:"));
        authorField = new JTextField();
        panel.add(authorField);

        panel.add(new JLabel("Precio:"));
        priceField = new JTextField();
        panel.add(priceField);

        bookInfoArea = new JTextArea();
        bookInfoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(bookInfoArea);
        panel.add(scrollPane);

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

    private void searchBook() {
        try {
            int id = Integer.parseInt(idField.getText());
            Book foundBook = BookManager.getInstance().findBookById(id);

            if (foundBook != null) {
                // Mostrar la información del libro encontrado
                bookInfoArea.setText(foundBook.toString());
                // Rellenar los campos de texto con los valores actuales del libro
                titleField.setText(foundBook.getTitle());
                authorField.setText(foundBook.getAuthor());
                priceField.setText(String.valueOf(foundBook.getPrice()));
            } else {
                JOptionPane.showMessageDialog(this, "Libro no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un identificador válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateBook() {
        try {
            int id = Integer.parseInt(idField.getText());
            String title = titleField.getText();
            String author = authorField.getText();
            float price = Float.parseFloat(priceField.getText());

            Book updatedBook = new Book(id, title, author, price);
            BookManager.getInstance().updateBook(updatedBook);

            JOptionPane.showMessageDialog(this, "Libro modificado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();  // Cierra el diálogo después de la modificación exitosa
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese valores válidos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
