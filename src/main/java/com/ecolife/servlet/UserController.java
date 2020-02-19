package com.ecolife.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.ecolife.dao.OrderDao;
import com.ecolife.dto.User;
import com.ecolife.dao.UserDao;

@WebServlet("/client")
public class UserController extends HttpServlet{
    private static final long serialVersionUID = -7558166539389234332L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "edit":
                    this.editClient(request, response);
                    break;
                default:
                    this.showListClient(request, response);
            }
        } else {
            this.showListClient(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "delete":
                    this.deleteClient(request, response);
                    break;
                case "insert":
                    this.insertClient(request, response);
                    break;
                case "update":
                    this.updateClient(request, response);
                    break;
                case "create":
                    this.createClient(request, response);
                    break;
                case "login":
                    this.loginClient(request, response);
                    break;
                case "close":
                    this.closeClient(request, response);
                    break;
                default:
                    this.showListClient(request, response);
            }
        } else {
            this.showListClient(request, response);
        }
    }

    private void returnToIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }

    private void closeClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("user", null);
        response.sendRedirect("index.jsp");
    }

    private void showListClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = new UserDao().listar();

        System.out.println("clientes = " + users);

        HttpSession session = request.getSession();
        session.setAttribute("users", users);

        response.sendRedirect("listClients.jsp");
    }

    private void editClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int idClient = Integer.parseInt(request.getParameter("idClient"));
        User user = new UserDao().findById(new User(idClient));
        request.setAttribute("user", user);
        String jspEditar = "/editClient.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);

    }

    private void loginClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userUsername = request.getParameter("userUsername");
        String userPass = request.getParameter("userPass");
        User user = new UserDao().findByUserPass(new User(userUsername, userPass));

        System.out.println(user);

       if(user.getUsername().equals("admin") && user.getDni() != null) {
           HttpSession session = request.getSession();
           session.setAttribute("user", user);
           response.sendRedirect("listClients.jsp");
       } else if(user.getDni() != null) {
           HttpSession session = request.getSession();
           session.setAttribute("user", user);

           int numOrders = new OrderDao().countOrdersByCustomerIdAndMonth(user);
           session.setAttribute("numOrders", numOrders);

           response.sendRedirect("index.jsp");
       } else {
           response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Usuario o contraseña incorrectos");
       }

    }

    private void insertClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String dni = request.getParameter("dni");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int phone = Integer.parseInt(request.getParameter("phone"));
        String email = request.getParameter("email");

        User user = new User(dni, name, surname, username, password, phone, email);

        int registrosModificados = new UserDao().create(user);
        System.out.println("Registres modificats:" + registrosModificados);

        this.showListClient(request, response);
    }

    private void createClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String dni = request.getParameter("dni");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int phone = Integer.parseInt(request.getParameter("phone"));
        String email = request.getParameter("email");

        User user = new User(dni, name, surname, username, password, phone, email);

        int registrosModificados = new UserDao().create(user);
        System.out.println("Registres modificats:" + registrosModificados);

        this.returnToIndex(request, response);
    }

    private void updateClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Modificar producto");

        int idClient = Integer.parseInt(request.getParameter("idClient"));
        String name = request.getParameter("name");
        System.out.println("Nombre: " + name);

        String dni = request.getParameter("dni");
        String surname = request.getParameter("surname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int phone = Integer.parseInt(request.getParameter("phone"));
        String email = request.getParameter("email");

        User user = new User(idClient, dni, name, surname, username, password, phone, email);

        int registrosModificados = new UserDao().update(user);
        System.out.println("Registres modificats:" + registrosModificados);

        this.showListClient(request, response);
    }

    private void deleteClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int idClient = Integer.parseInt(request.getParameter("idClient"));

        User user = new User(idClient);

        int registrosModificados = new UserDao().delete(user);
        System.out.println("Registres modificats:" + registrosModificados);

        this.showListClient(request, response);
    }

}
