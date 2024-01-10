import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ListDialog extends JDialog {

    public ListDialog(JFrame parent) {
        super(parent, "Lista de Libros", true);
        initializeComponents();
        setSize(600, 400);
        setLocationRelativeTo(parent);
    }

    private void initializeComponents() {
        JPanel panel = new JPanel(new BorderLayout());

        // Crea un modelo de tabla sin datos
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Título");
        tableModel.addColumn("Autor");
        tableModel.addColumn("Precio");

        // get books
        for (Book book : BookManager.getInstance().getBooks()) {
            // Obtener los atributos del libro
            int id = book.getId();
            String title = book.getTitle();
            String author = book.getAuthor();
            float price = book.getPrice();

            // Agregar filas de ejemplo (puedes reemplazar esto con tus datos reales)
            tableModel.addRow(new Object[]{id, title, author, price});
        }

        // Crea la tabla con el modelo de tabla
        JTable bookTable = new JTable(tableModel);

        // Agrega la tabla a un panel de desplazamiento
        JScrollPane scrollPane = new JScrollPane(bookTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(panel);
    }
}
