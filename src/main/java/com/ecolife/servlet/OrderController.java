package com.ecolife.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.ecolife.dao.OrderDao;
import com.ecolife.dto.Order;
import com.ecolife.dto.User;
import com.sun.org.apache.xpath.internal.operations.Or;


@WebServlet("/order")
public class OrderController extends HttpServlet{
    private static final long serialVersionUID = -7558166539389234332L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recuperam l'acció a realitzar i es crida a la funció corresponent
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "edit":
                    this.editOrder(request, response);
                    break;
                case "list":
                    this.listOrder(request, response);
                    break;
                default:
                    this.showListOrder(request, response);
            }
        } else {
            this.showListOrder(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Recuperam l'acció a realitzar i es crida a la funció corresponent
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "delete":
                    this.deleteOrder(request, response);
                    break;
                case "insert":
                    this.insertOrder(request, response);
                    break;
                case "update":
                    this.updateOrder(request, response);
                    break;
                default:
                    this.showListOrder(request, response);
            }
        } else {
            this.showListOrder(request, response);
        }
    }

    private void listOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User userId = new User(Integer.parseInt(request.getParameter("idCustomer")));
        List<Order> orders = new OrderDao().listByCustomer(userId);

        System.out.println("pedidos = " + orders);

        HttpSession session = request.getSession();
        session.setAttribute("customerOrders", orders);

        response.sendRedirect("customerOrders.jsp");
    }

    private void showListOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders = new OrderDao().listar();

        System.out.println("pedidos = " + orders);

        HttpSession session = request.getSession();
        session.setAttribute("orders", orders);

        response.sendRedirect("listOrders.jsp");
    }

    private void editOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idOrder = Integer.parseInt(request.getParameter("idOrder"));
        Order order = new OrderDao().findById(new Order(idOrder));
        request.setAttribute("order", order);
        String jspEditar = "/editOrder.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);

    }

    private void insertOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.setCharacterEncoding("UTF-8");

        // recuperamos los valores del formulario agregarCliente
        User userId = new User(Integer.parseInt(request.getParameter("idCustomer")));
        LocalDate date = LocalDate.now();
        double total = Double.parseDouble(request.getParameter("total"));

        Order order = new Order(userId, date, total);

        // Insertamos el nuevo objeto en la base de datos
        int registrosModificados = new OrderDao().create(order);
        System.out.println("Registres modificats:" + registrosModificados);

        response.sendRedirect("index.jsp");
        // Redirigimos hacia accion por default
        /*this.showListProduct(request, response);*/
    }

    private void updateOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Modificar pedido");

        int idOrder = Integer.parseInt(request.getParameter("idOrder"));

        User userId = new User(Integer.parseInt(request.getParameter("userId")));
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        double total = Double.parseDouble(request.getParameter("total"));

        Order order = new Order(idOrder, userId, date, total);

        // Modificar el objeto en la base de datos
        int registrosModificados = new OrderDao().update(order);
        System.out.println("Registres modificats:" + registrosModificados);

        // Redirigimos hacia accion por default
        /*this.showListProduct(request, response);*/
    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // recuperamos los valores del formulario editarCliente
        int idOrder = Integer.parseInt(request.getParameter("idOrder"));

        // Creamos el objeto de cliente (modelo)
        Order order = new Order(idOrder);

        // Eliminamos el objeto en la base de datos
        int registrosModificados = new OrderDao().delete(order);
        System.out.println("Registres modificats:" + registrosModificados);

        // Redirigimos hacia accion por default
        /*this.showListProduct(request, response);*/
    }

}
