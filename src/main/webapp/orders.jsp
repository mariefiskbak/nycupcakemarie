<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Ordrer
    </jsp:attribute>

    <jsp:attribute name="footer">
        Ordrer
    </jsp:attribute>

    <jsp:body>
        <h1>Ordrer</h1>

        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th scope="col">Fold ud</th>
                    <th scope="col">Order Id</th>
                    <th scope="col">Tidsstempel</th>
                    <th scope="col">Bruger Id</th>
                    <th scope="col">Fornavn</th>
                    <th scope="col">Efternavn</th>
                    <th scope="col">Samlet pris</th>
                    <th scope="col">Ordrestatus</th>
                    <th scope="col">Ændre ordrestatus</th>
                    <th scope="col">Fjern ordre</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="orderlinelist" items="${sessionScope.listoflists}" varStatus="orders">
                    <form action="OrderServlet">
                        <tr>
                            <td></td>
                            <td>${sessionScope.orderlist[orders.index].order_id}</td>
                            <td>${sessionScope.orderlist[orders.index].timestamp}</td>
                            <td>${sessionScope.orderlist[orders.index].user_id}</td>
                            <td>${sessionScope.orderlist[orders.index].firstname}</td>
                            <td>${sessionScope.orderlist[orders.index].lastname}</td>
                            <td>${sessionScope.orderlist[orders.index].total_price} kr</td>
                            <td>${sessionScope.orderlist[orders.index].orderStatus}</td>
                            <td>
                                <div class="col">
                                    <button type="submit" class="btn btn-outline-success" name="change_order"
                                            value="${sessionScope.orderlist[orders.index].order_id}">Skift ordrestatus
                                    </button>
                                </div>
                            </td>
                            <td>
                                <div class="col">
                                    <button type="submit" class="btn btn-outline-danger" name="remove_order"
                                            value="${sessionScope.orderlist[orders.index].order_id}">Slet ordre
                                    </button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="10">
                                <div class="col">
                                    <button type="button" class="btn btn-outline-success" data-bs-toggle="collapse"
                                            data-bs-target="#expand${sessionScope.orderlist[orders.index].order_id}"
                                            aria-expanded="false"
                                            aria-controls="expand${sessionScope.orderlist[orders.index].order_id}">Se
                                        ordrelinjer
                                    </button>
                                    <div class="collapse" id="expand${sessionScope.orderlist[orders.index].order_id}">
                                        <table class="table table-success table-striped table-sm">
                                            <thead>
                                            <tr>
                                                <th scope="col">Orderlinje Id</th>
                                                <th scope="col">Order Id</th>
                                                <th scope="col">Antal</th>
                                                <th scope="col">Bund</th>
                                                <th scope="col">Top</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach var="orderlines" items="${orderlinelist}">
                                                <%--                                                <c:set var = "orderline" scope = "session" value = "${orderlines}"/>--%>
                                                <%--                                                <c:if test = "${orderline.order_id == sessionScope.orderlist[orders.index].order_id}">--%>
                                                <%--                                                <c:if test = "${orderline.order_id eq sessionScope.orderlist[orders.index].order_id}">--%>

                                                <%--                                                <c:if test="${sessionScope.orderlist[orders.index].order_id == orderlines.order_id}">--%>
                                                <tr>
                                                    <td>${orderlines.orderline_id}</td>
                                                    <td>${orderlines.order_id}</td>
                                                    <td>${orderlines.quantity}</td>
                                                    <td>${orderlines.buttom}</td>
                                                    <td>${orderlines.topping}</td>
                                                </tr>
                                                <%--                                                </c:if>--%>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
                </tbody>
            </table>
        </div>


        <br><br><br><br>
        TEST MED LANGE KNAPPER

        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th scope="col">Order Id</th>
                    <th scope="col">Tidsstempel</th>
                    <th scope="col">Bruger Id</th>
                    <th scope="col">Fornavn</th>
                    <th scope="col">Efternavn</th>
                    <th scope="col">Samlet pris</th>
                    <th scope="col">Ordrestatus</th>
                    <th scope="col">Ændre ordrestatus</th>
                    <th scope="col">Fjern ordre</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="orderlinelist" items="${sessionScope.listoflists}" varStatus="orders">
                <form action="OrderServlet">
                    <tr>
                        <button type="button" class="btn btn-outline-secondary btn-sm" data-bs-toggle="collapse"
                                data-bs-target="#expand${sessionScope.orderlist[orders.index].order_id}"
                                aria-expanded="false"
                                aria-controls="expand${sessionScope.orderlist[orders.index].order_id}"
                                style="width: 100%;">

                            <table style="width: 100%; border: none">
                                <td>${sessionScope.orderlist[orders.index].order_id}</td>
                                <td>${sessionScope.orderlist[orders.index].timestamp}</td>
                                <td>${sessionScope.orderlist[orders.index].user_id}</td>
                                <td>${sessionScope.orderlist[orders.index].firstname}</td>
                                <td>${sessionScope.orderlist[orders.index].lastname}</td>
                                <td>${sessionScope.orderlist[orders.index].total_price} kr</td>
                                <td>${sessionScope.orderlist[orders.index].orderStatus}</td>
                                <td>
                                    <div class="col">
                                        <button type="submit" class="btn btn-outline-success btn-sm" name="change_order"
                                                value="${sessionScope.orderlist[orders.index].order_id}">Skift
                                            ordrestatus
                                        </button>
                                    </div>
                                </td>
                                <td>
                                    <div class="col">
                                        <button type="submit" class="btn btn-outline-danger btn-sm" name="remove_order"
                                                value="${sessionScope.orderlist[orders.index].order_id}">Slet ordre
                                        </button>
                                    </div>
                                </td>
                            </table>
                        </button>
                        <div class="collapse" id="expand${sessionScope.orderlist[orders.index].order_id}">
                            <table class="table table-success table-striped table-sm">
                                <thead>
                                <tr>
                                    <th scope="col">Orderlinje Id</th>
                                    <th scope="col">Order Id</th>
                                    <th scope="col">Antal</th>
                                    <th scope="col">Bund</th>
                                    <th scope="col">Top</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="orderlines" items="${orderlinelist}">
                                    <tr>
                                        <td>${orderlines.orderline_id}</td>
                                        <td>${orderlines.order_id}</td>
                                        <td>${orderlines.quantity}</td>
                                        <td>${orderlines.buttom}</td>
                                        <td>${orderlines.topping}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </tr>
                </form>
                </c:forEach>
            </table>
        </div>


</jsp:body>

</t:pagetemplate>