package dat.nycupcakemarie.control;

import dat.nycupcakemarie.model.config.ApplicationStart;
import dat.nycupcakemarie.model.entities.Cupcakebuttom;
import dat.nycupcakemarie.model.entities.Cupcaketopping;
import dat.nycupcakemarie.model.exceptions.DatabaseException;
import dat.nycupcakemarie.model.persistence.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ButtomToppingListServlet", value = "/ButtomToppingListServlet")
public class ButtomToppingListServlet extends HttpServlet {

    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        OrderMapper orderMapper = new OrderMapper(connectionPool);
        try {
            int orderId = orderMapper.getLatestOrderId() + 1;
            session.setAttribute("orderId", orderId);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        response.setContentType("text/html");
        ButtomListMapper buttomListMapper = new ButtomListMapper(connectionPool);
        List<Cupcakebuttom> buttomList = null;
        try {
            buttomList = buttomListMapper.getButtomData();
        } catch (DatabaseException e) {
            Logger.getLogger("web").log(Level.SEVERE, e.getMessage());
            request.setAttribute("fejlbesked", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        request.setAttribute("buttomlist", buttomList);

        ToppingListMapper toppingListMapper = new ToppingListMapper(connectionPool);
        List<Cupcaketopping> toppingList = null;
        try {
            toppingList = toppingListMapper.getToppingData();
        } catch (DatabaseException e) {
            Logger.getLogger("web").log(Level.SEVERE, e.getMessage());
            request.setAttribute("fejlbesked", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        request.setAttribute("toppinglist", toppingList);


        request.getRequestDispatcher("choose.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
