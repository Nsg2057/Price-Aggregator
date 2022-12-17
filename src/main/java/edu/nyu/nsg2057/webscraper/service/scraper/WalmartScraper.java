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
public class WalmartScraper implements Scraper {
    public EcomData getProductDetail(String keyword) {
        EcomData ecomData = new EcomData();
        Document doc = Jsoup.parse(new HTMLDownloader().getHTML(URLconstant.WALMART_SEARCH + keyword));
        Optional<Element> element = doc.getElementsByAttributeValue("data-testid", "list-view").stream().limit(10).filter(a -> !a.text().contains("Sponsored")).findFirst();

        if (element.isPresent()) {
            ecomData.setPrice(new StringPraser(element.get().text()).getPrice());
            String html = element.get().html();
            String id = html.substring(html.indexOf("data-testid=\"variant-") + 21, html.indexOf("\"", html.indexOf("data-testid=\"variant-") + 21));
            ecomData.setURL(doc.getElementsByAttributeValue("data-item-id", id).first().getElementsByTag("a").attr("href"));

        }
        return ecomData;
    }

    public Double getPriceChange(String endpoint) {
        Document doc = Jsoup.parse(new HTMLDownloader().getHTML(URLconstant.WALMART + endpoint));
        Optional<Element> e = Optional.ofNullable(doc.getElementsByAttributeValue("itemprop", "price").first());
        return e.map(element -> new StringPraser(element.text()).getPrice()).orElse(null);
    }

}
