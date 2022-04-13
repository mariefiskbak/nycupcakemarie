<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Indkøbskurv
    </jsp:attribute>

    <jsp:attribute name="footer">
        Indkøbskurv
    </jsp:attribute>

    <jsp:body>
        <h1>Indkøbskurv</h1>
        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th scope="col">Antal</th>
                    <th scope="col">Bund</th>
                    <th scope="col">Topping</th>
                    <th scope="col">Pris</th>
                    <th scope="col">Fjern</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="cartitem" items="${sessionScope.cartDTOList}">
                    <form action="CartServlet">
                        <tr>
                            <td>
                                <div class="container">
                                    <div class="row">

                                        <div class="col">
                                            <input type="number" name="quantitycart"
                                                   id="quantitycart${cartitem.buttom.buttom_id}${cartitem.topping.topping_id}"
                                                   class="form-control" value="${cartitem.quantity}"
                                                   style="width: 4rem"
                                                   min="0" />
                                        </div>
                                        <div class="col">
                                            <button type="submit" class="btn btn-outline-secondary"
                                                    name="quantitycart_id"
                                                    value="${sessionScope.orderId}${cartitem.buttom.buttom_id}${cartitem.topping.topping_id}">
                                                Opdater
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </td>
                            <td>${cartitem.buttom.flavor}</td>
                            <td>${cartitem.topping.flavor}</td>
                            <td>${cartitem.price} kr</td>
                            <td>
                                <div class="container">
                                    <div class="row">
                                        <div class="col">
                                            <button type="submit" class="btn btn-danger btn-sm"
                                                    name="remove"
                                                    value="${sessionScope.orderId}${cartitem.buttom.buttom_id}${cartitem.topping.topping_id}">x
                                            </button>
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

        <p>Total: ${sessionScope.total} kr</p>
        <c:if test="${sessionScope.user != null}">
        <p>Din saldo: ${sessionScope.user.balance} kr</p>
        <form>
            <button type="submit" formaction="PayServlet" class="btn btn-primary btn-lg">Betal</button>
        </form>
            <p>${requestScope.notenoughmoney}</p>
        </c:if>
        <c:if test="${sessionScope.user == null}">
            <p>Du er ikke logget ind endnu. Log ind for at betale: <a
                    href="login.jsp">Login</a></p>
        </c:if>

    </jsp:body>

</t:pagetemplate>