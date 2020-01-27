<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="./common/simpleHeader.jsp" %>

<section>
    <div class="container">
        <div class="row mb-3">
            <div class="col-md-4">
                <button data-toggle="modal" data-target="#addClientModal" class="btn btn-success btn-block">
                    <i class="fas fa-plus"></i> Agregar Cliente
                </button>
            </div>
            <div class="col-md-4">
                <a href="${pageContext.request.contextPath}/product?action=list" class="btn btn-success btn-block">
                    <i class="fas fa-eye"></i> Ver Productos
                </a>
            </div>
            <div class="col-md-4">
                <a href="${pageContext.request.contextPath}/client?action=list" class="btn btn-success btn-block">
                    <i class="fas fa-eye"></i> Ver Clientes
                </a>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">

            <div class="card" id="usersContent">
                <div class="card-header">
                    <h4>Listado de Clientes</h4>
                </div>
                <table class="table table-striped">
                    <thead class="table-success">
                    <tr>
                        <th>Dni</th>
                        <th>Nombre</th>
                        <th>Apellidos</th>
                        <th>Usuario</th>
                        <th>Telefono</th>
                        <th>Email</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="user" items="${users}" varStatus="status">
                        <tr>
                            <td>${user.dni}</td>
                            <td>${user.name}</td>
                            <td>${user.surname}</td>
                            <td>${user.username}</td>
                            <td>${user.phone}</td>
                            <td>${user.email}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/client?action=edit&idClient=${user.id}"
                                   class="btn btn-secondary">
                                    <i class="fas fa-angle-double-right"></i> Editar
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

<%@include file="./addClient.jsp" %>

<%@include file="./common/footer.jsp" %>