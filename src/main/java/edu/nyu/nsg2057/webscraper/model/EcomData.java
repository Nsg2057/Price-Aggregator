package edu.nyu.nsg2057.webscraper.model;

public class EcomData {

    private String URL;
    private String Price;

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
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
