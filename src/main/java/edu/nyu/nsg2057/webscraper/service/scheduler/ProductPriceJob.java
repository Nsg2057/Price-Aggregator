package edu.nyu.nsg2057.webscraper.service.scheduler;

import edu.nyu.nsg2057.webscraper.constant.Ecom;
import edu.nyu.nsg2057.webscraper.model.EcomData;
import edu.nyu.nsg2057.webscraper.model.Product;
import edu.nyu.nsg2057.webscraper.service.db.ProductService;
import edu.nyu.nsg2057.webscraper.service.scraper.BestBuyScraper;
import edu.nyu.nsg2057.webscraper.service.scraper.TargetScraper;
import edu.nyu.nsg2057.webscraper.service.scraper.WalmartScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@EnableAsync
@Component
public class ProductPriceJob {
    @Autowired
    ProductService productService;
    @Autowired
    WalmartScraper walmartScraper;
    @Autowired
    BestBuyScraper bestBuyScraper;




    @Async
        @Scheduled(fixedRate = 3600000)
        public void scheduleFixedRateTaskAsync() throws InterruptedException {
            System.out.println(
                    "Fixed rate task async - " + System.currentTimeMillis() / 1000);
        productService.getAllProducts().forEach(p -> priceFetchJob(p));
        }


        private void priceFetchJob(Product p){
            String modelID = p.getModelID().strip();
            EcomData w = walmartScraper.getWalmartProductDetail(modelID);
            EcomData b = bestBuyScraper.getProductDetail(modelID);
            Map<Ecom, EcomData> g = p.getPriceList();
            g.put(Ecom.WALMART, w);
            g.put(Ecom.BESTBUY, b);
            p.setPriceList(g);
            productService.updateProduct(p);
        }


}
