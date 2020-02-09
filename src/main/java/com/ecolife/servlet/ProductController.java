package com.ecolife.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.ecolife.dto.Category;
import com.ecolife.dto.Product;
import com.ecolife.dao.ProductDao;
import com.ecolife.dto.Type;

@WebServlet("/product")
public class ProductController extends HttpServlet{
    private static final long serialVersionUID = -7558166539389234332L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "edit":
                    this.editProduct(request, response);
                    break;
                case "filter":
                    this.showListProductFiltered(request, response);
                    break;
                default:
                    this.showListProduct(request, response);
            }
        } else {
            this.showListProduct(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "delete":
                    this.deleteProduct(request, response);
                    break;
                case "insert":
                    this.insertProduct(request, response);
                    break;
                case "update":
                    this.updateProduct(request, response);
                    break;
                case "search":
                    this.showListProductByName(request, response);
                    break;
                default:
                    this.showListProduct(request, response);
            }
        } else {
            this.showListProduct(request, response);
        }
    }

    private void showListProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = new ProductDao().listar();

        System.out.println("productos = " + products);

        HttpSession session = request.getSession();
        session.setAttribute("products", products);

        response.sendRedirect("listProducts.jsp");
    }

    private void showListProductByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("productSearch");
        List<Product> productsFiltered = new ProductDao().listByName(productName);

        System.out.println("productosFiltrados = " + productsFiltered);

        HttpSession session = request.getSession();
        if (productName.equals("")) {
            session.setAttribute("productsFiltered", null);
        } else {
            session.setAttribute("productsFiltered", productsFiltered);
        }


        response.sendRedirect("index.jsp");
    }

    private void showListProductFiltered(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryProduct = request.getParameter("categoryProduct");
        List<Product> productsFiltered = new ProductDao().listByCategory(categoryProduct);

        System.out.println("productosFiltrados = " + productsFiltered);

        HttpSession session = request.getSession();
        if (categoryProduct.equals("null")) {
            session.setAttribute("productsFiltered", null);
        } else {
            session.setAttribute("productsFiltered", productsFiltered);
        }


        response.sendRedirect("index.jsp");
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int idProduct = Integer.parseInt(request.getParameter("idProduct"));
        Product product = new ProductDao().findById(new Product(idProduct));
        request.setAttribute("product", product);
        String jspEditar = "/editProduct.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);

    }

    private void insertProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        double weight = Double.parseDouble(request.getParameter("weight"));
        String cover = request.getParameter("cover");
        String description = request.getParameter("description");
        Category category = Category.valueOf(request.getParameter("category"));
        Type type = Type.valueOf(request.getParameter("type"));

        Product product = new Product(name, price, weight, cover, description, category, type);

        int registrosModificados = new ProductDao().create(product);
        System.out.println("Registres modificats:" + registrosModificados);

        this.showListProduct(request, response);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Modificar producto");

        int idProduct = Integer.parseInt(request.getParameter("idProduct"));
        String name = request.getParameter("name");
        System.out.println("Nombre: " + name);

        double price = Double.parseDouble(request.getParameter("price"));
        double weight = Double.parseDouble(request.getParameter("weight"));
        String cover = request.getParameter("cover");
        String description = request.getParameter("description");
        Category category = Category.valueOf(request.getParameter("category"));
        Type type = Type.valueOf(request.getParameter("type"));

        Product product = new Product(idProduct, name, price, weight, cover, description, category, type);

        int registrosModificados = new ProductDao().update(product);
        System.out.println("Registres modificats:" + registrosModificados);

        this.showListProduct(request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int idProduct = Integer.parseInt(request.getParameter("idProduct"));

        Product product = new Product(idProduct);

        int registrosModificados = new ProductDao().delete(product);
        System.out.println("Registres modificats:" + registrosModificados);

        this.showListProduct(request, response);
    }

}
