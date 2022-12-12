package edu.nyu.nsg2057.webscraper.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

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

//    String getResponse(URLConnection uc){
//        StringBuilder builder = null;
//        try {
//
//
//            uc.setRequestProperty("Content-Type", "application/json");
//            uc.setRequestProperty("Accept", "application/json");
//
//
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(uc.getInputStream()));
//             builder = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                builder.append(line);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return builder.toString();
//    }
    
}
