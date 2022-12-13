package edu.nyu.nsg2057.webscraper.model;

import edu.nyu.nsg2057.webscraper.constant.Ecom;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("MONITOR")
public class Monitor {
    @Id
    private int id;
    private String URL;
    private Ecom ecom;
    private String modelID;
    private int price;
    private int threshold;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public Ecom getEcom() {
        return ecom;
    }

    public void setEcom(Ecom ecom) {
        this.ecom = ecom;
    }

    public String getModelID() {
        return modelID;
    }

    public void setModelID(String modelID) {
        this.modelID = modelID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
