package dat.nycupcakemarie.model.persistence;

import dat.nycupcakemarie.model.dtos.CartDTO;
import dat.nycupcakemarie.model.dtos.CustomerDTO;
import dat.nycupcakemarie.model.entities.Cupcakebuttom;
import dat.nycupcakemarie.model.entities.User;
import dat.nycupcakemarie.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserMapper implements IUserMapper {
    ConnectionPool connectionPool;

    public UserMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public User login(String email, String password) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        User user = null;

        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int user_id = Integer.parseInt(rs.getString("user_id"));
                    String roleIdString = rs.getString("role_id");
                    int roleId = Integer.parseInt(roleIdString);
                    String firstname = rs.getString("firstname");
                    String lastname = rs.getString("lastname");
                    int balance = Integer.parseInt(rs.getString("balance"));
                    int phone_no = Integer.parseInt(rs.getString("phone_no"));

                    user = new User(user_id, password, roleId, firstname, lastname, balance, phone_no, email);
                } else {
                    throw new DatabaseException("Wrong username or password");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
        return user;
    }

    @Override
    public User createUser(String email, String password, int roleId, String firstname, String lastname, int balanceInt, int phoneNo) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        User user;

        String user_id = "0";
        int user_idInt = Integer.parseInt(user_id);
        String phone_no = "" + phoneNo;
        String balance = "" + balanceInt;

        String role_id = "" + roleId;

        String sql = "insert into user (user_id, password, role_id, firstname, lastname, balance, phone_no, email) values (?,?,?,?,?,?,?,?)";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, user_id);
                ps.setString(2, password);
                ps.setString(3, role_id);
                ps.setString(4, firstname);
                ps.setString(5, lastname);
                ps.setString(6, balance);
                ps.setString(7, phone_no);
                ps.setString(8, email);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    user = new User(user_idInt, password, roleId, firstname, lastname, balanceInt, phoneNo, email);
                } else {
                    throw new DatabaseException("The user with username = " + email + " could not be inserted into the database");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not insert username into database");
        }
        return user;
    }

    public User getUserObject(int userId) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        String userIdS = "" + userId;

        User user = null;

        String sql = "SELECT * FROM user WHERE user_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, userIdS);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int user_id = rs.getInt("user_id");
                    String password = rs.getString("password");
                    int roleId = rs.getInt("role_id");
                    String firstname = rs.getString("firstname");
                    String lastname = rs.getString("lastname");
                    int balance = rs.getInt("balance");
                    int phone_no = rs.getInt("phone_no");
                    String email = rs.getString("email");

                    user = new User(user_id, password, roleId, firstname, lastname, balance, phone_no, email);
                } else {
                    throw new DatabaseException("Wrong userId");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Error updating user. Something went wrong with the database");
        }
        return user;
    }

    public List<CustomerDTO> getCustomerDTOList() throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        List<CustomerDTO> userList = new ArrayList<>();

        String sql = "SELECT * FROM user";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int user_id = rs.getInt("user_id");
//                    String password = rs.getString("password");
                    int role_id = rs.getInt("role_id");
                    String firstname = rs.getString("firstname");
                    String lastname = rs.getString("lastname");
                    int balance = rs.getInt("balance");
                    int phone_no = rs.getInt("phone_no");
                    String email = rs.getString("email");

                    CustomerDTO customerDTO = new CustomerDTO(user_id, role_id, firstname, lastname, balance, phone_no, email);
                    userList.add(customerDTO);
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Fejl under indlæsning af brugere fra databasen");
        }
        return userList;

    }

    public void depositToUser(int userId, int balanceInt) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        String userID = "" + userId;
        String balanceS = "" + balanceInt;

        String user_id = "" + userId;
        CustomerDTO customerDTO = null;

        String sql = "UPDATE user SET balance = ? WHERE user_id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, balanceS);
                ps.setString(2, user_id);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {

                } else {
                    throw new DatabaseException("Kontoen blev ikke opdateret");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Kunne ikke opdatere konto");
        }
    }

    public Map<Integer, CustomerDTO> getUserMap() throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        Map<Integer, CustomerDTO> userMap = new LinkedHashMap<>();

        String sql = "SELECT * FROM user";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int user_id = rs.getInt("user_id");
                    Integer userId = user_id;
//                    String password = rs.getString("password");
                    int role_id = rs.getInt("role_id");
                    String firstname = rs.getString("firstname");
                    String lastname = rs.getString("lastname");
                    int balance = rs.getInt("balance");
                    int phone_no = rs.getInt("phone_no");
                    String email = rs.getString("email");

                    CustomerDTO customerDTO = new CustomerDTO(user_id, role_id, firstname, lastname, balance, phone_no, email);
                    userMap.put(userId, customerDTO);
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Fejl under indlæsning af brugere fra databasen");
        }
        return userMap;

    }

    public Map<String, CustomerDTO> getUserMapByEmail() throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        Map<String, CustomerDTO> userMap = new LinkedHashMap<>();

        String sql = "SELECT * FROM user";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int user_id = rs.getInt("user_id");
                    Integer userId = user_id;
//                    String password = rs.getString("password");
                    int role_id = rs.getInt("role_id");
                    String firstname = rs.getString("firstname");
                    String lastname = rs.getString("lastname");
                    int balance = rs.getInt("balance");
                    int phone_no = rs.getInt("phone_no");
                    String email = rs.getString("email");

                    CustomerDTO customerDTO = new CustomerDTO(user_id, role_id, firstname, lastname, balance, phone_no, email);
                    userMap.put(email, customerDTO);
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Fejl under indlæsning af brugere fra databasen");
        }
        return userMap;

    }

}
