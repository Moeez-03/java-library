public class DVD extends LibraryItem {
    private int duration;

    public DVD(String title, String author, String isbn, int duration) {
        super(title, author, isbn);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public void display() {
        System.out.println("DVD title: " + getTitle());
        System.out.println("Director: " + getAuthor());
        System.out.println("ISBN: " + getIsbn());
        System.out.println("Duration: " + duration + " minutes");
        System.out.println("Available: " + isAvailable());
    }
}

