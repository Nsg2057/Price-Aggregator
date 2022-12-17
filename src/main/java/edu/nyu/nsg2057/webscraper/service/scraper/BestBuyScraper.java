package edu.nyu.nsg2057.webscraper.service.scraper;


import edu.nyu.nsg2057.webscraper.constant.URLconstant;
import edu.nyu.nsg2057.webscraper.helper.HTMLDownloader;
import edu.nyu.nsg2057.webscraper.helper.StringPraser;
import edu.nyu.nsg2057.webscraper.model.EcomData;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BestBuyScraper implements Scraper{
    public EcomData getProductDetail(String keyword) {
        EcomData ecomData = new EcomData();
        Document doc = new HTMLDownloader().getHTMLfromJSP(URLconstant.BESTBUY_SEARCH + keyword);
        Optional<Element> element = doc.getElementsByClass("list-item lv").stream().limit(10).filter(a -> !a.text().contains("Sponsored")).findFirst();
        if (element.isPresent()) {
            String text = element.get().text();
            ecomData.setPrice(text.substring(text.indexOf("$"), text.indexOf(".", text.indexOf("$")) + 3));
            ecomData.setURL(element.get().getElementsByTag("a").attr("href"));
        }

        return ecomData;
    }
    public Double getPriceChange(String endpoint) {
        Document doc = new HTMLDownloader().getHTMLfromJSP(URLconstant.BESTBUY + endpoint);
        Optional<Element> e = Optional.ofNullable(doc.getElementsByAttributeValueStarting("class","price-box").first());
        return e.map(element -> new StringPraser(element.text()).getPrice()).orElse(null);
    }

}
