package edu.nyu.nsg2057.webscraper.helper;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;


public class HTTPCaller {
    public URLConnection getConnection(String url) {
        System.out.println(8);
        URL myURL;
        URLConnection myURLConnection;
        try {
            myURL = new URL(url.toLowerCase().startsWith("http") ? url : "https://" + url);
            System.out.println(83);
            myURLConnection = myURL.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(82);
        return myURLConnection;
    }

    
}
