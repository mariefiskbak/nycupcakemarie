package dat.nycupcakemarie.model.dtos;

import dat.nycupcakemarie.model.entities.Cupcakebuttom;
import dat.nycupcakemarie.model.exceptions.DatabaseException;
import dat.nycupcakemarie.model.persistence.ButtomMapMapper;
import dat.nycupcakemarie.model.persistence.ConnectionPool;
import dat.nycupcakemarie.model.persistence.ToppingMapMapper;

public class OrderlineDTO {
    private int orderline_id;
    private int order_id;
    private int quantity;
    private int buttom_id;
    private int topping_id;
    private ConnectionPool connectionPool;
    private String buttomFlavor;
    private String toppingFlavor;

    public OrderlineDTO(int orderline_id, int order_id, int quantity, int buttom_id, int topping_id) {
        this.orderline_id = orderline_id;
        this.order_id = order_id;
        this.quantity = quantity;
        this.buttom_id = buttom_id;
        this.topping_id = topping_id;
        ButtomMapMapper buttomMapMapper = new ButtomMapMapper(connectionPool);
        ToppingMapMapper toppingMapMapper = new ToppingMapMapper(connectionPool);
        try {
            buttomFlavor = buttomMapMapper.getButtomObjectMap().get(this.buttom_id).getFlavor();
            toppingFlavor = toppingMapMapper.getCupcakeToppingObjectMap().get(this.topping_id).getFlavor();
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    public int getOrderline_id() {
        return orderline_id;
    }

    public String getButtomFlavor() {
        return buttomFlavor;
    }

    public String getToppingFlavor() {
        return toppingFlavor;
    }
    //    public void setButtomFlavor() throws DatabaseException {
//        ButtomMapMapper buttomMapMapper = new ButtomMapMapper(connectionPool);
//        String flavor = buttomMapMapper.getButtomObjectMap().get(buttom_id).getFlavor();
//    }
//
//    public String getButtomFlavor() {
//        return flavor;
//    }

    public void setOrderline_id(int orderline_id) {
        this.orderline_id = orderline_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getButtom_id() {
        return buttom_id;
    }

    public void setButtom_id(int buttom_id) {
        this.buttom_id = buttom_id;
    }

    public int getTopping_id() {
        return topping_id;
    }

    public void setTopping_id(int topping_id) {
        this.topping_id = topping_id;
    }
}
