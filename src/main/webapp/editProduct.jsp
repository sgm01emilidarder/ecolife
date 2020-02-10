<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="./common/simpleHeader.jsp" %>

<div class="row my-5">
    <div class="col-12 bg-light p-2 rounded">
        <h3 class="text-center text-success">EDITAR PRODUCTO</h3>
        <form name="frm-product" action="${pageContext.request.contextPath}/product" method="POST" class="was-validated">
            <input id="input-action" type="hidden" name="action" value="update" />
            <input type="hidden" name="idProduct" value="${product.code}" />
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="nombre">Nombre</label>
                    <input type="text" class="form-control" name="name" id="nombre" placeholder="nombre" value="${product.name}" required>
                </div>
                <div class="form-group col-md-6">
                    <label for="precio">Precio(€/kg)</label>
                    <input type="number" class="form-control" name="price" id="precio" placeholder="precio" value="${product.price}" step=".01" required>
                </div>
                <div class="form-group col-md-6">
                    <label for="peso">Peso(kg)</label>
                    <input type="number" class="form-control" name="weight" id="peso" placeholder="peso" value="${product.weight}" required>
                </div>
                <div class="form-group col-md-6">
                    <label for="imagen">Path imagen</label>
                    <input type="text" class="form-control" name="cover" id="imagen" placeholder="imagen"  value="${product.cover}" required>
                </div>
                <div class="form-group col-md-6">
                    <label for="descripcion">Descripción</label>
                    <input type="text" class="form-control" name="description" id="descripcion" placeholder="descripcion" value="${product.description}" required>
                </div>
                <div class="form-group col-md-6">
                    <label for="categoria">Categoria</label>
                    <select class="form-control"  name="category" id="categoria">
                        <option>fruta_y_verdura</option>
                        <option>carne</option>
                        <option>huevos_y_lacteos</option>
                    </select>
                </div>
                <div class="form-group col-md-6">
                    <label for="tipo">Tipo</label>
                    <select class="form-control"  name="type" id="tipo">
                        <option>por_peso</option>
                        <option>por_unidad</option>
                    </select>
                </div>
            </div>
            <div class="d-flex flex-row-reverse">
                <button id="btn-delete" class="btn btn-danger ml-3">Eliminar producto</button>
                <button type="submit" class="btn btn-success float-right">Modificar producto</button>
            </div>
        </form>
    </div>
</div>

<footer>
    <div class="row bg-light mt-2">
        <div class="col-12 col-md text-center">
            <small class="d-block mb-3 text-muted mt-2">&copy ecolife</small>
        </div>
    </div>
    <div class="row bg-light">
        <div class="col-6">
            <h5>Sobre nosotros</h5>
            <p>Ecolife, empresa de venta de productos ecológicos en Baleares.</p>
        </div>
        <div class="col-3">
            <h5>Información de interés</h5>
            <ul class="list-unstyled text-small">
                <li><a class="text-muted" href="#">Política de cookies</a></li>
                <li><a class="text-muted" href="#">Aviso Legal</a></li>
                <li><a class="text-muted" href="#">Información R.G.P.D.</a></li>
                <li><a class="text-muted" href="#">Información corporativa</a></li>
            </ul>
        </div>
        <div class="col-3">
            <h5>Grupo Ecolife</h5>
            <ul class="list-unstyled text-small">
                <li><a class="text-muted" href="#">Quienes somos?</a></li>
                <li><a class="text-muted" href="#">Quieres trabajar con nosotros?</a></li>
            </ul>
        </div>
    </div>
</footer>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
<script type="text/javascript">
    let btnDelete = document.getElementById("btn-delete");
    btnDelete.onclick = function(){
        document.getElementById("input-action").value ="delete";
        document.forms["frm-product"].submit();
    }
</script>
</body>
</html>