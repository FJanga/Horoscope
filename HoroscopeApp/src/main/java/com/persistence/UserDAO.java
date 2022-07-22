package com.persistence;

import com.Utils.ConnectionManager;
import com.Utils.CustomCRUDInterface;
import com.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDAO implements CustomCRUDInterface<User>{
    Connection connection;
    private String mood;
    private String User;

    public UserDAO(){
        connection = ConnectionManager.getConnection();
    }


    @Override
    public Integer create(User user) {

        try {
            String sql = "INSERT INTO users (user_id,first_name,last_name,email,username,password,zodiac_sign) VALUES (default,?,?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,user.getFirst_name());
            pstmt.setString(2, user.getLast_name());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUsername());
            pstmt.setString(5, user.getPass_word());
            pstmt.setString(6, user.getZodiac_sign());
            pstmt.setString(7, user.getMood());


            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            rs.next();
            return rs.getInt("user_id");

        } catch(Exception e){
            System.out.println("Error in the user Dao: " + e.getMessage());
        }
        return null;
    }

    @Override
    public User read(Integer id) {

        try {

            String sql = "SELECT * FROM horoscopes WHERE userid = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, id);

            User user = new User();

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                user.setUser_Id(rs.getInt("userid"));
                user.setFirst_name(rs.getString("firstname"));
                user.setLast_name(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPass_word(rs.getString("user_password"));
                user.setZodiac_sign(rs.getString("zodiac"));
                user.setMood(rs.getString("mood"));
            }

            return user;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }


    @Override
    public User update(User user) {

        try {
            String sql = "UPDATE Zodiac SET mood = ? WHERE userid = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


//            pstmt.setString(1, horoscopeUser.getUser_password());
//            pstmt.setString(2, horoscopeUser.getFirstname());
//            pstmt.setString(3, horoscopeUser.getLastname());
//            pstmt.setString(4, horoscopeUser.getEmail());
//            pstmt.setString(5, horoscopeUser.getZodiac());
            pstmt.setString(1, user.getMood());
            pstmt.setInt(2, user.getUser_Id());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            while (rs.next()) {

                user.setUser_Id(rs.getInt("userId"));
                user.setFirst_name(rs.getString("firstname"));
                user.setLast_name(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPass_word(rs.getString("user_password"));
                user.setZodiac_sign(rs.getString("zodiac"));
                user.setMood(rs.getString("mood"));
            }

            return user;

        }  catch (Exception e){

        }
        return null;
    }

    @Override
    public boolean delete(Integer Id) {
        try {
            String sql = "DELETE FROM horoscopes WHERE userid = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, Id);

            return pstmt.execute();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }





    public User loginUser(String email, String password){

        try {

            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,email);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next() && rs.getString("last_name").equals(password)){
                return new User(rs.getInt("user_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("pass_word"),
                        rs.getString("zodiac_sign"),
                        rs.getString("mood"));
            }


        }catch(Exception e){
            System.out.println("This is the userDAO: " + e.getMessage());
        }

        return null;
    }
}
