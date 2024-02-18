package org.example.productcatalog.servlet;

import org.example.productcatalog.model.Product;
import org.example.productcatalog.repository.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/products/add")
public class AddProductServlet extends HttpServlet {
    private ProductRepository productRepository = ProductRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/addProduct.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("product-name");
        double productPrice = Double.parseDouble(request.getParameter("product-price"));

        Product newProduct = new Product();
        newProduct.setName(productName);
        newProduct.setPrice(productPrice);
        productRepository.addProduct(newProduct);

        System.out.println("Products after adding : \n" + productRepository.findProducts() + '\n');

        response.sendRedirect("/products");
    }
}
