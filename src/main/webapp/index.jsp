<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.ecolife.dto.Product"%>
<%@ page import="com.ecolife.dao.ProductDao"%>
<%@ page import="java.util.List" %>

<%@include file="./common/header.jsp" %>

<section>
    <div class="jumbotron jumbotron-fluid row mb-0">
        <div class="col-12">
            <h1 class="display-4">Ecolife, tu tienda ecologica </h1>
            <p class="lead">Encuentra todo tipo de productos ecologicos que te sorprenderán.</p>
        </div>
    </div>
    <div class="row d-flex justify-content-center bg-light my-2">
        <h2 class="text-success"><i class="fas fa-apple-alt"></i> Productos populares <i class="fas fa-apple-alt"></i>
        </h2>
    </div>
    <div class="row d-flex justify-content-center">
    <c:forEach var="product" items="${products}" varStatus="status" >
        <div class="card col-3 mx-3 my-1" style="width: 18rem;">
            <img src="img/${product.cover}" class="card-img-top mt-2 imagen" alt="...">
            <div class="card-body">
                <h5 class="card-title text-center">${product.name}
                    <c:if test="${product.type=='por_peso'}">
                        ${product.price}€/kg
                    </c:if>
                    <c:if test="${product.type=='por_unidad'}">
                        ${product.price}€/unidad
                    </c:if>
                </h5>
                <p class="card-text text-center">${product.description}</p>
                <div class="row mb-2">
                    <c:if test="${product.type=='por_peso'}">
                        <div class="col-2"><label for=""></label></div>
                        <div class="col-6"><input name="quantity" type="number" class="form-control" value="0.5" min="0.5" step=".5"></div>
                        <div class="col-3"><p><b>kg</b></p></div>
                    </c:if>
                    <c:if test="${product.type=='por_unidad'}">
                        <div class="col-6"><input name="quantity" type="number" class="form-control" value="1" min="1" step="1"></div>
                        <div class="col-6"><p><b>unidad/es</b></p></div>
                    </c:if>
                </div>
                <button class="btn btn-success btn-block aling-bottom">Añadir al carrito <i class="fas fa-shopping-cart"></i></button>
            </div>
        </div>
        </c:forEach>
    </div>
</section>

<%@include file="./login.jsp" %>
<%@include file="./common/footer.jsp" %>
