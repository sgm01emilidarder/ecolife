<%@ page import="com.ecolife.dto.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ecolife.dto.OrderItems" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="./common/header.jsp" %>
<%
    List<OrderItems> items = (List<OrderItems>) session.getAttribute("customerOrderItems");
    request.setAttribute("items", items);
%>
<section>
    <div class="row">
        <div class="col-md-12">
            <div class="card" id="usersContent">
                <div class="card-header">
                    <h4>Pedido</h4>
                </div>
                <table class="table table-striped">
                    <thead class="table-success">
                    <tr>
                        <th>Producto</th>
                        <th>Nombre</th>
                        <th>Precio</th>
                        <th>cantidad</th>
                        <th>total</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${items}" varStatus="status">
                        <tr>
                            <td><img src="./img/${item.productCode.cover}" alt="" class="imagenCarrito"></td>
                            <td>${item.productCode.name}</td>
                            <td>${item.unitPrice}€</td>
                            <td>${item.quantity}
                                <c:if test="${item.productCode.type=='por_peso'}">kg</c:if>
                                <c:if test="${item.productCode.type=='por_unidad'}">unidad/es</c:if>
                            </td>
                            <td>${item.unitPrice * item.quantity}</td>
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
