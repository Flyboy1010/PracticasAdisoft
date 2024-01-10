import java.util.ArrayList;
import java.util.List;

public class BookManager {
    private static BookManager instance;
    private List<Book> books = new ArrayList<>();

    // Constructor privado para evitar la creación de instancias fuera de la clase
    private BookManager() {
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
        books.add(book);
    }

    public void removeBook(int id) {
        books.removeIf(book -> book.getId() == id);
    }

    public Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    public void updateBook(Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == updatedBook.getId()) {
                books.set(i, updatedBook);
                return;
            }
        }
    }
}
