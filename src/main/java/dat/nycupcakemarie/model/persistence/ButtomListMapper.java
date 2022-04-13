package dat.nycupcakemarie.model.persistence;

import dat.nycupcakemarie.model.entities.Cupcakebuttom;
import dat.nycupcakemarie.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ButtomListMapper {

    private ConnectionPool connectionPool;

    public ButtomListMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public List<Cupcakebuttom> getButtomData() throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        List<Cupcakebuttom> cupcakebuttomList = new ArrayList<>();

        //String sql = "SELECT cupcakebuttom.buttom_id, cupcakebuttom.flavor, cupcakebuttom.price";
        String sql = "SELECT * FROM cupcakebuttom";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    int buttom_id = rs.getInt("buttom_id");
                    String flavor = rs.getString("flavor");
                    int price = rs.getInt("price");
                    String buttom_picture_id = rs.getString("buttom_picture_id");

                    Cupcakebuttom cupcakebuttom = new Cupcakebuttom(buttom_id, flavor, price, buttom_picture_id);
                    cupcakebuttomList.add(cupcakebuttom);
                }
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Fejl under indlæsning af cupcakebunde fra databasen");
        }
        return cupcakebuttomList;
    }
}
