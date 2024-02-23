package repositories;

import data.interfaces.IDB;
import models.Book;
import models.User;
import repositories.interfaces.IUsersRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserRepository implements IUsersRepository {

    private final IDB db;
    public UserRepository(IDB db){
        this.db=db;
    }

    @Override
    public boolean Register(User user) {
        Connection con = null;
        try{
            con = db.getConnection();
            String sql = "INSERT INTO user_db(username,phone,email,password,role) VALUES (?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, user.getUserName());
            st.setString(2, user.getPhone());
            st.setString(3, user.getEmail());
            st.setString(4, user.getPassword());
            st.setString(5, user.getRole());
            st.execute();
            return true;
        }catch (SQLException e){
            System.out.println("sql error: " + e.getMessage());
        }finally {
            try {
                if(con!=null)
                    con.close();
            }catch (SQLException e){
                System.out.println("sql error: " + e.getMessage());
            }
        }
        return false;
    }


    @Override
    public List<User> getAllUsers() {
        Connection con = null;
        try{
            con = db.getConnection();
            String sql = "SELECT id,username,phone,email,password,role FROM user_db";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            List<User> users = new LinkedList<>();
            while(rs.next()){
                User user = new User(rs.getInt("id"),rs.getString("username"),
                        rs.getString("phone"),rs.getString("email"),rs.getString("password"),rs.getString("role"));
                users.add(user);
            }
            return users;
        }catch(SQLException e){
            System.out.println("sql error: " + e.getMessage());
        }finally {
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
    public boolean logIn(String username,String password){
        Connection con = null;
        try{
            con = db.getConnection();
            String sql = "SELECT * FROM user_db where username = ? AND password = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,username);
            st.setString(2,password);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (SQLException e){
            System.out.println("sql error: " +e.getMessage());
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

    @Override
    public String getUserRole(String username) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT role FROM user_db WHERE username = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString("role");
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return "user"; // Возвращаем роль "user", если пользователь не найден или произошла ошибка
    }

    @Override
    public int getUserId(String username) {
        Connection con = null;
        try{
            con = db.getConnection();
            String sql = "SELECT id FROM user_db WHERE username = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,username);
            ResultSet rs = st.executeQuery();
            if (rs.next()){
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return 1;
    }

}
