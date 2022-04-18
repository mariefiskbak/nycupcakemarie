package dat.nycupcakemarie.model.persistence;

import dat.nycupcakemarie.model.dtos.OrderDTO;
import dat.nycupcakemarie.model.dtos.OrderlineDTO;
import dat.nycupcakemarie.model.entities.User;
import dat.nycupcakemarie.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

    public List<OrderlineDTO> getOrderlineDTOList() throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        List<OrderlineDTO> orderlineList = new ArrayList<>();

        String sql = "SELECT * FROM cupcakemmp.orderline";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int orderline_id = rs.getInt("orderline_id");
                    int order_id = rs.getInt("order_id");
                    int quantity = rs.getInt("quantity");
                    int buttom_id = rs.getInt("buttom_id");
                    int topping_id = rs.getInt("topping_id");

                    OrderlineDTO orderlineDTO = new OrderlineDTO(orderline_id, order_id, quantity, buttom_id, topping_id);
                    orderlineList.add(orderlineDTO);
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Fejl under indlæsning af ordrelinjer fra databasen");
        }
        return orderlineList;

    }

    public List<OrderlineDTO> getJoinedOrderlineDTOList() throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        List<OrderlineDTO> orderlineList = new ArrayList<>();

        String sql = "SELECT cupcakemmp.orderline.orderline_id, cupcakemmp.orderline.order_id, cupcakemmp.orderline.quantity, cupcakemmp.cupcakebuttom.flavor AS buttom, cupcakemmp.cupcaketopping.flavor AS topping FROM cupcakemmp.orderline INNER JOIN cupcakemmp.cupcakebuttom ON cupcakemmp.orderline.buttom_id=cupcakemmp.cupcakebuttom.buttom_id INNER JOIN cupcakemmp.cupcaketopping ON cupcakemmp.orderline.topping_id=cupcakemmp.cupcaketopping.topping_id INNER JOIN cupcakemmp.order ON cupcakemmp.orderline.order_id=cupcakemmp.order.order_id WHERE NOT cupcakemmp.order.status_id='3' ORDER BY cupcakemmp.orderline.order_id ASC";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int orderline_id = rs.getInt("orderline_id");
                    int order_id = rs.getInt("order_id");
                    int quantity = rs.getInt("quantity");
                    String buttom = rs.getString("buttom");
                    String topping = rs.getString("topping");

                    OrderlineDTO orderlineDTO = new OrderlineDTO(orderline_id, order_id, quantity, buttom, topping);
                    orderlineList.add(orderlineDTO);
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Fejl under indlæsning af ordrelinjer fra databasen");
        }
        return orderlineList;

    }

    public Map<Integer, List<OrderlineDTO>> getJoinedOrderlineDTOListMap() throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        Map<Integer, List<OrderlineDTO>> orderlineListMap = new LinkedHashMap();
        List<OrderlineDTO> orderlineDTOList = null;
        int x = 0;

        String sql = "SELECT cupcakemmp.orderline.orderline_id, cupcakemmp.orderline.order_id, cupcakemmp.orderline.quantity, cupcakemmp.cupcakebuttom.flavor AS buttom, cupcakemmp.cupcaketopping.flavor AS topping FROM cupcakemmp.orderline INNER JOIN cupcakemmp.cupcakebuttom ON cupcakemmp.orderline.buttom_id=cupcakemmp.cupcakebuttom.buttom_id INNER JOIN cupcakemmp.cupcaketopping ON cupcakemmp.orderline.topping_id=cupcakemmp.cupcaketopping.topping_id INNER JOIN cupcakemmp.order ON cupcakemmp.orderline.order_id=cupcakemmp.order.order_id WHERE NOT cupcakemmp.order.status_id='3' ORDER BY cupcakemmp.orderline.order_id ASC";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int orderline_id = rs.getInt("orderline_id");
                    int order_id = rs.getInt("order_id");
                    int quantity = rs.getInt("quantity");
                    String buttom = rs.getString("buttom");
                    String topping = rs.getString("topping");



                    Integer orderId = order_id;

                    //DONE: noget logik der får den til at kun at putte de linjer med samme ordrenummer i samme liste

                    if(order_id != x) {
                        orderlineDTOList = new ArrayList<>();
                    }

                    x = order_id;
                    OrderlineDTO orderlineDTO = new OrderlineDTO(orderline_id, order_id, quantity, buttom, topping);
                    orderlineDTOList.add(orderlineDTO);

                    orderlineListMap.put(orderId, orderlineDTOList);
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Fejl under indlæsning af ordrelinjer fra databasen");
        }
        return orderlineListMap;

    }



    public Map<Integer, List<OrderlineDTO>> getOrderlineDTOListMap() throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        Map<Integer, List<OrderlineDTO>> orderlineListMap = new LinkedHashMap();
        List<OrderlineDTO> orderlineDTOList = null;
        int x = 0;

        String sql = "SELECT * FROM cupcakemmp.orderline";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int orderline_id = rs.getInt("orderline_id");
                    int order_id = rs.getInt("order_id");
                    int quantity = rs.getInt("quantity");
                    int buttom_id = rs.getInt("buttom_id");
                    int topping_id = rs.getInt("topping_id");

                    Integer orderId = order_id;

                    //DONE: noget logik der får den til at kun at putte de linjer med samme ordrenummer i samme liste

                    if(order_id != x) {
                        orderlineDTOList = new ArrayList<>();
                    }

                    x = order_id;
                    OrderlineDTO orderlineDTO = new OrderlineDTO(orderline_id, order_id, quantity, buttom_id, topping_id);
                    orderlineDTOList.add(orderlineDTO);

                    orderlineListMap.put(orderId, orderlineDTOList);
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Fejl under indlæsning af ordrelinjer fra databasen");
        }
        return orderlineListMap;

    }


}
