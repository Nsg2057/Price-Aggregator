package edu.nyu.nsg2057.webscraper.controller;

import edu.nyu.nsg2057.webscraper.model.Product;
import edu.nyu.nsg2057.webscraper.service.db.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public String saveProduct(@RequestBody Product p) {
        return productService.saveProduct(p);
    }

    @GetMapping("/{id}")

    public Optional<Product> getProduct(@PathVariable String id) {

        return productService.getProductById(id);
    }

    @GetMapping

    public List<Product> getAllProduct() {

        return productService.getAllProducts();
    }

    @DeleteMapping("/{id}")

    public String deleteProduct(@PathVariable String id) {

        productService.delete(id);

        return "Delete pro for id " + id;
    }

    @PutMapping

    public String updateProduct(@RequestBody Product e) {

        productService.updateProduct(e);

        return "Updated Product for id " + e.getModelID();
    }
}
