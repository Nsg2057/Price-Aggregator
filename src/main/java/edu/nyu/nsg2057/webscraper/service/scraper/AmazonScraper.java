package edu.nyu.nsg2057.webscraper.service.scraper;

import edu.nyu.nsg2057.webscraper.constant.Ecom;
import edu.nyu.nsg2057.webscraper.constant.URLconstant;
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
        return getAmazonProductList(keyword).parallelStream().map(this::getModelIDAmazon).filter(Product::isValidModel).collect(Collectors.toList());
    }

    Product getModelIDAmazon(Product as) {
        String url = URLconstant.AMAZON + as.getPriceList().get(Ecom.AMAZON).getURL();
        Document doc = Jsoup.parse(new HTMLDownloader().getHTML(url));
        Optional<Element> st = doc.getElementsByTag("table").parallelStream().flatMap(a -> a.getElementsByAttributeValueStarting("id", "productDetails").parallelStream().flatMap(b -> b.getElementsByTag("tr").parallelStream().filter(c -> c.text().contains("model number")).map(d -> d.getElementsByAttributeValueContaining("class", "prodDetAttrValue").first()))).findFirst();
        st.ifPresent(element -> as.setModelID(element.text().replaceAll("\\p{C}", "")));
        return as;
    }

    public List<Product> getAmazonProductList(String keyword) {
        Document doc = Jsoup.parse(new HTMLDownloader().getHTML(URLconstant.AMAZON_LIST + keyword));
        Elements elements = doc.getElementsByAttribute("data-index");
        List<Product> productList = new ArrayList<>();
        elements.stream().limit(10).parallel().forEach(element -> {
            Product product = new Product();
            EcomData ecomData = new EcomData();
            product.setName(element.getElementsByTag("h2").text());
            ecomData.setName(product.getName());

            Elements s = element.getElementsByTag("h2").select("a");
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
        Optional<Element> e = Optional.ofNullable(doc.getElementsByAttributeValueStarting("class", "a-price").first());
        return e.map(element -> new StringPraser(element.text()).getPrice()).orElse(null);
    }

    @Override
    public EcomData getProductDetail(String keyword) {
        return null;
    }

}
