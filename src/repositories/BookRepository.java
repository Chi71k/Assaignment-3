package repositories;

import data.interfaces.IDB;
import models.Book;
import repositories.interfaces.IBookRepository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class BookRepository implements IBookRepository {
    private final IDB db;

    public BookRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createBook(Book book) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "INSERT INTO books(bookname, author, year, price) VALUES (?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, book.getBookname());
            st.setString(2, book.getAuthor());
            st.setInt(3, book.getYear());
            st.setInt(4, book.getPrice());

            st.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                System.out.println("sql error: " + e.getMessage());
            }
        }

        return false;
    }

    @Override
    public Book getBook(int id) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT id, bookname, author, year, price,borrow FROM books WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Book(rs.getInt("id"),
                        rs.getString("bookname"),
                        rs.getString("author"),
                        rs.getInt("year"),
                        rs.getInt("price"),
                        rs.getBoolean("borrow"));
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                System.out.println("sql error: " + e.getMessage());
            }
        }

        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT id,bookname, author, year, price,borrow FROM books";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Book> books = new LinkedList<>();
            while (rs.next()) {
                Book book = new Book(rs.getInt("id"),
                        rs.getString("bookname"),
                        rs.getString("author"),
                        rs.getInt("year"),
                        rs.getInt("price"),
                        rs.getBoolean("borrow"));

                books.add(book);
            }

            return books;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                System.out.println("sql error: " + e.getMessage());
            }
        }

        return null;
    }

    @Override
    public boolean alterBook(int bookId, String bookName, String author, int price, int year) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "UPDATE books SET bookname=?, author=?,year=?, price=? WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, bookName);
            st.setString(2, author);
            st.setInt(3, year);
            st.setInt(4, price);
            st.setInt(5, bookId);

            int rowsUpdated = st.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        } finally {
            try {
                if (con != null)
                    con.close();
            }
            catch (SQLException e) {
                System.out.println("sql error: " + e.getMessage());
            }
        }
        return false;
    }


    @Override
    public boolean removeBook(int bookId) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "DELETE FROM books WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, bookId);
            int rowsDeleted = st.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                System.out.println("sql error: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean borrowBook(int bookId, int userId) {
        Connection con = null;
        try {
            con = db.getConnection();

            // Проверяем, доступна ли книга для аренды
            String checkAvailabilityQuery = "SELECT borrow FROM books WHERE id = ?";
            PreparedStatement checkSt = con.prepareStatement(checkAvailabilityQuery);
            checkSt.setInt(1, bookId);
            ResultSet rs = checkSt.executeQuery();
            if (rs.next()) {
                boolean isBookAvailable = !rs.getBoolean("borrow");
                if (isBookAvailable) {
                    // Если книга доступна, обновляем информацию о книге
                    String sql = "UPDATE books SET borrow = true, borrower_id = ? WHERE id = ?";
                    PreparedStatement st = con.prepareStatement(sql);
                    st.setInt(1, userId);
                    st.setInt(2, bookId);
                    int rowsUpdated = st.executeUpdate();
                    return rowsUpdated > 0;
                } else {
                    System.out.println("Book with this id is already borrowed.Please choose another one");
                    return false; // Если книга уже арендована, завершаем метод
                }
            } else {
                System.out.println("Book with this id is not found!");
                return false;//Если книга не найдена то завершаем метод
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                System.out.println("sql error: " + e.getMessage());
            }
        }
        return false;
    }


    @Override
    public boolean returnBook(int bookId) {
        Connection con = null;
        try{
            con = db.getConnection();
            String sql = "UPDATE books SET borrow = false, borrower_id = null WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1,bookId);
            int rowsUpdated = st.executeUpdate();
            return rowsUpdated>0;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                System.out.println("sql error: " + e.getMessage());
            }
        }
        return false;
    }
}