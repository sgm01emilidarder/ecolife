<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="com.ecolife.dto.User" %>
<%@ page import="com.ecolife.dao.OrderDao" %>

<%
    User usuari = (User) session.getAttribute("user");
    request.setAttribute("usuari", usuari);

    if (usuari != null){
        int numPedidos = new OrderDao().countOrdersByCustomerIdAndMonth(usuari);
        request.setAttribute("numPedidos", numPedidos);
    }

%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.css">
    <link rel="stylesheet" href="css/index.css">
    <style>
        <%@ include file="../css/style.css" %>
    </style>
    <title>Ecolife</title>
</head>
<body class="container back">
<input type="hidden" id="userSession" value="${usuari.id}">
<input type="hidden" id="userOrders" value="${numPedidos}">
<input type="hidden" id="pageContext" value="${pageContext.request.contextPath}">
<header class="mb-2">
    <nav class="navbar navbar-light bg-success row">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/product?action=filter&categoryProduct=null">Ecolife <i class="fas fa-leaf"></i></a>
        <form action="${pageContext.request.contextPath}/product?action=search" method="post" class="form-inline my-2 my-lg-0 ">
            <input class="form-control mr-sm-2" type="search" name="productSearch" placeholder="nombre producto" aria-label="Search">
            <button class="btn btn-outline-light my-2 my-sm-0" type="submit">Buscar</button>
        </form>
        <div>
            <c:if test="${usuari==null}">
                <button class="btn btn-light" data-toggle="modal" data-target="#loginModal">Login <i
                        class="fas fa-user"></i></button>
            </c:if>
            <c:if test="${usuari!=null}">
                <div class="btn-group">
                    <button class="btn btn-light dropdown-toggle" id="dropdownMenuButton" data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="false"><c:out value="${usuari.username}"/> <i
                            class="fas fa-user"></i></button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <a class="dropdown-item " href="${pageContext.request.contextPath}/order?action=list&idCustomer=${usuari.id}">Mis pedidos</a>
                        <form action="${pageContext.request.contextPath}/client?action=close" method="POST">
                            <button type="submit" class="dropdown-item text-danger">Cerrar Sesion</button>
                        </form>
                    </div>
                </div>
            </c:if>
            <a class="btn btn-light" href="carrito.jsp" onclick="setUser(${usuari.id}); printLocalStorage()">Carrito <i class="fas fa-shopping-cart"></i></a>
        </div>
    </nav>
    <nav class="nav justify-content-center bg-light row">
        <li class="nav-item">
            <a class="nav-link active text-success" href="${pageContext.request.contextPath}/product?action=filter&categoryProduct=null">Inicio</a>
        </li>
        <li class="nav-item">
            <a class="nav-link text-success" href="${pageContext.request.contextPath}/product?action=filter&categoryProduct=fruta_y_verdura">Fruta y Verdura Ecológica</a>
        </li>
        <li class="nav-item">
            <a class="nav-link text-success" href="${pageContext.request.contextPath}/product?action=filter&categoryProduct=carne">Carnes Ecológicas</a>
        </li>
        <li class="nav-item">
            <a class="nav-link text-success" href="${pageContext.request.contextPath}/product?action=filter&categoryProduct=huevos_y_lacteos">Huevos y Lácteos Ecológicos</a>
        </li>
    </nav>
</header>

