package edu.nyu.nsg2057.webscraper.model;

import edu.nyu.nsg2057.webscraper.helper.StringPraser;

public class EcomData {

    private String URL;
    private Double Price;

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = new StringPraser().getDoublePrice(price);
    }

    public void setPrice(Double price) {
        Price = price;
    }

    @Override
    public String toString() {
        return "EcomData{" +
                "URL='" + URL + '\'' +
                ", Price='" + Price + '\'' +
                '}';
    }
}
