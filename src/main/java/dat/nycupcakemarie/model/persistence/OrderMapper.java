package dat.nycupcakemarie.model.persistence;

import dat.nycupcakemarie.model.dtos.CustomerDTO;
import dat.nycupcakemarie.model.dtos.OrderDTO;
import dat.nycupcakemarie.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderMapper {
    ConnectionPool connectionPool;

    public OrderMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public int getLatestOrderId() throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        int orderId = 0;

        String sql = "SELECT MAX(order_id) AS maxorderid FROM cupcakemmp.order";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    orderId = rs.getInt("maxorderid");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Fejl under indlæsning af orderId fra databasen");
        }
        return orderId;
    }

    public void insertOrderToDB(int order_id, int user_id, int total_price, Timestamp timestamp, int status_id) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        OrderDTO orderDTO;

        String order_idS = "" + order_id;
        String user_idS = "" + user_id;
        String total_priceS = "" + total_price;
        String timestampS = "" + timestamp;
        String status_idS = "" + status_id;

        String sql = "insert into cupcakemmp.order (order_id, user_id, total_price, timestamp, status_id) values (?,?,?,?,?)";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, order_idS);
                ps.setString(2, user_idS);
                ps.setString(3, total_priceS);
                ps.setString(4, timestampS);
                ps.setString(5, status_idS);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    orderDTO = new OrderDTO(order_id, user_id, total_price, timestamp, status_id);
                } else {
                    throw new DatabaseException("Ordren med ordreId: " + order_id + " kunne ikke indsættes i databasen");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not insert username into database");
        }
    }

    public List<OrderDTO> getOrderDTOList() throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        List<OrderDTO> orderList = new ArrayList<>();

        String sql = "SELECT * FROM cupcakemmp.order";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int order_id = rs.getInt("order_id");
                    int user_id = rs.getInt("user_id");
                    int total_price = rs.getInt("total_price");
                    Timestamp timestamp = rs.getTimestamp("timestamp");
                    int status_id = rs.getInt("status_id");

                    OrderDTO orderDTO = new OrderDTO(order_id, user_id, total_price, timestamp, status_id);
                    orderList.add(orderDTO);
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Fejl under indlæsning af ordrer fra databasen");
        }
        return orderList;

    }

    public List<OrderDTO> getJoinedOrderDTOList() throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        List<OrderDTO> orderList = new ArrayList<>();

        String sql = "SELECT cupcakemmp.order.order_id, cupcakemmp.order.timestamp, cupcakemmp.order.user_id, cupcakemmp.user.firstname, cupcakemmp.user.lastname, cupcakemmp.order.total_price, cupcakemmp.orderstatus.name FROM cupcakemmp.order INNER JOIN cupcakemmp.user ON cupcakemmp.order.user_id=cupcakemmp.user.user_id INNER JOIN cupcakemmp.orderstatus ON cupcakemmp.order.status_id=cupcakemmp.orderstatus.status_id WHERE NOT cupcakemmp.order.status_id='3' ORDER BY cupcakemmp.order.order_id ASC";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int order_id = rs.getInt("order_id");
                    Timestamp timestamp = rs.getTimestamp("timestamp");
                    int user_id = rs.getInt("user_id");
                    String firstname = rs.getString("firstname");
                    String lastname = rs.getString("lastname");
                    int total_price = rs.getInt("total_price");
                    String orderStatus = rs.getString("name");


                    OrderDTO orderDTO = new OrderDTO(order_id, timestamp, user_id, firstname, lastname, total_price,  orderStatus);
                    orderList.add(orderDTO);
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Fejl under indlæsning af ordrer fra databasen");
        }
        return orderList;

    }


    public List<OrderDTO> getJoinedOrderDTOListByUserId(int userId) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        String userIdS = "" + userId;
        List<OrderDTO> orderList = new ArrayList<>();

        String sql = "SELECT cupcakemmp.order.order_id, cupcakemmp.order.timestamp, SUM(cupcakemmp.orderline.quantity) AS quantity, cupcakemmp.order.total_price FROM cupcakemmp.order INNER JOIN cupcakemmp.orderline ON cupcakemmp.order.order_id=cupcakemmp.orderline.order_id INNER JOIN cupcakemmp.user ON cupcakemmp.order.user_id=cupcakemmp.user.user_id WHERE cupcakemmp.user.user_id = ? GROUP BY cupcakemmp.order.order_id ORDER BY cupcakemmp.order.order_id ASC";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, userIdS);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int order_id = rs.getInt("order_id");
                    Timestamp timestamp = rs.getTimestamp("timestamp");
                    int quantity = rs.getInt("quantity");
                    int total_price = rs.getInt("total_price");

                    OrderDTO orderDTO = new OrderDTO(order_id, timestamp, quantity, total_price);
                    orderList.add(orderDTO);
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Fejl under indlæsning af ordrer fra databasen");
        }
        return orderList;

    }

    public void deleteOrder(int orderId) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        String orderID = "" + orderId;

        String order_id = "" + orderId;
        OrderDTO orderDTO = null;

        String sql = "UPDATE cupcakemmp.order SET cupcakemmp.order.status_id = '3' WHERE order_id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, order_id);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {

                } else {
                    throw new DatabaseException("Ordren blev ikke opdateret");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Kunne ikke opdatere ordre");
        }
    }

    public void changeOrder(int orderId) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        String order_id = "" + orderId;

        String sql = "UPDATE cupcakemmp.order SET cupcakemmp.order.status_id = CASE WHEN cupcakemmp.order.status_id = '1' THEN '2' WHEN cupcakemmp.order.status_id = '2' THEN '1' END WHERE cupcakemmp.order.order_id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, order_id);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {

                } else {
                    throw new DatabaseException("Ordren blev ikke opdateret");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Kunne ikke opdatere ordre");
        }
    }


}
