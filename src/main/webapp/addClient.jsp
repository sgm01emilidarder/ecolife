<%@page contentType="text/html" pageEncoding="UTF-8" %>
<div class="modal fade" id="addClientModal" tabindex="-1" aria-labelledby="addClientModal" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <h3 class="text-center text-success">AGREGAR USUARIO</h3>
                <form action="${pageContext.request.contextPath}/client?action=insert" method="POST"
                      class="was-validated">
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="usuario">Usuario</label>
                            <input type="text" class="form-control" name="username" id="usuario"
                                   placeholder="usuario"
                                   required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="dni">Dni</label>
                            <input type="text" class="form-control" name="dni" id="dni" placeholder="dni"
                                   required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="nombre">Nombre</label>
                            <input type="text" class="form-control" name="name" id="nombre"
                                   placeholder="nombre"
                                   required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="apellidos">Apellidos</label>
                            <input type="text" class="form-control" name="surname" id="apellidos"
                                   placeholder="apellidos"
                                   required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="telefono">Telefono</label>
                            <input type="text" class="form-control" name="phone" id="telefono"
                                   placeholder="telefono"
                                   required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="email">Email</label>
                            <input type="text" class="form-control" name="email" id="email" placeholder="email"
                                   required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="contraseña">Contraseña</label>
                            <input type="password" class="form-control" name="password" id="contraseña"
                                   placeholder="&#9679;&#9679;&#9679;&#9679;&#9679;" required>
                        </div>
                    </div>
                    <div class="col mb-4">
                        <button type="submit" class="btn btn-success float-right">Agregar cliente</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>