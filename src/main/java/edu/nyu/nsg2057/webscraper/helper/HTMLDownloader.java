package edu.nyu.nsg2057.webscraper.helper;

import edu.nyu.nsg2057.webscraper.constant.URLconstant;
import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.util.Optional;
import java.util.zip.GZIPInputStream;

public class HTMLDownloader {

    public String getHTML(String URL) {
        System.out.println(9);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            if (URL.contains(URLconstant.TRAGET)) throw new NullPointerException();
            System.out.println(URL);
            URLConnection conn = new HTTPCaller().getConnection(URL);
            System.out.println(84);
            conn.setRequestProperty("User-Agent", URLconstant.HEADER_ORIGIN);
            conn.setRequestProperty("Accept", "gzip, deflate, br");
            conn.setRequestProperty("Accept", "*/*");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
//            conn.setRequestProperty("Connection","keep-alive");
            conn.connect();
            System.out.println(85);
            InputStream inStream;
            try {
                inStream = conn.getInputStream();
            } catch (Exception e) {
                throw new NullPointerException();
            }
            System.out.println(88);
            Optional<String> encoding = Optional.ofNullable(conn.getContentEncoding());
            System.out.println(86);
            if (encoding.isPresent())
                if (encoding.get().toLowerCase().contains("zip")) inStream = new GZIPInputStream(inStream);
            String inputLine;
            System.out.println(87);
            try (BufferedReader in = new BufferedReader(new InputStreamReader(inStream))) {
                System.out.println(7);
                while ((inputLine = in.readLine()) != null) stringBuilder.append(inputLine);
            }
        } catch (IOException e) {
//            throw new RuntimeException(e);
            System.out.println("here " + e.getMessage());
        } catch (NullPointerException e) {
            try {

                stringBuilder.append(Jsoup.connect(URL).get().html());
            } catch (IOException ex) {
                System.out.println("and here");
                throw new RuntimeException(ex);
            }
        }
        System.out.println(6);
        return stringBuilder.toString();
    }
}
