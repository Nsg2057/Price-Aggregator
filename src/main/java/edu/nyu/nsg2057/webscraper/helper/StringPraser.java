package edu.nyu.nsg2057.webscraper.helper;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class StringPraser {
    private Double price;

    public StringPraser() {

    }

    public String retrivePrice(String line){
        return line.substring(line.indexOf("$"), line.indexOf(".", line.indexOf("$")) + 3);
    }

    public Double getDoublePrice(String line){
        line = line.replaceAll("\\$","");
        line = line.replaceAll(",","");
        System.out.println(line);
        return Double.valueOf(line);
    }

    public StringPraser(String line) {
        this.price = getDoublePrice(retrivePrice(line));
    }

    public Double getPrice() {
        return price;
    }
    public static String encoder(String text){
        return URLEncoder.encode(
                text,
                StandardCharsets.UTF_8
        );
    }
}
