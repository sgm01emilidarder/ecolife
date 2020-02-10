<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="./common/header.jsp" %>

<section>
    <div class="row mt-2">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">
                    <h4 class="text-success">Su carrito  <i class="fas fa-shopping-cart"></i></h4>
                </div>
                <table class="table table-striped">
                    <thead class="thead-light">
                    <tr>
                        <th>Producto</th>
                        <th>Nombre</th>
                        <th>Precio</th>
                        <th>Cantidad</th>
                        <th></th>
                        <th>Total</th>
                    </tr>
                    </thead>
                    <tbody id="localStorage">

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>

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

<%@include file="./login.jsp" %>
<%@include file="./alertLogin.jsp" %>

<script src="js/jquery-3.4.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
<script src="js/index.js"></script>
<script>
    printLocalStorage();
</script>
</body>
</html>