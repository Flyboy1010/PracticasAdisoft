import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateDialog extends JDialog {
    private JTextField idField, titleField, authorField, priceField;

    public CreateDialog(JFrame parent) {
        super(parent, "Crear Libro", true);
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
        panel.add(titleField);

        panel.add(new JLabel("Autor:"));
        authorField = new JTextField();
        panel.add(authorField);

        panel.add(new JLabel("Precio:"));
        priceField = new JTextField();
        panel.add(priceField);

        JButton createButton = new JButton("Crear");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewBook();
            }
        });

        panel.add(createButton);

        getContentPane().add(panel);
    }

    private void createNewBook() {
        try {
            int id = Integer.parseInt(idField.getText());
            String title = titleField.getText();
            String author = authorField.getText();
            float price = Float.parseFloat(priceField.getText());

            // check if id already exists (then throw exception)
            Book book = BookManager.getInstance().findBookById(id);
            if (book != null) {
                throw new DuplicateIdException("Id " + id + " already exists");
            }

            Book newBook = new Book(id, title, author, price);
            BookManager.getInstance().addBook(newBook);

            JOptionPane.showMessageDialog(this, "Libro creado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();  // Cierra el diálogo después de la creación exitosa
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese valores válidos para ID y Precio", "Error", JOptionPane.ERROR_MESSAGE);

        } catch (DuplicateIdException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

