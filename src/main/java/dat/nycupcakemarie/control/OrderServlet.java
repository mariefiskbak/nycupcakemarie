package dat.nycupcakemarie.control;

import dat.nycupcakemarie.model.config.ApplicationStart;
import dat.nycupcakemarie.model.dtos.OrderDTO;
import dat.nycupcakemarie.model.dtos.OrderlineDTO;
import dat.nycupcakemarie.model.entities.User;
import dat.nycupcakemarie.model.exceptions.DatabaseException;
import dat.nycupcakemarie.model.persistence.ConnectionPool;
import dat.nycupcakemarie.model.persistence.OrderMapper;
import dat.nycupcakemarie.model.persistence.OrderlineMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "OrderServlet", value = "/OrderServlet")
public class OrderServlet extends HttpServlet {
    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();

        OrderMapper orderMapper = new OrderMapper(connectionPool);
        List<OrderDTO> orderDTOList = null;

        OrderlineMapper orderlineMapper = new OrderlineMapper(connectionPool);
        List<OrderlineDTO> orderlineDTOList = null;
        Map<Integer, List<OrderlineDTO>> orderlineListMap = null;

        List<List<OrderlineDTO>> listOfLists = new ArrayList<>();

        int removeOrderId = 0;
        int changeOrderId = 0;

        try {
            String removeIdS = request.getParameter("remove_order");
            removeOrderId = Integer.parseInt(removeIdS);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            String changeIdS = request.getParameter("change_order");
            changeOrderId = Integer.parseInt(changeIdS);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        try {
//            orderDTOList = orderMapper.getOrderDTOList();
            if(removeOrderId > 0) {
                orderMapper.deleteOrder(removeOrderId);
            }
            if(changeOrderId > 0){
                orderMapper.changeOrder(changeOrderId);
            }
            orderDTOList = orderMapper.getJoinedOrderDTOList();

//            orderlineDTOList = orderlineMapper.getOrderlineDTOList();
            orderlineDTOList = orderlineMapper.getJoinedOrderlineDTOList();
//            orderlineListMap = orderlineMapper.getOrderlineDTOListMap();
            orderlineListMap = orderlineMapper.getJoinedOrderlineDTOListMap();
        } catch (DatabaseException e) {
            Logger.getLogger("web").log(Level.SEVERE, e.getMessage());
            request.setAttribute("fejlbesked", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        session.setAttribute("orderlist", orderDTOList);
        session.setAttribute("orderlinelist", orderlineDTOList);

        for (Integer key : orderlineListMap.keySet()) {
            String orderId = "" + key;
            session.setAttribute(orderId, orderlineListMap.get(key));

            listOfLists.add(orderlineListMap.get(key));

        }
        session.setAttribute("orderlineListMap", orderlineListMap);

        session.setAttribute("listoflists", listOfLists);

        request.getRequestDispatcher("orders.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
