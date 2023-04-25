public class Book extends LibraryItem {
    private int pages;

    public Book(String title, String author, String isbn, int pages) {
        super(title, author, isbn);
        this.pages = pages;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public void display() {
        System.out.println("Book title: " + getTitle());
        System.out.println("Author: " + getAuthor());
        System.out.println("ISBN: " + getIsbn());
        System.out.println("Pages: " + pages);
        System.out.println("Available: " + isAvailable());
    }
}
