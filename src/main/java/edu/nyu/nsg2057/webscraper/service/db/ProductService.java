package edu.nyu.nsg2057.webscraper.service.db;

import edu.nyu.nsg2057.webscraper.model.Product;
import edu.nyu.nsg2057.webscraper.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        return allProducts;
    }

    public Optional<Product> getProductById(String id) {
        Optional<Product> pro = productRepository.findById(id);

        return pro;
    }

    public void updateProduct(Product product) {
        productRepository.save(product);
    }
    public String saveProduct(Product p) {

        productRepository.insert(p);

        return "Added Product id " + p.getModelID();
    }

    public void delete(String id) {
        productRepository.deleteById(id);
    }
}
