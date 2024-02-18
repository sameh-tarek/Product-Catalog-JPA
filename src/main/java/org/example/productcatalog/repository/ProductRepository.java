package org.example.productcatalog.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.productcatalog.model.Product;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public class ProductRepository {

    private static volatile ProductRepository instance;

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    private ProductRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("ProductCatalogPersistenceUnit");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static ProductRepository getInstance() {
        // double-checked locking
        if(instance == null) {
            synchronized (ProductRepository.class) {
                if (instance == null) {
                    instance = new ProductRepository();
                }
            }
        }
        return instance;
    }

    public List<Product> findProducts() {
        return entityManager.createQuery("SELECT p FROM Product p",Product.class).getResultList();
    }

    public void addProduct(Product product) {
        try{
            begin();
            entityManager.persist(product);
            commit();
        }catch (Exception exception){
            rollback();
        }
    }
    public Product findById(Integer id){
        Product product = entityManager.find(Product.class ,id);
        return product;
    }
    public void updateProduct(Product product){
        addProduct(product);
    }
    public void deleteProduct(int id) {
        try {
            begin();
            Product product = findById(id);
            entityManager.remove(product);
            commit();
        }catch (Exception exception){
            rollback();
        }
    }


    private void begin(){
        entityManager.getTransaction().begin();
    }
    private void commit(){
        entityManager.getTransaction().commit();
    }
    private void rollback(){
        entityManager.getTransaction().rollback();
    }
}