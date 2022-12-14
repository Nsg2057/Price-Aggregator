package edu.nyu.nsg2057.webscraper.model;

import edu.nyu.nsg2057.webscraper.constant.Ecom;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("MONITOR")
public class Monitor {
    @Transient
    public static final String SEQUENCE_NAME = "monitor_sequence";
    @Id
    private long id;
    private String URL;
    private Ecom ecom;
    private String modelID;
    private Double price;
    private int threshold;
    private String emailID;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    @Override
    public String toString() {
        return "Monitor{" +
                "id=" + id +
                ", URL='" + URL + '\'' +
                ", ecom=" + ecom +
                ", modelID='" + modelID + '\'' +
                ", price=" + price +
                ", threshold=" + threshold +
                ", emailID='" + emailID + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
