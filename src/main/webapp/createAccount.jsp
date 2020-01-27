<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="./common/simpleHeader.jsp" %>
<div class="row my-5">
    <div class="col-12 bg-light p-2 rounded">
        <h3 class="text-center text-success">CREAR CUENTA</h3>
        <form action="${pageContext.request.contextPath}/client?action=insert" method="POST" class="was-validated">
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="dni">Usuario</label>
                    <input type="text" name="username" class="form-control" id="usuario" placeholder="usuario" required>
                </div>
                <div class="form-group col-md-6">
                    <label for="dni">Dni</label>
                    <input type="text" name="dni" class="form-control" id="dni" placeholder="dni" required>
                </div>
                <div class="form-group col-md-6">
                    <label for="nombre">Nombre</label>
                    <input type="password" name="name" class="form-control" id="nombre" placeholder="nombre" required>
                </div>
                <div class="form-group col-md-6">
                    <label for="apellidos">Apellidos</label>
                    <input type="text" name="surname" class="form-control" id="apellidos" placeholder="apellidos" required>
                </div>
                <div class="form-group col-md-6">
                    <label for="telefono">Telefono</label>
                    <input type="text" name="phone" class="form-control" id="telefono" placeholder="telefono" required>
                </div>
                <div class="form-group col-md-6">
                    <label for="email">Email</label>
                    <input type="text" name="email" class="form-control" id="email" placeholder="email" required>
                </div>
                <div class="form-group col-md-6">
                    <label for="contraseña">Contraseña</label>
                    <input type="password" name="password" class="form-control" id="contraseña" placeholder="&#9679;&#9679;&#9679;&#9679;&#9679;" required>
                </div>
                <div class="form-group col-md-6">
                    <label for="contraseña2">Repetir contraseña</label>
                    <input type="password"  class="form-control" id="contraseña2" placeholder="&#9679;&#9679;&#9679;&#9679;&#9679;" required>
                </div>
            </div>
            <div class="col mb-4">
                <button type="submit" class="btn btn-success float-right">Crear cuenta</button>
            </div>
        </form>
    </div>
</div>

<%@include file="./common/footer.jsp" %>
