package dat.nycupcakemarie.model.persistence;

import dat.nycupcakemarie.model.dtos.OrderDTO;
import dat.nycupcakemarie.model.entities.Cupcakebuttom;
import dat.nycupcakemarie.model.entities.User;
import dat.nycupcakemarie.model.exceptions.DatabaseException;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Fejl under indlæsning af orderId fra databasen");
        }
        return orderId;
    }

    public void insertOrderToDB(int order_id, int user_id, int total_price, int timestamp, int status_id) throws DatabaseException {
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

}
