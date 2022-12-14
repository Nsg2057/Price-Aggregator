package edu.nyu.nsg2057.webscraper.model;

import edu.nyu.nsg2057.webscraper.helper.StringPraser;

import java.util.Objects;

public class EcomData {

    private String URL;
    private Double Price;
    private String Name;

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

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EcomData)) return false;
        EcomData ecomData = (EcomData) o;
        return Objects.equals(getURL(), ecomData.getURL()) && Objects.equals(getPrice(), ecomData.getPrice()) && Objects.equals(getName(), ecomData.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getURL(), getPrice(), getName());
    }

    @Override
    public String toString() {
        return "EcomData{" +
                "URL='" + URL + '\'' +
                ", Price=" + Price +
                ", Name='" + Name + '\'' +
                '}';
    }

}
