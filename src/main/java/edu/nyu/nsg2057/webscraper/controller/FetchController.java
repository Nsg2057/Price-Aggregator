package edu.nyu.nsg2057.webscraper.controller;


import edu.nyu.nsg2057.webscraper.constant.Ecom;
import edu.nyu.nsg2057.webscraper.model.EcomData;
import edu.nyu.nsg2057.webscraper.model.Product;
import edu.nyu.nsg2057.webscraper.service.db.ProductService;
import edu.nyu.nsg2057.webscraper.service.scraper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static edu.nyu.nsg2057.webscraper.helper.StringPraser.encoder;

@RestController
@RequestMapping(path = "/api/scraper/")
public class FetchController {

    @Autowired
    AmazonScraper amazonScraper;

    @Autowired
    WalmartScraper walmartScraper;
    @Autowired
    TargetScraper targetScraper;
    @Autowired
    BestBuyScraper bestBuyScraper;
    @Autowired
    CostcoScraper costcoScraper;

    @Autowired
    ProductService productService;

    @GetMapping(value = {"/amazon", "/amazon/{keyword}"})
    List<Product> amazonScrapper(@PathVariable Optional<String> keyword) {
        String product = "XR65A80K";
        if (keyword.isPresent()) product = keyword.get();
        return amazonScraper.getAmazonProductDetail(encoder(product));
    }

    @GetMapping(value = {"/walmart", "/walmart/{keyword}"})
    EcomData walmartScrapper(@PathVariable Optional<String> keyword) {
        String product = "XR65A80K";
        if (keyword.isPresent()) product = keyword.get();
        return walmartScraper.getProductDetail(product);
    }

    @GetMapping(value = {"/bestBuy", "/bestBuy/{keyword}"})
    EcomData bestBuyScrapper(@PathVariable Optional<String> keyword) {
        String product = "TU7000";
        if (keyword.isPresent()) product = keyword.get();
        return bestBuyScraper.getProductDetail(product);
    }

    @GetMapping(path = "/target")
    String targetScrapper() {
        String product = "TU7000";
        return targetScraper.getTargetProductDetail(product);
    }

    @GetMapping(path = "/costco")
    EcomData costcoScrapper() {
        String product = "XR65A80K";
        return costcoScraper.getProductDetail(product);
    }

    @GetMapping(path = "/comparePrice/{keyword}")
    List<Product> comparePrice(@PathVariable("keyword") String keyword) {
        System.out.println(keyword);
        ExecutorService executor = Executors.newCachedThreadPool();
        return amazonScraper.getAmazonProductDetail(encoder(keyword)).parallelStream().peek(p -> {
            String modelID = encoder(p.getModelID().strip());
            Map<Ecom, EcomData> g = p.getPriceList();
            g.put(Ecom.WALMART, walmartScraper.getProductDetail(modelID));
            g.put(Ecom.BESTBUY, bestBuyScraper.getProductDetail(modelID));
            g.put(Ecom.COSTCO,  costcoScraper.getProductDetail(modelID));
            p.setPriceList(g);
        }).peek(p -> executor.execute(() -> productService.updateProduct(p))).collect(Collectors.toList());
    }

}
