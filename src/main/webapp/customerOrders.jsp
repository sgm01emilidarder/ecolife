<%@ page import="com.ecolife.dto.Order" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="./common/header.jsp" %>
<%
    List<Order> pedidos = (List<Order>) session.getAttribute("customerOrders");
    request.setAttribute("pedidos", pedidos);
%>
<section>
    <div class="row">
        <div class="col-md-12">
            <div class="card" id="usersContent">
                <div class="card-header">
                    <h4>Mis pedidos</h4>
                </div>
                <table class="table table-striped">
                    <thead class="table-success">
                    <tr>
                        <th>Fecha</th>
                        <th>Total Pedido</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="pedido" items="${pedidos}" varStatus="status">
                        <tr>
                            <td>${pedido.orderDate}</td>
                            <td>${pedido.orderTotal}€</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/orderItems?action=list&idOrder=${pedido.id}"
                                   class="btn btn-secondary">
                                    <i class="fas fa-angle-double-right"></i> Ver pedido
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>

<%@include file="./login.jsp" %>
<%@include file="./common/footer.jsp" %>
