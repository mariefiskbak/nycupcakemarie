package dat.nycupcakemarie.model.config;

import dat.nycupcakemarie.model.persistence.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebListener
public class ApplicationStart implements ServletContextListener
{
    private static ConnectionPool connectionPool;

    public ApplicationStart()
    {

    }

    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        Logger.getLogger("web").log(Level.INFO, "Starting up application and connection pool");
        try
        {
            Class.forName("org.slf4j.impl.StaticLoggerBinder");
            connectionPool = new ConnectionPool();
        }
        catch (ClassNotFoundException e)
        {
            Logger.getLogger("web").log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public static ConnectionPool getConnectionPool()
    {
            return connectionPool;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {
        Logger.getLogger("web").log(Level.INFO, "Shutting down application and connection pool");
        unregisterJDBCdrivers();
        connectionPool.close();
    }

    private void unregisterJDBCdrivers()
    {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        // Loop through all drivers
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements())
        {
            Driver driver = drivers.nextElement();
            if (driver.getClass().getClassLoader() == cl)
            {
                // This driver was registered by the webapp's ClassLoader, so deregister it:
                try
                {
                    Logger.getLogger("web").log(Level.INFO, "Deregistering JDBC driver");
                    DriverManager.deregisterDriver(driver);
                }
                catch (SQLException ex)
                {
                    Logger.getLogger("web").log(Level.SEVERE, "Error deregistering JDBC driver");
                }
            } else
            {
                // driver was not registered by the webapp's ClassLoader and may be in use elsewhere
                Logger.getLogger("web").log(Level.WARNING, "Error deregistering JDBC driver");
            }
        }
    }
}

        //TODO: en admin skal kunne g??re andre brugere til admin

        //DONE: SKal ikke printe de slettede ordrer til sk??rmen, plus evt fortryd mulighed

        //TODO: Knap til at ??ndre en ordres status

        //DONE: checke for ens password ved oprettelse

        //DONE: checke for unik email ved oprettelse

        //DONE: US-1: Som kunde kan jeg bestille og betale cupcakes med en valgfri bund og top, s??dan at jeg senere kan k??re forbi butikken i Olsker og hente min ordre.

        //DONE: US-2 Som kunde kan jeg oprette en konto/profil for at kunne betale og gemme en en ordre.

        //DONE: US-3: Som administrator kan jeg inds??tte bel??b p?? en kundes konto direkte i MySql, s?? en kunde kan betale for sine ordrer.

        //DONE: US-4: Som kunde kan jeg se mine valgte ordrelinier i en indk??bskurv, s?? jeg kan se den samlede pris.

        //DONE: US-5: Som kunde eller administrator kan jeg logge p?? systemet med email og kodeord. N??r jeg er logget p??, skal jeg kunne se min email p?? hver side (evt. i topmenuen, som vist p?? mockup???en).

        //DONE: US-6: Som administrator kan jeg se alle ordrer i systemet, s?? jeg kan se hvad der er blevet bestilt.

        //DONE: US-7: Som administrator kan jeg se alle kunder i systemet og deres ordrer, s??dan at jeg kan f??lge op p?? ordrer og holde styr p?? mine kunder.

        //DONE: US-8: Som kunde kan jeg fjerne en ordre fra min indk??bskurv, s?? jeg kan justere min ordre.

        //DONE: US-9: Som administrator kan jeg fjerne en ordre, s?? systemet ikke kommer til at indeholde udgyldige ordrer. F.eks. hvis kunden aldrig har betalt.
