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
import jdk.nashorn.internal.ir.LiteralNode;
import org.apache.taglibs.standard.lang.jstl.Literal;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ChooseServlet", value = "/ChooseServlet")
public class ChooseServlet extends HttpServlet {
    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
//        List<CartDTO> cartDTOList = (List<CartDTO>) session.getAttribute("cartDTOList");
//        if (cartDTOList == null) {
//            cartDTOList = new ArrayList<>();
//        }
        Map<Integer, OrderlineDTO> orderlineDTOMap = (Map<Integer, OrderlineDTO>) session.getAttribute("orderlineDTOMap");
        if (orderlineDTOMap == null) {
            orderlineDTOMap = new LinkedHashMap<>();
        }

        ButtomMapMapper buttomMapMapper = new ButtomMapMapper(connectionPool);
        ToppingMapMapper toppingMapMapper = new ToppingMapMapper(connectionPool);

        String selectedButtomString = request.getParameter("buttom");
        Integer selectedButtom = Integer.parseInt(selectedButtomString);
        int selectedButtomInt = selectedButtom;
        String selectedToppingString = request.getParameter("topping");
        Integer selectedTopping = Integer.parseInt(selectedToppingString);
        int selectedToppingInt = selectedTopping;
        String selectedQuantatyString = request.getParameter("quantity");
        int selectedQuantity = Integer.parseInt(selectedQuantatyString);

            /*cartDTOList.add(new CartDTO(buttomMapMapper.getButtomObjectMap().get(selectedButtom), toppingMapMapper.getCupcakeToppingObjectMap().get(selectedTopping), selectedQuantity));
            int total = 0;
            for (CartDTO cartDTO : cartDTOList) {
                total += cartDTO.getPrice();
            }
            session.setAttribute("cartDTOList", cartDTOList);
            session.setAttribute("total", total);*/

        String orderIdString = "" + session.getAttribute("orderId");
        int orderId = Integer.parseInt(orderIdString);
        String orderlineIdS = orderIdString + selectedButtomString + selectedToppingString;
        Integer orderlineId = Integer.parseInt(orderlineIdS);
        int orderlineIdInt = orderlineId;

        //Checker om den type cupcake allerede er lagt i kurven og opdaterer i så fald antal
        if(orderlineDTOMap.containsKey(orderlineId)) {
            int totalQuantity = orderlineDTOMap.get(orderlineIdInt).getQuantity() + selectedQuantity;
            orderlineDTOMap.get(orderlineIdInt).setQuantity(totalQuantity);
        }
        else {
            orderlineDTOMap.put(orderlineId, new OrderlineDTO(orderlineIdInt, orderId, selectedQuantity, selectedButtomInt, selectedToppingInt));
        }
        session.setAttribute("orderlineDTOMap", orderlineDTOMap);

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



//        request.getRequestDispatcher("CartDTOListServlet").forward(request, response);
        request.getRequestDispatcher("").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
