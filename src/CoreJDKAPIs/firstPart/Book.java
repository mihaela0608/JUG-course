package CoreJDKAPIs.firstPart;

public class Book implements Cloneable{
    private String title;
    private String author;
    private Publisher publisher;
    private int pages;

    public Book(String title, String author, Publisher publisher, int pages) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.pages = pages;
    }

//    @Override
//    public Book clone(){
//        try {
//            return (Book) super.clone();
//        } catch (CloneNotSupportedException e) {
//            throw new RuntimeException(e);
//        }
//    }


    @Override
    protected Book clone(){
        try {
            return (Book) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %d", title, author, publisher.getName(), pages);
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
