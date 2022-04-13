package dat.nycupcakemarie.model.persistence;

import dat.nycupcakemarie.model.entities.Cupcaketopping;
import dat.nycupcakemarie.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ToppingListMapper{

    private ConnectionPool connectionPool;

    public ToppingListMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public List<Cupcaketopping> getToppingData() throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        List<Cupcaketopping> toppingList = new ArrayList<>();

        //String sql = "SELECT cupcaketopping.topping_id, cupcaketopping.flavor, cupcaketopping.price";
        String sql = "SELECT * FROM cupcaketopping";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    int topping_id = rs.getInt("topping_id");
                    String flavor = rs.getString("flavor");
                    int price = rs.getInt("price");
                    String topping_picture_id = rs.getString("topping_picture_id");

                    Cupcaketopping cupcaketopping = new Cupcaketopping(topping_id, flavor, price, topping_picture_id);
                    toppingList.add(cupcaketopping);
                }
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Fejl under indlæsning af cupcaketoppe fra databasen");
        }
        return toppingList;
    }
}

