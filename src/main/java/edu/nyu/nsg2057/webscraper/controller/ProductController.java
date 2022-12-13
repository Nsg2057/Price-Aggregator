package edu.nyu.nsg2057.webscraper.controller;

import edu.nyu.nsg2057.webscraper.model.Product;
import edu.nyu.nsg2057.webscraper.repository.ProductRepository;
import edu.nyu.nsg2057.webscraper.service.db.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/db")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
            ("/add")
    public String saveProduct(@RequestBody Product p) {

        productService.saveProduct(p);
        return "Added Product id " + p.getModelID();
    }

    @GetMapping("/find/{id}")

    public Optional<Product> getProduct(@PathVariable String id) {

       return  productService.getProductById(id);
    }

    @GetMapping("/all")

    public List<Product> getAllProduct() {

return productService.getAllProducts();
    }

    @DeleteMapping("/delete/{id}")

    public String deleteProduct(@PathVariable String id) {

        productService.delete(id);

        return "Delete pro for id " + id;
    }

    @PutMapping("/update")

    public String updateProduct(@RequestBody Product e) {

        productService.updateProduct(e);

        return "Updated Product for id " + e.getModelID();
    }
}
