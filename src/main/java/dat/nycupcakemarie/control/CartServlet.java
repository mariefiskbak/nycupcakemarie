package dat.nycupcakemarie.control;

import dat.nycupcakemarie.model.config.ApplicationStart;
import dat.nycupcakemarie.model.dtos.CartDTO;
import dat.nycupcakemarie.model.dtos.OrderlineDTO;
import dat.nycupcakemarie.model.entities.Cupcakebuttom;
import dat.nycupcakemarie.model.entities.Cupcaketopping;
import dat.nycupcakemarie.model.exceptions.DatabaseException;
import dat.nycupcakemarie.model.persistence.ButtomMapMapper;
import dat.nycupcakemarie.model.persistence.ConnectionPool;
import dat.nycupcakemarie.model.persistence.ToppingMapMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CartServlet", value = "/CartServlet")
public class CartServlet extends HttpServlet {
    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map<Integer, OrderlineDTO> orderlineDTOMap = (Map<Integer, OrderlineDTO>) session.getAttribute("orderlineDTOMap");

        ButtomMapMapper buttomMapMapper = new ButtomMapMapper(connectionPool);
        ToppingMapMapper toppingMapMapper = new ToppingMapMapper(connectionPool);

        int orderlineId = 0;
        int quantityUpdate = 1;
        try {
            String orderlineIdString = request.getParameter("quantitycart_id");
            orderlineId = Integer.parseInt(orderlineIdString);
            String quantityString = request.getParameter("quantitycart");
            quantityUpdate = Integer.parseInt(quantityString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        try {
            String orderlineIdString = request.getParameter("remove");
            orderlineId = Integer.parseInt(orderlineIdString);
            if(orderlineId > 0){
                quantityUpdate = 0;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

//        if(orderlineDTOMap.containsKey(orderlineId)) {
            orderlineDTOMap.get(orderlineId).setQuantity(quantityUpdate);
//        }
        if(orderlineDTOMap.get(orderlineId).getQuantity() == 0) {
            orderlineDTOMap.remove(orderlineId);
        }

        session.setAttribute("orderlineDTOMap", orderlineDTOMap);

//        request.getRequestDispatcher("CartDTOListServlet").forward(request, response);

        if(orderlineDTOMap.size() == 0) {
            request.getRequestDispatcher("emptycart.jsp").forward(request, response);
        }

        // printer cartDTOliste fra orderlineDTOMap
        List<CartDTO> cartDTOList = new ArrayList<>();
        for (Integer key : orderlineDTOMap.keySet()) {
            Integer buttomId = orderlineDTOMap.get(key).getButtom_id();
            Cupcakebuttom buttomObject = null;
            try {
                buttomObject = buttomMapMapper.getButtomObjectMap().get(buttomId);

                Integer topId = orderlineDTOMap.get(key).getTopping_id();
                Cupcaketopping toppingObject = toppingMapMapper.getCupcakeToppingObjectMap().get(topId);

                int quantity = orderlineDTOMap.get(key).getQuantity();

                cartDTOList.add(new CartDTO(buttomObject, toppingObject, quantity));
                int total = 0;
                for (CartDTO cartDTO : cartDTOList) {
                    total += cartDTO.getPrice();
                }
                session.setAttribute("cartDTOList", cartDTOList);
                session.setAttribute("total", total);

            } catch (DatabaseException e) {
                e.printStackTrace();
            }
        }

        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
