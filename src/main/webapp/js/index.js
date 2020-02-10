let carrito = JSON.parse(localStorage.getItem("carrito"));
let newItem = {};

function addNewItem(codigo, nombre, imagen, precio, tipo) {
    newItem = {"codigo" : codigo, "nombre" : nombre, "imagen" : imagen, "precio" : precio, "tipo" : tipo, "cantidad" : parseFloat(document.getElementById('quantity' + codigo).value), "total" : (parseFloat(document.getElementById('quantity'+ codigo).value) * precio)};
    console.log(newItem);
    console.log(carrito);
}

function compareItems(newItem) {
    for (let item of carrito){
        if(item.codigo === newItem.codigo){
            return false;
        }
    } return true;
}

function addToLocalStorage() {
    if (carrito === null) {
        carrito = [];
    }

    if (Array.isArray(carrito) && !carrito.length){
        carrito.push(newItem);
    }else {
        for (let item of carrito) {
            if(item.codigo === newItem.codigo){
                item.cantidad += newItem.cantidad;
                item.total = (item.cantidad * item.precio);
            } else if(compareItems(newItem)){
                carrito.push(newItem);
            }
        }
    }
    localStorage.setItem("carrito", JSON.stringify(carrito));
    console.log(carrito);
}

function printLocalStorage() {
    content = document.getElementById('localStorage');
    content.innerHTML = '';
    let totalPedido = 0;
    let numPedidos = document.getElementById('userOrders').value;
    for (let item of carrito) {
        let tdPrecio = '';
        let tdCantidad = '';
        if(item.tipo === "por_peso"){
            tdPrecio = '€/kg';
            tdCantidad = 'kg';
        } else if (item.tipo === "por_unidad"){
            tdPrecio = '€/unidad';
            tdCantidad = 'unidad/es';
        }
        content.innerHTML += `
    <tr>
                        <td><img src="./img/${item.imagen}" alt="" class="imagenCarrito"></td>
                        <td class="align-middle">${item.nombre}</td>
                        <td class="align-middle">${item.precio}${tdPrecio}</td>
                        <td class="align-middle">${item.cantidad} ${tdCantidad}</td>
                        <td class="align-middle text-center">
                            <a href="#" onclick="removeItem(${item.codigo})" class="btn">
                                <i class="fas fa-trash"></i>
                            </a>
                        </td>
                        <td class="align-middle">${item.total}€</td>
                    </tr>
    `
        totalPedido += item.total;
    }

    if (totalPedido > 120 && numPedidos >= 2){
        totalPedido = (totalPedido * 0.9);
    }

    if(totalPedido !== 0){
        content.innerHTML += `
        <tr class="table-success">
                        <td colspan="4"></td>
                        <td colspan="1"><b>TOTAL PEDIDO:</b></td>
                        <td colspan="1"><b>${totalPedido}€</b></td>
                    </tr>
                    <tr class="table-light">
                        <td colspan="4"></td>
                        <td colspan="1"><button class="btn btn-danger" onclick="deleteLocalStorage()">Borrar Carrito</button></td>
                        <td colspan="1"><button class="btn btn-success" onclick="insertOrder(${totalPedido})">Realizar pedido</button></td>
                    </tr>
    `
    }
}

function removeItem(id) {
    carrito = carrito.filter(e => e.codigo != id);
    localStorage.setItem("carrito", JSON.stringify(carrito));
    printLocalStorage();
}

function deleteLocalStorage() {
    localStorage.removeItem("carrito");
    carrito = [];
    printLocalStorage();
    document.getElementById('localStorage').innerHTML = '';
}

function insertOrder(total){
    let pageContext = document.getElementById("pageContext").value;
    let user = document.getElementById("userSession").value;
        $.ajax({
            type : "POST",
            url : pageContext + "/order?action=insert&idCustomer=" + user +"&total=" + total,
            timeout : 100000,
            success : function() {
                console.log("SUCCESS");
                calledFromAjaxSuccess(true);
            },
            error : function(e) {
                console.log("ERROR: ", e);
                message("Para realizar el pedido es necesario iniciar sesión");
                calledFromAjaxSuccess(false);
            },
            done : function(e) {
                console.log("DONE");
            }
        });
}

function insertOrderItems(){
    let pageContext = document.getElementById("pageContext").value;
    let user = document.getElementById("userSession").value;
    for (let item of carrito) {
        $.ajax({
            type : "POST",
            url : pageContext + "/orderItems?action=insert&idProduct=" + item.codigo +"&unit_price=" + item.precio + "&quantity=" + item.cantidad + "&idCustomer=" + user,
            timeout : 100000,
            success : function() {
                console.log("SUCCESS: ");
            },
            error : function(e) {
                console.log("ERROR: ", e);
            },
            done : function(e) {
                console.log("DONE");
            }
        });
    }

}

function calledFromAjaxSuccess(returnValue){
    if(returnValue){
        insertOrderItems();
        location.replace("confirmPurchase.jsp");
        deleteLocalStorage();
    }
}

function checkUserAndPass(){
    let pageContext = document.getElementById("pageContext").value;
    let username = document.getElementById('user').value;
    let password = document.getElementById('pass').value;
    $.ajax({
        type : "POST",
        url : pageContext + "/client?action=login&userUsername=" + username +"&userPass=" + password,
        timeout : 100000,
        success : function() {
            console.log("SUCCESS: ");
            document.getElementById('loginForm').submit();
        },
        error : function(e) {
            console.log("ERROR: ", e);
            document.getElementById('alertMessage').className="alert alert-danger";
        },
        done : function(e) {
            console.log("DONE");
        }
    });
}

function message(msg) {
    $("#modalMsg").html(msg);
    $("#myModal").modal("show");
};