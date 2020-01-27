<%@page contentType="text/html" pageEncoding="UTF-8" %>
<div class="modal fade" id="addProductModal" tabindex="-1" role="dialog" aria-labelledby="addProductModal"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <h3 class="text-center text-success">AGREGAR PRODUCTO</h3>
                <form action="${pageContext.request.contextPath}/product?action=insert" method="POST"
                      class="was-validated">
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="nombre">Nombre</label>
                            <input type="text" class="form-control" name="name" id="nombre" placeholder="nombre"
                                   required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="precio">Precio(€/kg)</label>
                            <input type="number" class="form-control" name="price" id="precio" placeholder="precio"
                                   min=".01" step=".01" required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="peso">Peso(kg)</label>
                            <input type="number" class="form-control" min="1" name="weight" id="peso" placeholder="peso"
                                   required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="imagen">Path imagen</label>
                            <input type="text" class="form-control" name="cover" id="imagen" placeholder="imagen"
                                   required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="descripcion">Descripción</label>
                            <input type="text" class="form-control" name="description" id="descripcion"
                                   placeholder="descripcion" required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="categoria">Categoria</label>
                            <select class="form-control" name="category" id="categoria">
                                <option>fruta_y_verdura</option>
                                <option>carne</option>
                                <option>huevos_y_lacteos</option>
                            </select>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="tipo">Tipo</label>
                            <select class="form-control" name="type" id="tipo">
                                <option>por_peso</option>
                                <option>por_unidad</option>
                            </select>
                        </div>
                    </div>
                    <div class="col mb-4">
                        <button type="submit" class="btn btn-success float-right">Guardar producto</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

