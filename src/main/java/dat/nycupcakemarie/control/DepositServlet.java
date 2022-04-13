package dat.nycupcakemarie.control;

import dat.nycupcakemarie.model.config.ApplicationStart;
import dat.nycupcakemarie.model.dtos.CustomerDTO;
import dat.nycupcakemarie.model.exceptions.DatabaseException;
import dat.nycupcakemarie.model.persistence.ConnectionPool;
import dat.nycupcakemarie.model.persistence.UserMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "DepositServlet", value = "/DepositServlet")
public class DepositServlet extends HttpServlet {
    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Logger.getLogger("web").log(Level.INFO, "");
        UserMapper userMapper = new UserMapper(connectionPool);

        String depositIdString = request.getParameter("deposit_id");
        int depositId = Integer.parseInt(depositIdString);
        Integer depositIdInteger = depositId;

        String depositString = request.getParameter("deposit");
        int deposit = Integer.parseInt(depositString);
        int balance = 1000;

        try {
            balance = deposit + userMapper.getUserMap().get(depositIdInteger).getBalance();
        } catch (DatabaseException e) {
            e.printStackTrace();
        }


        try {
            userMapper.depositToUser(depositId, balance);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }


        //Ville jeg behøve at kalde servletten igen, hvis jeg havde sat det på sessionScope?
        request.getRequestDispatcher("CustomerListServlet").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
