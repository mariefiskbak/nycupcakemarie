<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
             Login
    </jsp:attribute>

    <jsp:attribute name="footer">
            Login
    </jsp:attribute>

    <jsp:body>
        <h1>Login</h1>
        <br>
        <h3>Du kan logge ind her</h3>

        <form action="login" method="post">
            <label for="email">Email: </label>
            <input type="email" id="email" name="email"/>
            <label for="password">Password: </label>
            <input type="password" id="password" name="password"/>
            <input type="submit"  value="Log in"/>
        </form>
        <br>
        <h3>Har du ikke en konto endnu?</h3>
        <br>
        <form action="signup.jsp" method="get">
            <input type="submit"  value="Opret bruger"/>
        </form>
    </jsp:body>
</t:pagetemplate>