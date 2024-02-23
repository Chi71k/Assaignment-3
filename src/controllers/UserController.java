package controllers;
import models.User;
import repositories.interfaces.IUsersRepository;

import java.util.List;

public class UserController {
    private final IUsersRepository repo;

    public UserController(IUsersRepository repo){
        this.repo = repo;
    }
    public String Register(String username,String phone,String email,String password,String role){
        User user = new User(username,phone,email,password,role);
        boolean registered = repo.Register(user);
        return (registered ? "User was registered!":"Registration failed");
    }
    public String getAllUsers(){
        List<User> users = repo.getAllUsers();

        StringBuilder response = new StringBuilder();
        for (User user : users) {
            response.append(user.toString()).append("\n");
        }

        return response.toString();
    }
    public String logIn(String username,String password){
        boolean loggedIn = repo.logIn(username,password);
        if (loggedIn){
            String role = repo.getUserRole(username);
            return role;
        }else{
            return "Login failed.Incorrect username or pasword";
        }
    }

    public String getUserRole(String username){
        return repo.getUserRole(username);
    }
    public int getUserId(String username){
        return repo.getUserId(username);
    }
}
