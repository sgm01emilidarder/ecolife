package com.ecolife.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.ecolife.dao.OrderItemsDao;
import com.ecolife.dto.Order;
import com.ecolife.dto.OrderItems;
import com.ecolife.dto.Product;
import com.sun.org.apache.xpath.internal.operations.Or;


@WebServlet("/orderItems")
public class OrderItemsController extends HttpServlet {
    private static final long serialVersionUID = -7558166539389234332L;
    List<OrderItems> cart = new ArrayList<>();

/*    private boolean compareCartItems(OrderItems item){
        for (OrderItems oItem : cart){
            if (oItem.getProductCode().getCode() == item.getProductCode().getCode()){
                return false;
            }
        } return true;
    }*/

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recuperam l'acció a realitzar i es crida a la funció corresponent
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "edit":
                    this.editOrderItem(request, response);
                    break;
                default:
                    this.showListOrderItem(request, response);
            }
        } else {
            this.showListOrderItem(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Recuperam l'acció a realitzar i es crida a la funció corresponent
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "delete":
                    this.deleteOrderItem(request, response);
                    break;
                case "insert":
                    this.insertOrderItem(request, response);
                    break;
                case "update":
                    this.updateOrderItem(request, response);
                    break;
                /*case "addItem":
                    this.addItemToList(request, response);
                    break;*/
                default:
                    this.showListOrderItem(request, response);
            }
        } else {
            this.showListOrderItem(request, response);
        }
    }

   /* private void addItemToList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Product idProduct = new Product(Integer.parseInt(request.getParameter("idProduct")));
        double price = Double.parseDouble(request.getParameter("price"));
        double quantity = Double.parseDouble(request.getParameter("quantity"));

        OrderItems newItem = new OrderItems(idProduct, price, quantity);

        if (cart.isEmpty()) {
            cart.add(newItem);
        } else {

            for (OrderItems cartItem : new ArrayList<>(cart)) {
                if (cartItem.getProductCode().getCode() == newItem.getProductCode().getCode()) {
                    cartItem.setQuantity(cartItem.getQuantity() + quantity);
                }else if (compareCartItems(newItem)) {
                    cart.add(newItem);
                }
            }
        }


        System.out.println("carrito = " + cart);

        HttpSession session = request.getSession();
        session.setAttribute("cart", cart);

        response.sendRedirect("index.jsp");
    }*/

    private void showListOrderItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<OrderItems> items = new OrderItemsDao().listar();

        System.out.println("items = " + items);

        HttpSession session = request.getSession();
        session.setAttribute("items", items);

        response.sendRedirect("listItems.jsp");
    }

    private void editOrderItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order idOrder = new Order(Integer.parseInt(request.getParameter("idOrder")));
        Product idProduct = new Product(Integer.parseInt(request.getParameter("idProduct")));
        OrderItems item = new OrderItemsDao().findById(new OrderItems(idOrder, idProduct));
        request.setAttribute("item", item);
        String jspEditar = "/editOrderItem.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);

    }

    private void insertOrderItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Order idOrder = new Order(Integer.parseInt(request.getParameter("idOrder")));
        Product idProduct = new Product(Integer.parseInt(request.getParameter("idProduct")));
        double price = Double.parseDouble(request.getParameter("unit_price"));
        double quantity = Double.parseDouble(request.getParameter("quantity"));

        OrderItems item = new OrderItems(idOrder, idProduct, price, quantity);

        int registrosModificados = new OrderItemsDao().create(item);
        System.out.println("Registres modificats:" + registrosModificados);

        // Redirigimos hacia accion por default
        /*this.showListProduct(request, response);*/
    }

    private void updateOrderItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Modificar pedido");

        Order idOrder = new Order(Integer.parseInt(request.getParameter("idOrder")));
        Product idProduct = new Product(Integer.parseInt(request.getParameter("idProduct")));

        double price = Double.parseDouble(request.getParameter("unit_price"));
        double quantity = Double.parseDouble(request.getParameter("quantity"));


        OrderItems item = new OrderItems(idOrder, idProduct, price, quantity);

        // Modificar el objeto en la base de datos
        int registrosModificados = new OrderItemsDao().update(item);
        System.out.println("Registres modificats:" + registrosModificados);

        // Redirigimos hacia accion por default
        /*this.showListProduct(request, response);*/
    }

    private void deleteOrderItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // recuperamos los valores del formulario editarCliente
        Order idOrder = new Order(Integer.parseInt(request.getParameter("idOrder")));
        Product idProduct = new Product(Integer.parseInt(request.getParameter("idProduct")));

        // Creamos el objeto de cliente (modelo)
        OrderItems item = new OrderItems(idOrder, idProduct);

        // Eliminamos el objeto en la base de datos
        int registrosModificados = new OrderItemsDao().delete(item);
        System.out.println("Registres modificats:" + registrosModificados);

        // Redirigimos hacia accion por default
        /*this.showListProduct(request, response);*/
    }

}
