package org.example.productcatalog.servlet;

import org.example.productcatalog.model.Product;
import org.example.productcatalog.repository.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ListAllProductsServlet extends HttpServlet {
    ProductRepository productRepository = ProductRepository.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> allProducts = productRepository.findProducts();
        System.out.println("All products in a DB :\n" + allProducts + '\n');
        if(!allProducts.isEmpty()){
            request.setAttribute("products", allProducts);
            request.getRequestDispatcher("/products.jsp").forward(request, response);
        }else{
            response.getWriter().println("No products available.");
        }
    }
}
