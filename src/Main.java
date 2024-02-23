import controllers.BookController;
import controllers.UserController;
import data.PostgresDB;
import data.interfaces.IDB;
import repositories.BookRepository;
import repositories.UserRepository;
import repositories.interfaces.IBookRepository;
import repositories.interfaces.IUsersRepository;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        String connectionUrl = "jdbc:postgresql://localhost:5432/SimpleDB";
        Connection con = null;
        ResultSet rs = null;
        ResultSet rs_1 = null;
        Statement stmt = null;
        Statement stmt1 = null;
        try {
            Class.forName("org.postgresql.Driver");

            con = DriverManager.getConnection(connectionUrl, "postgres", "Eroha100!");

            stmt = con.createStatement();
            stmt1 = con.createStatement();

            rs_1 = stmt1.executeQuery("select * from user_db");
            rs = stmt.executeQuery("select * from books");

            while(rs_1.next())
                System.out.println(rs_1.getInt("id")+"\n"
                        +rs_1.getString("username") + "\n"
                        +rs_1.getString("phone")+"\n"
                        +rs_1.getString("email")+"\n"
                        +rs_1.getString("password"));

            while (rs.next())
                System.out.println(rs.getInt("id") + "\n"
                        + rs.getString("bookname") + "\n"
                        + rs.getString("author") + "\n"
                        + rs.getInt("year") + "\n"
                        + rs.getInt("price"));

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Exception occurred!");
            }
        }

        System.out.println("Finished!");

        IDB db = new PostgresDB();
        IBookRepository repo = new BookRepository(db);
        BookController controller = new BookController(repo);
        IUsersRepository repo_1 = new UserRepository(db);
        UserController u_controller = new UserController(repo_1);
        MyApplication app = new MyApplication(controller,u_controller);
        app.start();
    }
}