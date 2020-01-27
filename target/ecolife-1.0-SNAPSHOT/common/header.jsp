<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.ecolife.dto.User"%>

<%
    User usuari = (User) session.getAttribute("user");
    request.setAttribute("usuari", usuari);
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
    <style><%@ include file="../css/style.css" %></style>
    <title>Ecolife</title>
</head>
<body class="container back">
<header class="mb-2">
    <nav class="navbar navbar-light bg-success row">
        <a class="navbar-brand" href="index.jsp">Ecolife <i class="fas fa-leaf"></i></a>
        <form class="form-inline my-2 my-lg-0 ">
            <input class="form-control mr-sm-2" type="search" placeholder="nombre producto" aria-label="Search">
            <button class="btn btn-outline-light my-2 my-sm-0" type="submit">Buscar</button>
        </form>
        <div>
            <c:if test="${usuari==null}">
                <button class="btn btn-light" data-toggle="modal" data-target="#loginModal">Login <i class="fas fa-user"></i></button>
            </c:if>
            <c:if test="${usuari!=null}">
                <button class="btn btn-light"><c:out value="${usuari.nom}"/> <i class="fas fa-user"></i></button>
            </c:if>
            <a class="btn btn-light" href="carrito.jsp">Carrito <i class="fas fa-shopping-cart"></i></a>
        </div>
    </nav>
    <nav class="nav justify-content-center bg-light row">
        <li class="nav-item">
            <a class="nav-link active text-success" href="index.html">Inicio</a>
        </li>
        <li class="nav-item">
            <a class="nav-link text-success" href="#">Fruta y Verdura Ecológica</a>
        </li>
        <li class="nav-item">
            <a class="nav-link text-success" href="#">Carnes Ecológicas</a>
        </li>
        <li class="nav-item">
            <a class="nav-link text-success" href="#">Huevos y Lácteos Ecológicos</a>
        </li>
    </nav>
</header>

