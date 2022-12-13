package edu.nyu.nsg2057.webscraper.controller;


import edu.nyu.nsg2057.webscraper.helper.Ecom;
import edu.nyu.nsg2057.webscraper.model.EcomData;
import edu.nyu.nsg2057.webscraper.model.Product;
import edu.nyu.nsg2057.webscraper.service.AmazonScraper;
import edu.nyu.nsg2057.webscraper.service.BestBuyScraper;
import edu.nyu.nsg2057.webscraper.service.TargetScraper;
import edu.nyu.nsg2057.webscraper.service.WalmartScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/scraper")
public class FetchController {

    @Autowired
    AmazonScraper amazonScraper;

    @Autowired
    WalmartScraper walmartScraper;
    @Autowired
    TargetScraper targetScraper;
    @Autowired
    BestBuyScraper bestBuyScraper;

    @GetMapping(value = {"/amazon", "/amazon/{keyword}"})
    List<Product> amazonScrapper(@PathVariable Optional<String> keyword) {
        String product = "XR65A80K";
        if (keyword.isPresent()) product = keyword.get();
        return amazonScraper.getAmazonProductDetail(product);
    }

    @GetMapping(value = {"/walmart", "/walmart/{keyword}"})
    EcomData walmartScrapper(@PathVariable Optional<String> keyword) {
        String product = "XR65A80K";
        if (keyword.isPresent()) product = keyword.get();
        return walmartScraper.getWalmartProductDetail(product);
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

    @GetMapping(value = {"/comparePrice", "/comparePrice/{keyword}"})
    List<Product> comparePrice(@PathVariable Optional<String> keyword) {
        String product = "TU7000";
        if (keyword.isPresent()) product = keyword.get();
        product = URLEncoder.encode(
                product,
                StandardCharsets.UTF_8
        );
        List<Product> products = amazonScraper.getAmazonProductDetail(product);
        List<Product> output = new ArrayList<>();
        for (Product p :
                products) {
            String modelID = p.getModelID().strip();
            EcomData w = walmartScraper.getWalmartProductDetail(modelID);
            EcomData b = bestBuyScraper.getProductDetail(modelID);
            Map<Ecom, EcomData> g = p.getPriceList();
            g.put(Ecom.WALMART, w);
            g.put(Ecom.BESTBUY, b);
            p.setPriceList(g);
            output.add(p);
        }
        return output;
    }
}
