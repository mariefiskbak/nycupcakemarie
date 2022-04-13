package dat.nycupcakemarie.model.persistence;

import dat.nycupcakemarie.model.entities.Cupcaketopping;
import dat.nycupcakemarie.model.exceptions.DatabaseException;
import dat.nycupcakemarie.model.entities.Cupcaketopping;
import dat.nycupcakemarie.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ToppingMapMapper {

    private ConnectionPool connectionPool;

    public ToppingMapMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }


    public Map<Integer, Cupcaketopping> getCupcakeToppingObjectMap() throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        Map<Integer, Cupcaketopping> cupcaketoppingMap = new HashMap<>();

        //String sql = "SELECT cupcaketopping.topping_id, cupcaketopping.flavor, cupcaketopping.price";
        String sql = "SELECT * FROM cupcaketopping";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    Integer topping_id = rs.getInt("topping_id");
                    String flavor = rs.getString("flavor");
                    int price = rs.getInt("price");
                    String topping_picture_id = rs.getString("topping_picture_id");

                    Cupcaketopping cupcaketopping = new Cupcaketopping(topping_id, flavor, price, topping_picture_id);
                    cupcaketoppingMap.put(topping_id, cupcaketopping);
                }
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Fejl under indlæsning af cupcaketoppe fra databasen");
        }
        return cupcaketoppingMap;
    }
}

