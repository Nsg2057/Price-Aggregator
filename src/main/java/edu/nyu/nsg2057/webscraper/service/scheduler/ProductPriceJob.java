package edu.nyu.nsg2057.webscraper.service.scheduler;

import edu.nyu.nsg2057.webscraper.constant.Ecom;
import edu.nyu.nsg2057.webscraper.constant.URLconstant;
import edu.nyu.nsg2057.webscraper.model.EcomData;
import edu.nyu.nsg2057.webscraper.model.EmailDetails;
import edu.nyu.nsg2057.webscraper.model.Product;
import edu.nyu.nsg2057.webscraper.service.db.MonitorService;
import edu.nyu.nsg2057.webscraper.service.db.ProductService;
import edu.nyu.nsg2057.webscraper.service.scraper.AmazonScraper;
import edu.nyu.nsg2057.webscraper.service.scraper.BestBuyScraper;
import edu.nyu.nsg2057.webscraper.service.scraper.Scraper;
import edu.nyu.nsg2057.webscraper.service.scraper.WalmartScraper;
import edu.nyu.nsg2057.webscraper.service.smtp.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
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
    @Autowired
    AmazonScraper amazonScraper;
    @Autowired
    EmailService emailService;
    @Autowired
    MonitorService monitorService;


    @Async
    @Scheduled(fixedRate = 360000)
    public void updateProductDB() {
        EmailDetails ed = new EmailDetails("venkatesha2017@gmail.com", "Update product Job Started", "Update product Job Started at " + LocalDateTime.now());
        System.out.println(emailService.sendSimpleMail(ed));
//        productService.getAllProducts().forEach(this::priceFetchJob);
        ed = new EmailDetails("venkatesha2017@gmail.com", "Update product Job Stopped", "Update product Job Stopped at " + LocalDateTime.now());
        System.out.println(emailService.sendSimpleMail(ed));
    }


    private void priceFetchJob(Product p) {
        Map<Ecom, EcomData> g = p.getPriceList();
        EcomData a = g.get(Ecom.AMAZON);
        EcomData w = g.get(Ecom.WALMART);
        EcomData b = g.get(Ecom.BESTBUY);
        if (a.getURL() != null) a.setPrice(amazonScraper.getPriceChange(a.getURL()));
        if (w.getURL() != null) w.setPrice(walmartScraper.getPriceChange(w.getURL()));
        if (b.getURL() != null) b.setPrice(bestBuyScraper.getPriceChange(b.getURL()));
        g.put(Ecom.WALMART, w);
        g.put(Ecom.BESTBUY, b);
        g.put(Ecom.AMAZON, a);
        p.setPriceList(g);
        productService.updateProduct(p);
    }


    @Async
    @Scheduled(fixedRate = 3600000)
    public void checkPriceChange() {

        System.out.println("PriceChange JOB - " + System.currentTimeMillis() / 1000);
        monitorService.getAllMonitors().forEach(a -> {
            Scraper s = createObject(a.getEcom());
            Double p = s.getPriceChange(a.getURL());
            if (!p.equals(a.getPrice())) {
                String sb = "Product = " + a.getName() + "\n" + "in " + a.getEcom() + "\n" + "Price changed from " + a.getPrice() + " to " + p + "\n" + "\n" + getHomePage(a.getEcom()) + a.getURL() + "\n" + a;
                EmailDetails ed = new EmailDetails(a.getEmailID(), sb, "Price Changed " + a.getModelID());
                System.out.println(emailService.sendSimpleMail(ed));
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
            default:
                return null;
        }

    }


}
