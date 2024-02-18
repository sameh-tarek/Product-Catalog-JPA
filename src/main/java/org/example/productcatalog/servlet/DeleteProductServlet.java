package org.example.productcatalog.servlet;

import org.example.productcatalog.repository.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/products/delete")
public class DeleteProductServlet extends HttpServlet {
    private ProductRepository productRepository = ProductRepository.getInstance();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        productRepository.deleteProduct(productId);

        System.out.println("Products after deletion : \n" + productRepository.findProducts() + '\n');

        response.sendRedirect("/products");
    }
}
