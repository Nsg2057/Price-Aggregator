package edu.nyu.nsg2057.webscraper.service.scraper;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.nyu.nsg2057.webscraper.constant.URLconstant;
import edu.nyu.nsg2057.webscraper.helper.HTMLDownloader;
import edu.nyu.nsg2057.webscraper.helper.HTTPCaller;
import edu.nyu.nsg2057.webscraper.helper.StringPraser;
import edu.nyu.nsg2057.webscraper.model.EcomData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class CostcoScraper implements Scraper {
    public EcomData getProductDetail(String keyword) {
        EcomData ecomData = new EcomData();
        Document doc = new HTMLDownloader().getHTMLfromJSP(URLconstant.COSTCO_SEARCH + keyword);
        if(doc.text().contains("We were not able to find a match") || doc.text().contains("We found no matches")) return ecomData;
        Optional<Element> element = doc.getElementsByClass("product-tile-set").stream().limit(5).filter(a -> !a.text().contains("Sponsored")).findFirst();

        if (element.isPresent()) {
            ecomData.setName(element.get().getElementsByClass("description").text());
            ecomData.setPrice(new StringPraser(element.get().text()).getPrice());
            ecomData.setURL(element.get().getElementsByClass("description").first().getElementsByTag("a").attr("href").replaceAll("https://www.costco.com",""));
        }
        return ecomData;
    }

    public Double getPriceChange(String endpoint) {
      HTMLDownloader htmlDownloader=  new HTMLDownloader();
        String prodID = htmlDownloader.getHTMLfromJSP(URLconstant.COSTCO + endpoint)
                .select("input[name=productBeanId]").first().attr("value");
        JsonNode response = null;
        try {
            String s = HTTPCaller.restGetCall(URLconstant.COSTOCO_PRICE+prodID);
                response = new ObjectMapper().readTree(s);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        Double finalOnlinePrice = (double) 0;
        Double discount = (double) 0;
        try {
            finalOnlinePrice = response.get("finalOnlinePrice").asDouble();
            discount = response.get("discount").asDouble();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finalOnlinePrice - discount;
    }

}
