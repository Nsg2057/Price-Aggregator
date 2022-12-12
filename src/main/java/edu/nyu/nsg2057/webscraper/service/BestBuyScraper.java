package edu.nyu.nsg2057.webscraper.service;


import edu.nyu.nsg2057.webscraper.constant.URLconstant;
import edu.nyu.nsg2057.webscraper.helper.HTMLDownloader;
import edu.nyu.nsg2057.webscraper.model.EcomData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BestBuyScraper {
    public EcomData getProductDetail(String keyword) {

        EcomData ecomData = new EcomData();
        Document doc = Jsoup.parse(new HTMLDownloader().getHTML(URLconstant.BESTBUY_SEARCH + keyword));
        System.out.println(6);
        Optional<Element> element = doc.getElementsByClass("list-item lv")
                .stream().filter(a -> !a.text().contains("Sponsored"))
                .findFirst();
        System.out.println("1");
if (element.isPresent()){
    ecomData.setPrice(element.get().text().substring(element.get().text().indexOf("$"), element.get().text().indexOf(".", element.get().text().indexOf("$"))+3));
    String html = element.get().html();
//    String id = html.substring(html.indexOf("data-testid=\"variant-")+21, html.indexOf("\"",html.indexOf("data-testid=\"variant-")+21));
    ecomData.setURL(element.get().getElementsByTag("a").attr("href"));
    System.out.println("2");

}
        System.out.println("3");

       return ecomData;
    }

    }
