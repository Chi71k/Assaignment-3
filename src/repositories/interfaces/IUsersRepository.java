package repositories.interfaces;
import models.Book;
import models.User;
import java.util.List;

public interface IUsersRepository {
    boolean Register(User user);
    List<User> getAllUsers();
    boolean logIn(String username,String password);
    String getUserRole(String username);
    int getUserId(String username);
}
