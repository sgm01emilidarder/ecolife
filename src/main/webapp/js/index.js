let carrito = JSON.parse(localStorage.getItem("carrito"));
let newItem = {};

function addNewItem(codigo, nombre, imagen, precio, cantidad) {
    newItem = {"codigo" : codigo, "nombre" : nombre, "imagen" : imagen, "precio" : precio, "cantidad" : cantidad, "total" : (cantidad * precio)};
}

function compareItems(newItem) {
    for (let item of carrito){
        if(item === newItem){
            return false;
        }
    } return true;
}

function addToLocalStorage(codigo, nombre, imagen, precio, cantidad) {
    if (carrito === null) {
        carrito = [];
    }

    if (carrito === "[]"){
        carrito.push(newItem);
    }else {
        for (let item of carrito) {
            if(item.codigo === newItem.codigo){
                item.cantidad += newItem.cantidad;
            } else if(compareItems(newItem)){

            }
        }
    }

    carrito.push({"codigo" : codigo, "nombre" : nombre, "imagen" : imagen, "precio" : precio, "cantidad" : cantidad, "total" : (cantidad * precio)});
    localStorage.setItem("carrito", JSON.stringify(carrito));
}

function printLocalStorage() {
    content = document.getElementById('localStorage');
    content.innerHTML = '';
    for (let item of carrito) {
        content.innerHTML += `
    <div class="col-12">
      <p>${item.codigo} ${item.nombre} ${item.imagen} ${item.precio} ${item.cantidad} ${item.total}</p>
    </div>
    `
    }
}

function deleteLocalStorage() {
    localStorage.removeItem("carrito");
    usuarios = [];
    printLocalStorage();
}