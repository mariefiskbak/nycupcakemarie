package dat.nycupcakemarie.model.entities;

public class Cupcaketopping {
    private int topping_id;
    private String flavor;
    private int price;
    private String topping_picture_id;


    public Cupcaketopping(int topping_id, String flavor, int price, String topping_picture_id) {
        this.topping_id = topping_id;
        this.flavor = flavor;
        this.price = price;
        this.topping_picture_id = topping_picture_id;
    }

    public int getTopping_id() {
        return topping_id;
    }

    public void setTopping_id(int topping_id) {
        this.topping_id = topping_id;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTopping_picture_id() {
        return topping_picture_id;
    }

    public void setTopping_picture_id(String topping_picture_id) {
        this.topping_picture_id = topping_picture_id;
    }

    @Override
    public String toString() {
        return "Cupcaketopping{" +
                "topping_id=" + topping_id +
                ", flavor='" + flavor + '\'' +
                ", price=" + price +
                ", topping_picture_id='" + topping_picture_id + '\'' +
                '}';
    }
}