package dat.nycupcakemarie.model.dtos;

import java.sql.Timestamp;

public class OrderDTO {
private int order_id;
private int user_id;
private int total_price;
private Timestamp timestamp;
private int status_id;

    public OrderDTO(int order_id, int user_id, int total_price, Timestamp timestamp, int status_id) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.total_price = total_price;
        this.timestamp = timestamp;
        this.status_id = status_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }
}
