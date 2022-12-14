package edu.nyu.nsg2057.webscraper.service.scheduler;

import edu.nyu.nsg2057.webscraper.constant.Ecom;
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
    public void updateProductDB() throws InterruptedException {
        EmailDetails ed = new EmailDetails("venkatesha2017@gmail.com", "test", "test");
        System.out.println(emailService.sendSimpleMail(ed));
        System.out.println(
                "updateProduct JOB - " + System.currentTimeMillis() / 1000);
//        productService.getAllProducts().forEach(p -> priceFetchJob(p));
    }


    private void priceFetchJob(Product p) {
        String modelID = p.getModelID().strip();
        EcomData w = walmartScraper.getProductDetail(modelID);
        EcomData b = bestBuyScraper.getProductDetail(modelID);
        Map<Ecom, EcomData> g = p.getPriceList();
        g.put(Ecom.WALMART, w);
        g.put(Ecom.BESTBUY, b);
        p.setPriceList(g);
        productService.updateProduct(p);
    }


    @Async
    @Scheduled(fixedRate = 360000)
    public void checkPriceChange() throws InterruptedException {

        System.out.println(
                "PriceChange JOB - " + System.currentTimeMillis() / 1000);
        monitorService.getAllMonitors().forEach(a -> {
            Scraper s = createObject(a.getEcom());
            Double p = s.getPriceChange(a.getURL());
            if (p != a.getPrice()) {
                String sb = "Product = " + a.getName() +
                        "\n" +
                        "in " + a.getEcom() +
                        "\n" +
                        "Price changed from " + a.getPrice() + " to " + p +
                        "\n" +
                        "\n" +
                        "\n" +
                        a;
                EmailDetails ed = new EmailDetails(a.getEmailID(), sb, "Price Changed " + a.getModelID());
                System.out.println(emailService.sendSimpleMail(ed));
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


}
