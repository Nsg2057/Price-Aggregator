package edu.nyu.nsg2057.webscraper.model;

import edu.nyu.nsg2057.webscraper.helper.Ecom;

import java.util.Map;
import java.util.Optional;

public class Product {
    private String name;
    private String imgURL;
    private String modelID;
    private Map<Ecom, EcomData> priceList;

    public Map<Ecom, EcomData> getPriceList() {
        return priceList;
    }

    public void setPriceList(Map<Ecom, EcomData> priceList) {
        this.priceList = priceList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }


    public String getModelID() {
        return modelID;
    }

    public void setModelID(String modelID) {
        this.modelID = modelID;
    }

    public Boolean isValid(Ecom ecom) {
       if(Optional.ofNullable(getPriceList().get(ecom).getURL()).isPresent()){
           return Optional.of(getPriceList().get(ecom).getURL()).get().length() < 200;
       }
           return false;
    }
    public Boolean isValidModel() {
        return Optional.ofNullable(getModelID()).isPresent();
    }

}
