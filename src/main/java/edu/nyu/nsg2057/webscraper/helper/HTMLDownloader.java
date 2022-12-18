package edu.nyu.nsg2057.webscraper.helper;

import edu.nyu.nsg2057.webscraper.constant.URLconstant;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.util.Optional;
import java.util.zip.GZIPInputStream;

public class HTMLDownloader {

    public String getHTML(String URL) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            URLConnection conn = new HTTPCaller().getConnection(URL);
            conn.setRequestProperty("User-Agent", URLconstant.HEADER_ORIGIN);
            conn.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
            conn.setRequestProperty("Accept", "*/*");
//            conn.setConnectTimeout(5000);
//            conn.setReadTimeout(50000);
            conn.connect();
            InputStream inStream;
            inStream = conn.getInputStream();
            Optional<String> encoding = Optional.ofNullable(conn.getContentEncoding());
            if (encoding.isPresent())
                if (encoding.get().toLowerCase().contains("zip")) inStream = new GZIPInputStream(inStream);
            String inputLine;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(inStream))) {
                while ((inputLine = in.readLine()) != null) stringBuilder.append(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public Document getHTMLfromJSP(String URL) {
        try {
            return Jsoup.connect(URL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Document.createShell("");
    }
}
