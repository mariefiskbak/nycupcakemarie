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
                    <th scope="col">Bruger Id</th>
                    <th scope="col">Samlet pris</th>
                    <th scope="col">Tidsstempel</th>
                    <th scope="col">Status Id</th>
                    <th scope="col">Fjern ordre</th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>


        <br><br><br><br>

        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th scope="col">Fold ud</th>
                    <th scope="col">Order Id</th>
                    <th scope="col">Bruger Id</th>
                    <th scope="col">Samlet pris</th>
                    <th scope="col">Tidsstempel</th>
                    <th scope="col">Status Id</th>
                    <th scope="col">Fjern ordre</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="orders" items="${sessionScope.orderlist}">
                    <form action="">
                        <tr>
                            <td>
                                <div class="col">
                                    <button type="button" class="btn btn-outline-success" data-bs-toggle="collapse"
                                            data-bs-target="#expand${orders.order_id}" aria-expanded="false"
                                            aria-controls="expand${orders.order_id}">Se
                                    </button>
                                    <div class="collapse" id="expand${orders.order_id}">
                                        <table class="table table-striped">
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
                                                <%--                                            <c:forEach var="orderlines" items="${sessionScope.orderlinelist}">--%>
                                            <tr>
                                                    <%--                                                    <td>${orderlines.orderline_id}</td>--%>
                                                    <%--                                                    <td>${orderlines.order_id}</td>--%>
                                                    <%--                                                    <td>${orderlines.quantity}</td>--%>
                                                    <%--                                                    <td>${orderlines.buttomFlavor}</td>--%>
                                                    <%--                                                    <td>${orderlines.toppingFlavor}</td>--%>
                                                <td>1</td>
                                                <td>1</td>
                                                <td>1</td>
                                                <td>1</td>
                                                <td>1</td>
                                            </tr>
                                                <%--                                            </c:forEach>--%>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </td>
                            <td>${orders.order_id}</td>
                            <td>${orders.user_id}</td>
                            <td>${orders.total_price} kr</td>
                            <td>${orders.timestamp}</td>
                            <td>${orders.status_id}</td>
                            <td>

                                <div class="col">
                                    <button type="submit" class="btn btn-outline-danger" name="remove_order"
                                            value="${orders.order_id}">Slet
                                    </button>

                                </div>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
                </tbody>
            </table>
        </div>


        <br><br><br><br>
        Den med knappen på næste række


        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th scope="col">Fold ud</th>
                    <th scope="col">Order Id</th>
                    <th scope="col">Bruger Id</th>
                    <th scope="col">Samlet pris</th>
                    <th scope="col">Tidsstempel</th>
                    <th scope="col">Status Id</th>
                    <th scope="col">Fjern ordre</th>
                </tr>
                </thead>
                <tbody>
                    <%--                    <c:forEach var="orderlinelist" items="${sessionScope.listoflists}">--%>
                    <%--                <c:forEach var="orders" items="${sessionScope.orderlist}">--%>

                <c:forEach var="orderlinelist" items="${sessionScope.listoflists}" varStatus="orders">
<%--                    <tr>--%>
<%--                        <td>${orderlinelist}</td>--%>
<%--                        <td>${sessionScope.orderlist[orders.index]}</td>--%>
<%--                        <td>${sessionScope.orderlist[orders.index].order_id}</td>--%>
<%--                    </tr>--%>
                    <%--                    </c:forEach>--%>
                    <form action="">
                        <tr>
                            <td></td>
                            <td>${sessionScope.orderlist[orders.index].order_id}</td>
                            <td>${sessionScope.orderlist[orders.index].user_id}</td>
                            <td>${sessionScope.orderlist[orders.index].total_price} kr</td>
                            <td>${sessionScope.orderlist[orders.index].timestamp} kr</td>
                            <td>${sessionScope.orderlist[orders.index].status_id}</td>
                            <td>
                                <div class="col">
                                    <button type="submit" class="btn btn-outline-danger" name="remove_order"
                                            value="${sessionScope.orderlist[orders.index].order_id}">Slet ordre
                                    </button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="7">
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
                                                <%--                                            Vikrer ikke med nøglen, hvordan fiske de rigtige lister ud?--%>
                                            <c:forEach var="orderlines" items="${orderlinelist}">
                                                <tr>
                                                    <td>${orderlines.orderline_id}</td>
                                                    <td>${orderlines.order_id}</td>
                                                    <td>${orderlines.quantity}</td>
                                                    <td>${orderlines.buttom_id}</td>
                                                    <td>${orderlines.topping_id}</td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
                    <%--                </c:forEach>--%>
                </tbody>
            </table>
        </div>


        <br><br><br><br>


        <%--<div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>
                            &lt;%&ndash;                        <div class="col">&ndash;%&gt;
                        <button type="button" style="width: 100%;">
                            <table style="width: 100%; border: none">
                                <th scope="col">Order Id</th>
                                <th scope="col">Bruger Id</th>
                                <th scope="col">Samlet pris</th>
                                <th scope="col">Tidsstempel</th>
                                <th scope="col">Status Id</th>
                                <th scope="col">Fjern ordre</th>
                            </table>
                        </button>
                            &lt;%&ndash;                        </div>&ndash;%&gt;
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="orders" items="${sessionScope.orderlist}">
                    <form action="">
                        <tr>
                            <div class="col">
                                <button type="button" class="btn btn-outline-success" data-bs-toggle="collapse"
                                        data-bs-target="#expand${orders.order_id}" aria-expanded="false"
                                        aria-controls="expand${orders.order_id}"
                                        style="width: 100%">

                                    <table style="width: 100%; border: none">
                                        <td></td>
                                        <td>${orders.order_id}</td>
                                        <td>${orders.user_id}</td>
                                        <td>${orders.total_price} kr</td>
                                        <td>${orders.timestamp}</td>
                                        <td>${orders.status_id}</td>
                                        <td>
                                            <div class="col">
                                                <button type="submit" class="btn btn-outline-danger" name="remove_order"
                                                        value="${orders.order_id}">Slet
                                                </button>

                                            </div>
                                        </td>
                                    </table>
                                </button>
                                <div class="collapse" id="expand${orders.order_id}">
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
                                            &lt;%&ndash;                                            <c:forEach var="orderlines" items="${sessionScope.orderlinelist}">&ndash;%&gt;
                                        <tr>
                                                &lt;%&ndash;                                                    <td>${orderlines.orderline_id}</td>&ndash;%&gt;
                                                &lt;%&ndash;                                                    <td>${orderlines.order_id}</td>&ndash;%&gt;
                                                &lt;%&ndash;                                                    <td>${orderlines.quantity}</td>&ndash;%&gt;
                                                &lt;%&ndash;                                                    <td>${orderlines.buttomFlavor}</td>&ndash;%&gt;
                                                &lt;%&ndash;                                                    <td>${orderlines.toppingFlavor}</td>&ndash;%&gt;
                                            <td>
                                                1
                                            </td>
                                            <td>1</td>
                                            <td>1</td>
                                            <td>1</td>
                                            <td>1</td>
                                        </tr>
                                        <tr>
                                            <td>1</td>
                                            <td>1</td>
                                            <td>1</td>
                                            <td>1</td>
                                            <td>1</td>
                                        </tr>
                                            &lt;%&ndash;                                            </c:forEach>&ndash;%&gt;
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                                &lt;%&ndash;                            </td>&ndash;%&gt;
                        </tr>
                    </form>
                </c:forEach>
                </tbody>
            </table>
        </div>--%>

    </jsp:body>

</t:pagetemplate>