package edu.nyu.nsg2057.webscraper.service.scraper;

import edu.nyu.nsg2057.webscraper.model.EcomData;

public interface Scraper {
    Double getPriceChange(String endpoint);

    EcomData getProductDetail(String keyword);
}
