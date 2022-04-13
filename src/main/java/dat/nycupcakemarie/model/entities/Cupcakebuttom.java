package dat.nycupcakemarie.model.entities;

public class Cupcakebuttom {
    private int buttom_id;
    private String flavor;
    private int price;
    private String buttom_picture_id;


    public Cupcakebuttom(int buttom_id, String flavor, int price, String buttom_picture_id) {
        this.buttom_id = buttom_id;
        this.flavor = flavor;
        this.price = price;
        this.buttom_picture_id = buttom_picture_id;
    }

    public int getButtom_id() {
        return buttom_id;
    }

    public void setButtom_id(int buttom_id) {
        this.buttom_id = buttom_id;
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

    public String getButtom_picture_id() {
        return buttom_picture_id;
    }

    public void setButtom_picture_id(String buttom_picture_id) {
        this.buttom_picture_id = buttom_picture_id;
    }

    @Override
    public String toString() {
        return "Cupcakebuttom{" +
                "buttom_id=" + buttom_id +
                ", flavor='" + flavor + '\'' +
                ", price=" + price +
                ", buttom_picture_id='" + buttom_picture_id + '\'' +
                '}';
    }
}