package controllers;

import models.Book;
import repositories.interfaces.IBookRepository;

import java.util.List;

public class BookController {
    private final IBookRepository repo;

    public BookController(IBookRepository repo) {
        this.repo = repo;
    }

    public String createBook(String bookname, String author, int year, int price) {
        Book book = new Book(bookname, author, year, price);

        boolean created = repo.createBook(book);

        return (created ? "Book was created!" : "Book creation was failed!");
    }

    public String getBook(int id) {
        Book book = repo.getBook(id);

        return (book == null ? "Book was not found!" : book.toString());
    }

    public String getAllBooks() {
        List<Book> books = repo.getAllBooks();

        StringBuilder response = new StringBuilder();
        for (Book book : books) {
            response.append(book.toString()).append("\n");
        }

        return response.toString();
    }

    public String removeBook(int bookId) {
        boolean removed = repo.removeBook(bookId);
        return removed ? "Book removed successfully!" : "Failed to remove the book.";
    }

    public String borrowBook(int bookId, int userId) {
        boolean borrowed = repo.borrowBook(bookId, userId);
        return (borrowed ? "Book borrowed successfully!" : "Failed to borrow the book.");
    }

    public String returnBook(int bookId) {
        boolean returned = repo.returnBook(bookId);
        return (returned ? "Book returned successfully!" : "Failed to return the book.");
    }

    public String alterBook(int bookId, String bookName, String author, int price, int year) {
        boolean altered = repo.alterBook(bookId, bookName, author, price, year);
        return (altered ? "Book details updated successfully!" : "Failed to update book details.");
    }
}
