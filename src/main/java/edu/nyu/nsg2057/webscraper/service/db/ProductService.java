package edu.nyu.nsg2057.webscraper.service.db;

import edu.nyu.nsg2057.webscraper.model.Product;
import edu.nyu.nsg2057.webscraper.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String id) {

        return productRepository.findById(id);
    }

    public void updateProduct(Product product) {
        productRepository.save(product);
        System.out.println("Product Updated");
    }

    public String saveProduct(Product p) {

        productRepository.insert(p);

        return "Added Product id " + p.getModelID();
    }

    public void delete(String id) {
        productRepository.deleteById(id);
    }
}
