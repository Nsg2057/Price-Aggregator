package edu.nyu.nsg2057.webscraper.helper;

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
}
