
public class BookLibrary {

    public String ISBN;
    protected String title;
    private String author;

    public BookLibrary(String ISBN, String title, String author) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }
}

class EBook extends BookLibrary {

    public EBook(String ISBN, String title, String author) {
        super(ISBN, title, author);
    }

    void showDetails() {
        System.out.println(ISBN + " " + title);
    }
}
