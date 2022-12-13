package edu.nyu.nsg2057.webscraper.model;

import edu.nyu.nsg2057.webscraper.constant.Ecom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.Optional;
@Document("PRODUCT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    private String name;
    private String imgURL;
    @Id
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