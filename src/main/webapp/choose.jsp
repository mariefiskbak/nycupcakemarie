<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@page errorPage="error.jsp" isErrorPage="false" %>--%>

<t:pagetemplate>
    <jsp:attribute name="header">
         Velkommen ombord
    </jsp:attribute>

    <jsp:attribute name="footer">
        Velkommen ombord
    </jsp:attribute>

    <jsp:body>
        <h1>Velkommen ombord</h1>
        <div class="container">
            <div class="row">
                <div class="col-7">
                    <br>
                    <h2>Øens bedste cupcakes. Vælg og bestil her:</h2>
                    <br>
        <%--        <form action="login.jsp" class="row gx-5">--%>
                    <form action="ChooseServlet">
                        <div class="row gx-5">
                            <div class="form-floating col-auto">
                                <select class="form-select" id="buttom" name="buttom" style="font-size: large" required>
                                    <c:forEach var="cupcakebuttom" items="${requestScope.buttomlist}">
                                        <option value="${cupcakebuttom.buttom_id}">${cupcakebuttom.flavor} ${cupcakebuttom.price}
                                            kr
                                        </option>
                                    </c:forEach>
                                </select>
                                <label for="buttom" style="margin-left: 14px">Vælg bund</label>
                            </div>
                            <div class="form-floating col-auto">
                                <select class="form-select" id="topping" name="topping" style="font-size: large"
                                        required>
                                    <c:forEach var="cupcaketopping" items="${requestScope.toppinglist}">
                                        <option value="${cupcaketopping.topping_id}">${cupcaketopping.flavor} ${cupcaketopping.price}
                                            kr
                                        </option>
                                    </c:forEach>
                                </select>
                                <label for="topping" style="margin-left: 14px">Vælg topping</label>
                            </div>
                            <div class="form-floating col-auto">
                                <select class="form-select" id="quantity" name="quantity" style="font-size: large"
                                        required>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="6">6</option>
                                    <option value="7">7</option>
                                    <option value="8">8</option>
                                    <option value="9">9</option>
                                    <option value="10">10</option>
                                    <option value="11">11</option>
                                    <option value="12">12</option>
                                </select>
                                <label for="quantity" style="margin-left: 14px">Antal</label>
                            </div>
                        </div>
                        <br><br>
                            <%-- <input type="submit" value="Læg i kurv" style="width: 8rem;
                     height: 3rem;
                     background-color: #0d6efd;
                     color:white;
                     border: none;
                     border-radius: 10px;
                     font-size: large;
                     float: right">--%>
                        <button type="submit" class="btn btn-primary btn-lg float-end">Læg i kurv</button>
                    </form>
                </div>
                <div class="col-1">
                </div>
                <div class="col-4">
                    <br>
                    <c:if test="${sessionScope.orderlineDTOMap.size() > 0}">
                    <h2>Indkøbskurv</h2>
                    <c:forEach var="cartitem" items="${sessionScope.cartDTOList}">
                        <p>${cartitem.quantity} stk ${cartitem.buttom.flavor} med ${cartitem.topping.flavor} - ${cartitem.price} kr</p>
                    </c:forEach>
                    <p>Total: ${sessionScope.total} kr</p>
                        <form>
                        <button type="submit" formaction="cart.jsp" class="btn btn-primary">Gå til indkøbskurv</button></form>
                    </c:if>
                </div>
            </div>
        </div>
        <br><br>

        <p>Ordrenummer er ${sessionScope.orderId}</p>
      <%--  <c:if test="${sessionScope.user != null}">
            <p>Du er logget ind som: "${sessionScope.user.roleId}".</p>
        </c:if>--%>

        <c:if test="${sessionScope.user == null}">
            <p>Du er ikke logget ind endnu. Du kan gøre det her: <a
                    href="login.jsp">Login</a></p>
        </c:if>

    </jsp:body>

</t:pagetemplate>