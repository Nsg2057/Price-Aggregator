package edu.nyu.nsg2057.webscraper.service.scraper;

import edu.nyu.nsg2057.webscraper.model.EcomData;

public interface Scraper {
    public Double getPriceChange(String endpoint);
    public EcomData getProductDetail(String keyword);
}
