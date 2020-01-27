package com.ecolife.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.ecolife.dto.User;
import com.ecolife.dao.UserDao;

@WebServlet("/client")
public class UserController extends HttpServlet{
    private static final long serialVersionUID = -7558166539389234332L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recuperam l'acció a realitzar i es crida a la funció corresponent
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

        // Recuperam l'acció a realitzar i es crida a la funció corresponent
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
                default:
                    this.showListClient(request, response);
            }
        } else {
            this.showListClient(request, response);
        }
    }

    private void showListClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = new UserDao().listar();

        System.out.println("clientes = " + users);

        // Dades a desar a la sessió de la classe
        HttpSession session = request.getSession();
        session.setAttribute("users", users);

        // request.getRequestDispatcher("frmClient.jsp").forward(request, response);
        response.sendRedirect("listClients.jsp");
    }

    private void editClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // recuperamos el idCliente
        int idClient = Integer.parseInt(request.getParameter("idClient"));
        User user = new UserDao().findById(new User(idClient));
        request.setAttribute("user", user);
        String jspEditar = "/editClient.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);

    }

    private void insertClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.setCharacterEncoding("UTF-8");

        // recuperamos los valores del formulario agregarCliente
        String dni = request.getParameter("dni");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int phone = Integer.parseInt(request.getParameter("phone"));
        String email = request.getParameter("email");

        // Creamos el objeto de cliente (modelo)
        User user = new User(dni, name, surname, username, password, phone, email);

        // Insertamos el nuevo objeto en la base de datos
        int registrosModificados = new UserDao().create(user);
        System.out.println("Registres modificats:" + registrosModificados);

        // Redirigimos hacia accion por default
        this.showListClient(request, response);
    }

    private void updateClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.setCharacterEncoding("UTF-8");
        System.out.println("Modificar producto");

        // Recuperam els valors del formulari editClient
        int idClient = Integer.parseInt(request.getParameter("idClient"));
        String name = request.getParameter("name");
        System.out.println("Nombre: " + name);

        String dni = request.getParameter("dni");
        String surname = request.getParameter("surname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int phone = Integer.parseInt(request.getParameter("phone"));
        String email = request.getParameter("email");

        // Creamos el objeto de cliente (modelo)
        User user = new User(idClient, dni, name, surname, username, password, phone, email);

        // Modificar el objeto en la base de datos
        int registrosModificados = new UserDao().update(user);
        System.out.println("Registres modificats:" + registrosModificados);

        // Redirigimos hacia accion por default
        this.showListClient(request, response);
    }

    private void deleteClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // recuperamos los valores del formulario editarCliente
        int idClient = Integer.parseInt(request.getParameter("idClient"));

        // Creamos el objeto de cliente (modelo)
        User user = new User(idClient);

        // Eliminamos el objeto en la base de datos
        int registrosModificados = new UserDao().delete(user);
        System.out.println("Registres modificats:" + registrosModificados);

        // Redirigimos hacia accion por default
        this.showListClient(request, response);
    }

}
