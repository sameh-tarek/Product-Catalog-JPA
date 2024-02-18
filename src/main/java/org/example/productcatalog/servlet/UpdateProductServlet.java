package org.example.productcatalog.servlet;

import org.example.productcatalog.model.Product;
import org.example.productcatalog.repository.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/products/update")
public class UpdateProductServlet extends HttpServlet {
    private ProductRepository productRepository = ProductRepository.getInstance();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        String productName = request.getParameter("product-name");
        double productPrice = Double.parseDouble(request.getParameter("product-price"));

        Product existingProduct = productRepository.findById(productId);
        System.out.println("The product you want to update:\n" + existingProduct + '\n');

        existingProduct.setName(productName);
        existingProduct.setPrice(productPrice);
        productRepository.updateProduct(existingProduct);

        System.out.println("Products after updating : \n" + productRepository.findProducts() + '\n');

        response.sendRedirect("/products");
    }
}
