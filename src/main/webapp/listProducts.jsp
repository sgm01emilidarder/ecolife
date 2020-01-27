<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="./common/simpleHeader.jsp" %>

<section>
    <div class="container">
        <div class="row mb-3">
            <div class="col-md-4">
                <button data-toggle="modal" data-target="#addProductModal" class="btn btn-success btn-block"
                >
                    <i class="fas fa-plus"></i> Agregar Producto
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

                <div class="card" id="productsContent">
                        <div class="card-header">
                            <h4>Listado de Productos</h4>
                        </div>
                        <table class="table table-striped">
                            <thead class="table-success">
                            <tr>
                                <th>Nombre</th>
                                <th>Precio</th>
                                <th>Peso(kg)</th>
                                <th>Imagen</th>
                                <th>Nombre_Imagen</th>
                                <th>Descripcion</th>
                                <th>Categoria</th>
                                <th>Tipo</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="product" items="${products}" varStatus="status">
                                <tr>
                                    <td>${product.name}</td>
                                    <td>${product.price}€</td>
                                    <td>${product.weight}</td>
                                    <td><img src="img/${product.cover}" alt="" style="width: 3vw; height: 3vw"></td>
                                    <td>${product.cover}</td>
                                    <td>${product.description}</td>
                                    <td>${product.category}</td>
                                    <td>${product.type}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/product?action=edit&idProduct=${product.code}"
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

<%@include file="./addProduct.jsp" %>
<%@include file="./common/footer.jsp" %>