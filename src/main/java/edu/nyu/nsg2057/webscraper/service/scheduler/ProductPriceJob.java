package edu.nyu.nsg2057.webscraper.service.scheduler;

import edu.nyu.nsg2057.webscraper.constant.Ecom;
import edu.nyu.nsg2057.webscraper.constant.URLconstant;
import edu.nyu.nsg2057.webscraper.model.EcomData;
import edu.nyu.nsg2057.webscraper.model.EmailDetails;
import edu.nyu.nsg2057.webscraper.model.Product;
import edu.nyu.nsg2057.webscraper.service.db.MonitorService;
import edu.nyu.nsg2057.webscraper.service.db.ProductService;
import edu.nyu.nsg2057.webscraper.service.scraper.*;
import edu.nyu.nsg2057.webscraper.service.smtp.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@EnableAsync
@Component
public class ProductPriceJob {
    @Autowired
    ProductService productService;
    @Autowired
    WalmartScraper walmartScraper;
    @Autowired
    BestBuyScraper bestBuyScraper;
    @Autowired
    AmazonScraper amazonScraper;
    @Autowired
    EmailService emailService;
    @Autowired
    MonitorService monitorService;

    @Async
    @Scheduled(fixedRate = 3600000)
    public void updateProductDB() {
        Executor executor = Executors.newCachedThreadPool();
        productService.getAllProducts().parallelStream().forEach(
                p ->{
                    executor.execute(() -> {
                        try {
                            priceFetchJob(p);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                });
    }


    private void priceFetchJob(Product p) {
        Map<Ecom, EcomData> g = p.getPriceList();
        EcomData a = g.get(Ecom.AMAZON);
        EcomData w = g.get(Ecom.WALMART);
        EcomData b = g.get(Ecom.BESTBUY);
        Map<Ecom, EcomData> n = new HashMap<>();
        if (a.getURL() != null) a.setPrice(amazonScraper.getPriceChange(a.getURL()));
        if (w.getURL() != null) w.setPrice(walmartScraper.getPriceChange(w.getURL()));
        if (b.getURL() != null) b.setPrice(bestBuyScraper.getPriceChange(b.getURL()));
        n.put(Ecom.WALMART, w);
        n.put(Ecom.BESTBUY, b);
        n.put(Ecom.AMAZON, a);
        if (!n.equals(g)){

        p.setPriceList(n);
        productService.updateProduct(p);
        }
    }


    @Async
    @Scheduled(fixedRate = 3600000)
    public void checkPriceChange() {
        monitorService.getAllMonitors().forEach(a -> {
            Scraper s = createObject(a.getEcom());
            Double p = s.getPriceChange(a.getURL());
            if (!p.equals(a.getPrice())) {
                String status = p>a.getPrice()? "Increased":"Reduced";
                String sb = "Product = " + a.getName() + "\n" + "in " + a.getEcom() + "\n" + "Price changed from " + a.getPrice() + " to " + p + "\n" + "\n" + getHomePage(a.getEcom()) + a.getURL() + "\n" + a;
                EmailDetails ed = new EmailDetails(a.getEmailID(), sb, "Price "+status +" for " + a.getModelID());
                System.out.println("PRICE CHANGE EMAIL SENT = "+emailService.sendPrimaryEmail(ed) +" FOR "+ a.getId() );
                a.setPrice(p);
                monitorService.updateMonitor(a);
            }

        });

    }

    Scraper createObject(Ecom ecom) {
        switch (ecom) {
            case AMAZON:
                return new AmazonScraper();
            case BESTBUY:
                return new BestBuyScraper();
            case WALMART:
                return new WalmartScraper();
            case COSTCO:
                return new CostcoScraper();
            default:
                return null;
        }

    }

    String getHomePage(Ecom ecom) {

        switch (ecom) {
            case AMAZON:
                return URLconstant.AMAZON;
            case BESTBUY:
                return URLconstant.BESTBUY;
            case WALMART:
                return URLconstant.WALMART;
            case COSTCO:
                return URLconstant.COSTCO;
            default:
                return null;
        }

    }


}
