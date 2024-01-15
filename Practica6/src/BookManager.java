import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class BookManager {
    private static BookManager instance;
    private List<Book> books = new ArrayList<>();

    // sql vainas
    Connection myConnection = null;

    // Constructor privado para evitar la creación de instancias fuera de la clase
    private BookManager() {
        // connect to the db

        Connect("a776900", "exopte40");

        // load books from the db

        LoadBooks();
    }

    // Método estático para obtener la instancia (o crearla si aún no existe)
    public static BookManager getInstance() {
        if (instance == null) {
            instance = new BookManager();
        }
        return instance;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        // add it to the list

        books.add(book);

        // add it to the db

        String query = String.format(Locale.US,"INSERT INTO Books VALUES (%d, '%s', '%s', %.2f)", book.getId(), book.getTitle(), book.getAuthor(), book.getPrice());
        executeSQL(query);
    }

    public void removeBook(int id) {
        // remove from the list

        books.removeIf(book -> book.getId() == id);
        
        executeSQL(String.format("DELETE FROM Books WHERE id = %d", id));
    }

    public void updateBook(Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == updatedBook.getId()) {
                books.set(i, updatedBook);
                // update in the db

                String query = String.format(Locale.US, "UPDATE Books SET titulo = '%s', autor = '%s', precio = %.2f WHERE id = %d", updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getPrice(), updatedBook.getId());
                System.out.println(query);
                executeSQL(query);

                // exit
                return;
            }
        }
    }

    public Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    // establecer la conexion con la db

    private void Connect(String username, String password) {
        // Cargamos el driver JDBC de Oracle
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (Exception e) {
            System.err.println("No puedo encontrar el driver JDBC.");
        }

        // conectarse

        try {
            myConnection = DriverManager.getConnection("jdbc:oracle:thin:@danae04.unizar.es:1521:barret", username, password);
            System.out.println("La conexion se ha establecido");

        } catch (SQLException  e) {
            System.err.println("No se ha podido establecer la conexion");
        }
    }

    // ejecuta un comando sql

    private void executeSQL(String query) {
        Statement theStatement = null;
        ResultSet theSet = null;

        // cargamos los libros
        try {
            theStatement = myConnection.createStatement();
            theSet = theStatement.executeQuery(query);
        } catch (SQLException  e) {
            System.err.println(e);
        } finally {
            try {
                if (theSet != null) {
                    theSet.close();
                }
            } catch (SQLException e) {
                // e.printStackTrace();
                System.err.println("No se ha podido cerrar theSet.");
            }

            try {
                if (theStatement != null) {
                    theStatement.close();
                }
            } catch (SQLException e) {
                // e.printStackTrace();
                System.err.println("No se ha podido cerrar theStatement.");
            }
        }
    }

    // cargar los libros de la db

    private void LoadBooks() {
        Statement theStatement = null;
        ResultSet theSet = null;

        // cargamos los libros
        try {
            theStatement = myConnection.createStatement();
            theSet = theStatement.executeQuery("SELECT * FROM Books");

            //Para iterar en el ResultSet:
            while (theSet.next())
            {
                int id = Integer.parseInt(theSet.getString("id"));
                String title = theSet.getString("titulo");
                String author = theSet.getString("autor");
                float price = Float.parseFloat(theSet.getString("precio"));

                System.out.println(id + " " + title + " " + author + " " + price);

                Book book = new Book(id, title, author, price);
                books.add(book);
            }

            // sort books

            books.sort(Comparator.comparingInt(Book::getId));

        } catch (SQLException  e) {
            System.err.println(e);

        } finally {

            // Al finalizar el programa, cerramos los recursos
            try {
                if (theSet != null) {
                    theSet.close();
                }
            } catch (SQLException e) {
                // e.printStackTrace();
                System.err.println("No se ha podido cerrar theSet.");
            }

            try {
                if (theStatement != null) {
                    theStatement.close();
                }
            } catch (SQLException e) {
                // e.printStackTrace();
                System.err.println("No se ha podido cerrar theStatement.");
            }
        }
    }
}
