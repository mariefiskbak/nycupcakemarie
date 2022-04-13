<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Kunder
    </jsp:attribute>

    <jsp:attribute name="footer">
        Kunder
    </jsp:attribute>

    <jsp:body>
        <h1>Kunder</h1>
        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th scope="col">Bruger Id</th>
                    <th scope="col">Rolle</th>
                    <th scope="col">Fornavn</th>
                    <th scope="col">Efternavn</th>
                    <th scope="col">Email</th>
                    <th scope="col">Telefonnummer</th>
                    <th scope="col">Saldo (kr)</th>
                    <th scope="col">Indsæt</th>

                </tr>
                </thead>
                <tbody>
                <c:forEach var="customers" items="${requestScope.customerlist}">
                    <form action="DepositServlet">
                        <tr>
                            <td>${customers.user_id}</td>
                            <td>${customers.role_id}</td>
                            <td>${customers.firstname}</td>
                            <td>${customers.lastname}</td>
                            <td>${customers.email}</td>
                            <td>${customers.phone_no}</td>
                            <td>${customers.balance}</td>
                            <td>
                                <div class="container">
                                    <div class="row">

                                        <div class="col">
                                            <input type="number" name="deposit" id="deposit${customers.user_id}"
                                                   class="form-control" placeholder="kr"
                                                   style="width: 5rem"/>
                                        </div>
                                        <div class="col">
                                            <button type="submit" class="btn btn-outline-secondary" name="deposit_id" value="${customers.user_id}">Insæt</button>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
                </tbody>
            </table>
        </div>


    </jsp:body>

</t:pagetemplate>