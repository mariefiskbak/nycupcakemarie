package dat.nycupcakemarie.model.persistence;

import dat.nycupcakemarie.model.dtos.OrderlineDTO;
import dat.nycupcakemarie.model.entities.User;
import dat.nycupcakemarie.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderlineMapper {
    ConnectionPool connectionPool;

    public OrderlineMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public void insertOrderlineToDB(OrderlineDTO orderlineDTO) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        int order_id = orderlineDTO.getOrder_id();
        int quantity = orderlineDTO.getQuantity();
        int buttom_id = orderlineDTO.getButtom_id();
        int topping_id = orderlineDTO.getTopping_id();


//        String orderline_idS = "" + buttom_id + topping_id;
        //DONE: lave helt unikt id med kombi af orderId, buttomId og toppingID?
        String orderline_idS = "" + order_id + buttom_id + topping_id;
        int orderline_id = Integer.parseInt(orderline_idS);

        String quantityS = "" + quantity;
        String buttom_idS = "" + buttom_id;
        String topping_idS = "" + topping_id;
        String order_idS = "" + order_id;

        String sql = "insert into cupcakemmp.orderline (orderline_id, order_id, quantity, buttom_id, topping_id) values (?,?,?,?,?)";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, orderline_idS);
                ps.setString(2, order_idS);
                ps.setString(3, quantityS);
                ps.setString(4, buttom_idS);
                ps.setString(5, topping_idS);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    orderlineDTO = new OrderlineDTO(orderline_id, order_id, quantity, buttom_id, topping_id);
                } else {
                    throw new DatabaseException("Ordrelinjen med id: " + order_id + " kunne ikke puttes i databasen");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not insert orderline into database");
        }
    }


}
