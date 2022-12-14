package edu.nyu.nsg2057.webscraper.service.scraper;

import edu.nyu.nsg2057.webscraper.constant.URLconstant;
import edu.nyu.nsg2057.webscraper.constant.Ecom;
import edu.nyu.nsg2057.webscraper.helper.HTMLDownloader;
import edu.nyu.nsg2057.webscraper.helper.StringPraser;
import edu.nyu.nsg2057.webscraper.model.EcomData;
import edu.nyu.nsg2057.webscraper.model.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class AmazonScraper implements Scraper {

    public List<Product> getAmazonProductDetail(String keyword) {
        System.out.println("AmazonScraper");
        return getAmazonProductList(keyword).stream().map(this::getModelIDAmazon).filter(Product::isValidModel).collect(Collectors.toList());
    }

    Product getModelIDAmazon(Product as) {
        String url = URLconstant.AMAZON + as.getPriceList().get(Ecom.AMAZON).getURL();
        Document doc = Jsoup.parse(new HTMLDownloader().getHTML(url));
        Optional<Element> st = doc.getElementsByTag("table").stream().flatMap(a -> a.getElementsByAttributeValueStarting("id", "productDetails").stream().flatMap(b -> b.getElementsByTag("tr").stream().filter(c -> c.text().contains("model number")).map(d -> d.getElementsByAttributeValueContaining("class", "prodDetAttrValue").first()))).findFirst();
        st.ifPresent(element -> as.setModelID(element.text().replaceAll("\\p{C}", "")));
        return as;
    }

    public List<Product> getAmazonProductList(String keyword) {
        Document doc = Jsoup.parse(new HTMLDownloader().getHTML(URLconstant.AMAZON_LIST + keyword));
        Elements elements = doc.getElementsByAttribute("data-index");
        List<Product> productList = new ArrayList<>();
        elements.forEach(element -> {
            Product product = new Product();
            product.setName(element.getElementsByTag("h2").text());
            Elements s = element.getElementsByTag("h2").select("a");
            EcomData ecomData = new EcomData();
            if (!s.isEmpty()) ecomData.setURL(s.first().attr("href"));

            s = element.getElementsByClass("a-price");
            if (!s.isEmpty()) ecomData.setPrice(s.first().getElementsByClass("a-offscreen").text());
            Map<Ecom, EcomData> priceList = new HashMap<>();
            priceList.put(Ecom.AMAZON, ecomData);
            product.setPriceList(priceList);
            s = element.getElementsByAttributeValueStarting("class", "s-product-image-container");
            if (!s.isEmpty()) product.setImgURL(s.select("img").first().attr("src"));

            if (product.isValid(Ecom.AMAZON)) productList.add(product);
        });
        return productList;
    }
    public Double getPriceChange(String endpoint) {
        Document doc = Jsoup.parse(new HTMLDownloader().getHTML(URLconstant.AMAZON + endpoint));
        return new StringPraser(doc.getElementsByAttributeValueStarting("class","a-price").first().text()).getPrice();
    }

    @Override
    public EcomData getProductDetail(String keyword) {
        return null;
    }

}
