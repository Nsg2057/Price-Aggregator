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
public class WalmartScraper {
    public EcomData getWalmartProductDetail(String keyword) {

        EcomData ecomData = new EcomData();
        Document doc = Jsoup.parse(new HTMLDownloader().getHTML(URLconstant.WALMART_SEARCH + keyword));
        Optional<Element> element = doc.getElementsByAttributeValue("data-testid", "list-view").stream().filter(a -> !a.text().contains("Sponsored")).findFirst();

        if (element.isPresent()) {
            ecomData.setPrice(element.get().text().substring(element.get().text().indexOf("$"), element.get().text().indexOf(".", element.get().text().indexOf("$")) + 3));
            String html = element.get().html();
            String id = html.substring(html.indexOf("data-testid=\"variant-") + 21, html.indexOf("\"", html.indexOf("data-testid=\"variant-") + 21));
            ecomData.setURL(doc.getElementsByAttributeValue("data-item-id", id).first().getElementsByTag("a").attr("href"));

        }
        return ecomData;
    }

}
