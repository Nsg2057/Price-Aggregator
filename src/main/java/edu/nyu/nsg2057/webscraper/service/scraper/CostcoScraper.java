package edu.nyu.nsg2057.webscraper.service.scraper;


import edu.nyu.nsg2057.webscraper.constant.URLconstant;
import edu.nyu.nsg2057.webscraper.helper.HTMLDownloader;
import edu.nyu.nsg2057.webscraper.helper.StringPraser;
import edu.nyu.nsg2057.webscraper.model.EcomData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CostcoScraper implements Scraper {
    public EcomData getProductDetail(String keyword) {
        EcomData ecomData = new EcomData();
        Document doc = new HTMLDownloader().getHTMLfromJSP(URLconstant.COSTCO_SEARCH + keyword);
        if(doc.text().contains("We were not able to find a match") || doc.text().contains("We found no matches")) return ecomData;
        Optional<Element> element = doc.getElementsByClass("product-tile-set").stream().limit(5).filter(a -> !a.text().contains("Sponsored")).findFirst();

        if (element.isPresent()) {
            ecomData.setPrice(new StringPraser(element.get().text()).getPrice());
            ecomData.setURL(element.get().getElementsByClass("description").first().getElementsByTag("a").attr("href").replaceAll("https://www.costco.com",""));
        }
        return ecomData;
    }

    public Double getPriceChange(String endpoint) {
        Document doc = Jsoup.parse(new HTMLDownloader().getHTML(URLconstant.WALMART + endpoint));
        Optional<Element> e = Optional.ofNullable(doc.getElementById("pull-right-price"));
        return e.map(element -> new StringPraser(element.text()).getPrice()).orElse(null);
    }

}
