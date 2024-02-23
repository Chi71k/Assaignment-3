package models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String username;
    private String phone;
    private String email;
    private String password;
    private String role;
    private String history;
    List<Book> borrowedBooks;

    public User(int id,String username, String phone, String email,String password,String role) {
        setUserName(username);
        setPhone(phone);
        setEmail(email);
        setPassword(password);
        setId(id);
        borrowedBooks = new ArrayList<>();
    }
    public User(String username, String phone, String email,String password,String role){
        setUserName(username);
        setPhone(phone);
        setEmail(email);
        setPassword(password);
        setRole("user");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }
    public void logIn(String username,String password){
    }
    public String getRole(){
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public List<Book> getBorrowedBooks(){
        return borrowedBooks;
    }
    public void setBorrowedBooks(List<Book> borrowedBooks){
        this.borrowedBooks = borrowedBooks;
    }

    @Override
    public String toString() {
        return "User{"+"id"+id+" , username="+username+" ,phone="+phone+" ,email"+email+", password"+password+"}";
    }
}
