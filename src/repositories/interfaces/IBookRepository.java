package repositories.interfaces;

import models.Book;

import java.util.List;

public interface IBookRepository {
    boolean createBook(Book book);
    Book getBook(int id);
    List<Book> getAllBooks();
    boolean alterBook(int bookId,String bookname,String author,int price,int year);
    boolean removeBook(int bookId);
    boolean borrowBook(int bookId,int userId);
    boolean returnBook(int bookId);
}
