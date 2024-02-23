package models;

public class Book {
    private int id;
    private String bookname;
    private String author;
    private int year;
    private int price;
    private boolean borrow;


    public Book(String bookname, String author, int year, int price,boolean borrow) {
        setBookname(bookname);
        setAuthor(author);
        setYear(year);
        setPrice(price);
        setBorrow(false);
    }

    public Book(int id, String bookname, String author, int year, int price,boolean borrow) {
        this(bookname, author, year, price,borrow);
        setId(id);
    }
    public Book(String bookname, String author, int year, int price) {
        setBookname(bookname);
        setAuthor(author);
        setYear(year);
        setPrice(price);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setBorrow(boolean borrow) {
        this.borrow = borrow;
    }
    public String getBorrow(){
        return "false";
    }

    @Override
    public String toString() {
        return "Book info: " + '\n' +
                "id: " + id + '\n' +
                "the title of the book: " + bookname + '\n' +
                "author: " + author + '\n' +
                "the year of the book's release: " + year + '\n' +
                "the cost of the book: " + price + '\n';
    }
}
