package dat.nycupcakemarie.model.persistence;

import dat.nycupcakemarie.model.entities.Cupcakebuttom;
import dat.nycupcakemarie.model.exceptions.DatabaseException;
import dat.nycupcakemarie.model.entities.Cupcakebuttom;
import dat.nycupcakemarie.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ButtomMapMapper {

    private ConnectionPool connectionPool;

    public ButtomMapMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public Map<Integer, Cupcakebuttom> getButtomObjectMap() throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        Map<Integer, Cupcakebuttom> cupcakebuttomMap = new HashMap();

        //String sql = "SELECT cupcakebuttom.buttom_id, cupcakebuttom.flavor, cupcakebuttom.price";
        String sql = "SELECT * FROM cupcakebuttom";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    Integer buttom_id = rs.getInt("buttom_id");
                    String flavor = rs.getString("flavor");
                    int price = rs.getInt("price");
                    String buttom_picture_id = rs.getString("buttom_picture_id");

                    Cupcakebuttom cupcakebuttom = new Cupcakebuttom(buttom_id, flavor, price, buttom_picture_id);
                    cupcakebuttomMap.put(buttom_id, cupcakebuttom);
                }
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Fejl under indlæsning af cupcakebunde fra databasen");
        }
        return cupcakebuttomMap;
    }
}
