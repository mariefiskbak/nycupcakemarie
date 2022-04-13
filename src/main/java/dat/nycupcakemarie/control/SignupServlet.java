package dat.nycupcakemarie.control;

import dat.nycupcakemarie.model.config.ApplicationStart;
import dat.nycupcakemarie.model.dtos.CustomerDTO;
import dat.nycupcakemarie.model.exceptions.DatabaseException;
import dat.nycupcakemarie.model.persistence.ConnectionPool;
import dat.nycupcakemarie.model.persistence.UserMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "SignupServlet", value = "/SignupServlet")
public class SignupServlet extends HttpServlet {
    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      /*  String email = request.getParameter("email");
        String password = request.getParameter("password");

        int roleId = 2;

        String rpPassword = request.getParameter("rppassword");

        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String balance = request.getParameter("balance");
        int balanceInt = Integer.parseInt(balance);

        String user_id = "0";
        int user_idInt = Integer.parseInt(user_id);
        String phone_no = request.getParameter("phoneno");
        int phone_noInt = Integer.parseInt(phone_no);

        UserMapper userMapper = new UserMapper(connectionPool);
        try {
            userMapper.createUser(email, password, roleId, firstname, lastname, balanceInt, phone_noInt);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);*/
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        int roleId = 2;

        String rpPassword = request.getParameter("rppassword");

        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
//        String balance = request.getParameter("balance");
//        int balanceInt = Integer.parseInt(balance);
        int balanceInt = 1000;

        String user_id = "0";
        int user_idInt = Integer.parseInt(user_id);
        String phone_no = request.getParameter("phoneno");
        int phone_noInt = Integer.parseInt(phone_no);


        if (!password.equals(rpPassword)) {
            request.setAttribute("fejlTilIndex", "Kodeordene var ikke ens, prøv igen");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }

        UserMapper userMapper = new UserMapper(connectionPool);

        try {
            Map<String, CustomerDTO> userMap = userMapper.getUserMapByEmail();
            if (userMap.containsKey(email)) {
                request.setAttribute("fejlTilIndex", "Emailadressen " + email + " er allerede oprettet i systemet");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
        }


        try {
            userMapper.createUser(email, password, roleId, firstname, lastname, balanceInt, phone_noInt);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("login").forward(request, response);
    }

}

