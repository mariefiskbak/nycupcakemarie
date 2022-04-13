<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Tom kurv
    </jsp:attribute>

    <jsp:attribute name="footer">
        Tom kurv
    </jsp:attribute>

    <jsp:body>
        <h1 style="color: red">Din kurv er tom</h1>
        <br><br>

        <div class="container">
            <p><a href="ButtomToppingListServlet">Tilbage til forsiden</a></p>
        </div>

    </jsp:body>

</t:pagetemplate>