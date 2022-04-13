package dat.nycupcakemarie.control;

import dat.nycupcakemarie.model.config.ApplicationStart;
import dat.nycupcakemarie.model.dtos.OrderlineDTO;
import dat.nycupcakemarie.model.entities.User;
import dat.nycupcakemarie.model.exceptions.DatabaseException;
import dat.nycupcakemarie.model.persistence.ConnectionPool;
import dat.nycupcakemarie.model.persistence.OrderMapper;
import dat.nycupcakemarie.model.persistence.OrderlineMapper;
import dat.nycupcakemarie.model.persistence.UserMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "PayServlet", value = "/PayServlet")
public class PayServlet extends HttpServlet {
    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserMapper userMapper = new UserMapper(connectionPool);

        User user = (User) session.getAttribute("user");
        int balance = user.getBalance();
        String totalS = "" + session.getAttribute("total");
        int total = Integer.parseInt(totalS);
        int userId = user.getUser_id();

        String orderIdS = "" + session.getAttribute("orderId");
        int orderId = Integer.parseInt(orderIdS);

        if (balance >= total) {
            int nybalance = balance - total;
            try {
                userMapper.depositToUser(userId, nybalance);
                user = userMapper.getUserObject(userId);
                session = request.getSession();
                session.setAttribute("user", user);



                //TODO: ordren skal i databasen (skal totalprisen væk fra ordretabellen)
                //TODO: problemet er at ordreId skal i ordretabellen, før ordrelinjerne kan komme i
                OrderMapper orderMapper = new OrderMapper(connectionPool);
                orderMapper.insertOrderToDB(orderId, userId, total, 121212, 1);


                //TODO:  ordrelinjerne skal i databasen og (hentes fra mappet?)
                Map<Integer, OrderlineDTO> orderlineDTOMap = (Map<Integer, OrderlineDTO>) session.getAttribute("orderlineDTOMap");

                OrderlineMapper orderlineMapper = new OrderlineMapper(connectionPool);
                for (Integer key : orderlineDTOMap.keySet()) {
                    orderlineMapper.insertOrderlineToDB(orderlineDTOMap.get(key));
                }

                //TODO: orderId'et skal fornys og (sker allerede, hvorfor? pga AI?)

                //TODO: indkøbskurven skal renses


            } catch (DatabaseException e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("notenoughmoney", "Der var ikke penge nok på saldoen");
            request.getRequestDispatcher("cart.jsp").forward(request, response);

        }

        request.getRequestDispatcher("userpage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
