package dat.nycupcakemarie.control;

import dat.nycupcakemarie.model.config.ApplicationStart;
import dat.nycupcakemarie.model.dtos.CustomerDTO;
import dat.nycupcakemarie.model.entities.User;
import dat.nycupcakemarie.model.exceptions.DatabaseException;
import dat.nycupcakemarie.model.persistence.ConnectionPool;
import dat.nycupcakemarie.model.persistence.UserMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        response.setContentType("text/html");
        HttpSession session = request.getSession();

        //henter info fra formularen
        String email = null;
        String password = null;
        String rpPassword = null;
        String firstname = null;
        String lastname = null;
        int phone_noInt = 0;
        try {
            email = request.getParameter("email");
            password = request.getParameter("password");
            rpPassword = request.getParameter("rppassword");
            firstname = request.getParameter("firstname");
            lastname = request.getParameter("lastname");
            String phone_no = request.getParameter("phoneno");
            phone_noInt = Integer.parseInt(phone_no);
        } catch (NumberFormatException e) {
            request.setAttribute("fejlTilIndex", "Hov prøv igen");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }

        int balanceInt = 1000;
        int roleId = 2;

        String user_id = "0";
        int user_idInt = Integer.parseInt(user_id);

        //TJekker om de to password er ens
        if (!password.equals(rpPassword)) {
            request.setAttribute("fejlTilIndex", "Kodeordene var ikke ens, prøv igen");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }


        //Tjekker om emailadressen allerede er oprettet
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

        //Opretter brugeren i databasen
        try {
            userMapper.createUser(email, password, roleId, firstname, lastname, balanceInt, phone_noInt);
        } catch (DatabaseException e) {
            request.setAttribute("fejlTilIndex", "Hov prøv igen");
            request.getRequestDispatcher("signup.jsp").forward(request, response);

        }

        //Logger brugeren på
        session.setAttribute("user", null); // adding empty user object to session scope
        User user = null;
        try {
            user = userMapper.login(email, password);
            session = request.getSession();
            session.setAttribute("user", user); // adding user object to session scope
//            request.getRequestDispatcher("UserpageServlet").forward(request, response);
            request.getRequestDispatcher("userpage.jsp").forward(request, response);
        }
        catch (DatabaseException e) {
            Logger.getLogger("web").log(Level.SEVERE, e.getMessage());
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

}

