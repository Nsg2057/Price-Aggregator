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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @GetMapping(path = "/amazon")
    List<Product> amazonScrapper(){
        String product = "XR65A80K";
    return amazonScraper.getAmazonProductDetail(product);
    }

    @GetMapping(path = "/walmart")
    EcomData walmartScrapper(){
        String product = "XR65A80K";
        byte[] keywordBytes = product.getBytes();
        String asciiEncodedString = new String(keywordBytes, StandardCharsets.US_ASCII);
        return walmartScraper.getWalmartProductDetail(asciiEncodedString);
    }

    @GetMapping(path = "/bestBuy")
    EcomData bestBuyScrapper(){
        String product = "TU7000";
        System.out.println(10);
        return bestBuyScraper.getProductDetail(product);
    }

    @GetMapping(path = "/target")
    String targetScrapper(){
        String product = "TU7000";
        return targetScraper.getTargetProductDetail(product);
    }

    @GetMapping(path = "/comparePrice")
    List<Product> comparePrice(){
        String product = "TU7000";
        product = URLEncoder.encode(product);
//        return amazonScraper.getAmazonProductDetail(product).stream().peek(a -> a.getPriceList().put(Ecom.WALMART, walmartScraper.getWalmartProductDetail(a.getModelID()))).collect(Collectors.toList());
        List<Product> products =  amazonScraper.getAmazonProductDetail(product);
        List<Product> output = new ArrayList<>();
        for (Product p:
                products) {
            String keyword = p.getModelID().strip();
            byte[] keywordBytes = keyword.getBytes(StandardCharsets.UTF_8);
            String asciiEncodedString = new String(keywordBytes, StandardCharsets.US_ASCII);
            EcomData w = walmartScraper.getWalmartProductDetail(Normalizer.normalize(asciiEncodedString, Normalizer.Form.NFKD));
            EcomData b = bestBuyScraper.getProductDetail(keyword);
            Map<Ecom, EcomData> g  = p.getPriceList();
            g.put(Ecom.WALMART, w);
            g.put(Ecom.BESTBUY, b);
            p.setPriceList(g);
            output.add(p);
        }
        return  output;
    }
}
