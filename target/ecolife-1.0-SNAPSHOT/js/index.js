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
    content.innerHTML += `
        <tr class="table-success">
                        <td colspan="4"></td>
                        <td colspan="1"><b>TOTAL PEDIDO:</b></td>
                        <td colspan="1"><b>${totalPedido}€</b></td>
                    </tr>
                    <tr class="table-light">
                        <td colspan="4"></td>
                        <td colspan="1"><button class="btn btn-danger" onclick="deleteLocalStorage()">Borrar Carrito</button></td>
                        <td colspan="1"><button class="btn btn-success">Realizar pedido</button></td>
                    </tr>
    `
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
}