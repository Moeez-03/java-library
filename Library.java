import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<Book> books;
    private String fileName;

    public Library(String fileName) {
        this.books = new ArrayList<Book>();
        this.fileName = fileName;
    }

    public void loadBooks() {
        File file = new File(this.fileName);
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    Book book = new Book(data[0], data[1], data[2], data[3], data[4]);
                    this.books.add(book);
                }
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        } else {
            System.out.println("File does not exist.");
        }
    }

    public void saveBooks() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.fileName))) {
            for (Book book : this.books) {
                bw.write(book.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public void removeBook(Book book) {
        this.books.remove(book);
    }

    public List<Book> searchBooks(String keyword) {
        List<Book> result = new ArrayList<Book>();
        for (Book book : this.books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())
                    || book.getAuthor().toLowerCase().contains(keyword.toLowerCase())
                    || book.getIsbn().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> getBooks() {
        return this.books;
    }

    public static void main(String[] args) {
        Library library = new Library("books.txt");
        library.loadBooks();
        System.out.println("Library loaded successfully.");

        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "1925", "Scribner", "978-3-16-148410-0");
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", "1960", "J. B. Lippincott & Co.",
                "978-3-16-148410-1");

        library.addBook(book1);
        library.addBook(book2);
        System.out.println("Books added successfully.");

        library.saveBooks();
        System.out.println("Books saved successfully.");

        List<Book> result = library.searchBooks("great");
        System.out.println("Search results:");
        for (Book book : result) {
            System.out.println(book);
        }
    }
}

class Book {
    private String title;
    private String author;
    private String year;
    private String publisher;
    private String isbn;

    public Book(String title, String author, String year, String publisher, String isbn) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.publisher = publisher;
        this.isbn = isbn;
    }

    // getter methods for each field
    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getYear() {
        return this.year;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public String getIsbn() {
        return this.isbn;
    }

    // toString method for printing book information
    public String toString() {
        return this.title + " by " + this.author + " (" + this.year + ")";
    }

    // toCSV method for writing book information to a CSV file
    public String toCSV() {
        return this.title + "," + this.author + "," + this.year + "," + this.publisher + "," + this.isbn;
    }
}
