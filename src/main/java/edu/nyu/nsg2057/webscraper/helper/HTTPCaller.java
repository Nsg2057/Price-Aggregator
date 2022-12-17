package edu.nyu.nsg2057.webscraper.helper;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;


public class HTTPCaller {
    public URLConnection getConnection(String url) {
        URL myURL;
        URLConnection myURLConnection;
        try {
            myURL = new URL(url.toLowerCase().startsWith("http") ? url : "https://" + url);
            myURLConnection = myURL.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return myURLConnection;
    }


}
