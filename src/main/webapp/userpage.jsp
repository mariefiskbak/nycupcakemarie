<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
            Brugerside
    </jsp:attribute>

    <jsp:attribute name="footer">
            Brugerside
    </jsp:attribute>

    <jsp:body>
        <h1>Brugerside</h1>
        <c:if test="${sessionScope.user != null}">


            <br>
            <p style="font-size: 18px;">Velkommen
                tilbage ${sessionScope.user.firstname} ${sessionScope.user.lastname}</p>
            <p style="font-size:18px;">Din nuværende saldo er: ${sessionScope.user.balance} kr.</p>
            <br>
            <br>

            <h2>Dine tidligere ordrer</h2>
            <br>
            <div class="table-responsive">
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th scope="col">Ordre Id</th>
                        <th scope="col">Dato</th>
                        <th scope="col">Antal cupcakes i alt</th>
                        <th scope="col">Pris i alt</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="order" items="${sessionScope.userorderlist}">
                        <form action="DepositServlet">
                            <tr>
                                <td>${order.order_id}</td>
                                <td>${order.timestamp}</td>
                                <td>${order.quantity} stk</td>
                                <td>${order.total_price} kr</td>
                            </tr>
                        </form>
                    </c:forEach>
                    </tbody>
                </table>
            </div>


            <c:if test="${sessionScope.user.roleId == 1}">
                <p>Rolle: administrator.</p>
            </c:if>
            <c:if test="${sessionScope.user.roleId == 2}">
                <p>Rolle: bruger.</p>
            </c:if>

        </c:if>

        <c:if test="${sessionScope.user == null}">
            <p>Du er ikke logget på endnu, log på her : <a
                    href="login.jsp">Login</a></p>
            <br>
            <br>
            <p1>Har du ikke en bruger endnu?</p1>
            <form action="signup.jsp" method="get">
                <input type="submit" value="Opret bruger"/>
            </form>

        </c:if>
    </jsp:body>
</t:pagetemplate>