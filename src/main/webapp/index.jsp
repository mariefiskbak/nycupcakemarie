<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Velkommen ombord
    </jsp:attribute>

    <jsp:attribute name="footer">
        Velkommen ombord
    </jsp:attribute>

    <jsp:body>
        <h1>Velkommen ombord</h1>
        <br>
        <h2>Øens bedste cupcakes. Vælg og bestil her:</h2>
        <br>

        <a href=ButtomToppingListServlet>Ny side</a>

       <%-- <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownButtom" data-bs-toggle="dropdown" aria-expanded="false">
                Vælg bund
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownButtom">
            <c:forEach var="cupcakebuttom" items="${requestScope.buttomlist}">
                <li><a class="dropdown-item" href="#">${cupcakebuttom.flavor}</a></li>
            </c:forEach>
            </ul>
        </div>
--%>
        <br>
        <c:if test="${sessionScope.user != null}">
            <p>Du er logget ind som: "${sessionScope.user.roleId}".</p>
        </c:if>

        <c:if test="${sessionScope.user == null}">
            <p>Du er ikke logget ind endnu. Du kan gøre det her: <a
                    href="login.jsp">Login</a></p>
        </c:if>

    </jsp:body>

</t:pagetemplate>