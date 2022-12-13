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
            if (URL.contains(URLconstant.TARGET)) throw new NullPointerException();
            URLConnection conn = new HTTPCaller().getConnection(URL);
            conn.setRequestProperty("User-Agent", URLconstant.HEADER_ORIGIN);
            conn.setRequestProperty("Accept", "gzip, deflate, br");
            conn.setRequestProperty("Accept", "*/*");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.connect();
            InputStream inStream;
            try {
                inStream = conn.getInputStream();
            } catch (Exception e) {
                throw new NullPointerException();
            }
            Optional<String> encoding = Optional.ofNullable(conn.getContentEncoding());
            if (encoding.isPresent())
                if (encoding.get().toLowerCase().contains("zip")) inStream = new GZIPInputStream(inStream);
            String inputLine;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(inStream))) {
                while ((inputLine = in.readLine()) != null) stringBuilder.append(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            try {

                stringBuilder.append(Jsoup.connect(URL).get().html());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        return stringBuilder.toString();
    }
}
