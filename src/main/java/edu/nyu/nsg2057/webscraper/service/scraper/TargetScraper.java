package edu.nyu.nsg2057.webscraper.service.scraper;


import edu.nyu.nsg2057.webscraper.constant.URLconstant;
import edu.nyu.nsg2057.webscraper.helper.HTMLDownloader;
import edu.nyu.nsg2057.webscraper.model.EcomData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class TargetScraper {
    public String getTargetProductDetail(String keyword) {
        Document doc = Jsoup.parse(new HTMLDownloader().getHTML(URLconstant.TARGET_SEARCH + keyword));
        return doc.html();
    }

    }
