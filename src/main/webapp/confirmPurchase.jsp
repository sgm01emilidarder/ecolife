<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="./common/header.jsp" %>

<div class="row bg-light">
    <h2 class="text-success text-center my-5 col-12">Pedido realizado correctamente</h2>
    <div class="d-flex justify-content-center mb-5 col-12">
        <a href="index.jsp" class="btn btn-success mx-2">Volver a inicio</a>
        <a href="customerOrders.jsp" class="btn btn-success mx-2">Ver mis pedidos</a>
    </div>
</div>

<%@include file="./login.jsp" %>
<%@include file="./common/footer.jsp" %>
