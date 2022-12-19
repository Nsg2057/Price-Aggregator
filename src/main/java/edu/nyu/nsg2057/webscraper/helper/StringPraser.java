package edu.nyu.nsg2057.webscraper.helper;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class StringPraser {
    private Double price;

    public StringPraser() {

    }

    public StringPraser(String line) {
        this.price = getDoublePrice(retrivePrice(line));
    }

    public static String encoder(String text) {
        return URLEncoder.encode(
                text,
                StandardCharsets.UTF_8
        );
    }

    public static double getMatchPercentage(String str1, String str2) {
        // Create a hash map of the words in str1
        if (str2 == null) return 0.00;
        System.out.println(str1 + " === " + str2);
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : str1.split("\\s+")) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        // Count the number of matching words in str2
        int matchCount = 0;
        for (String word : str2.split("\\s+")) {
            if (map.containsKey(word)) {
                matchCount++;
                map.put(word, map.get(word) - 1);
                if (map.get(word) == 0) {
                    map.remove(word);
                }
            }
        }

        // Calculate the match percentage
        int totalWords = str1.split("\\s+").length + str2.split("\\s+").length;
        double matchPercentage = (double) matchCount / totalWords * 100;
        System.out.println(matchPercentage);
        return matchPercentage;
    }

    public String retrivePrice(String line) {
        return line.substring(line.indexOf("$"), line.indexOf(".", line.indexOf("$")) + 3);
    }

    public Double getDoublePrice(String line) {
        line = line.replaceAll("\\$", "");
        line = line.replaceAll(",", "");
        return Double.valueOf(line);
    }

    public Double getPrice() {
        return price;
    }
}
