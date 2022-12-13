package edu.nyu.nsg2057.webscraper.constant;

public class URLconstant {
    public static final String AMAZON = "https://www.amazon.com";
    public static final String AMAZON_LIST = AMAZON + "/s?k=";
    public static final String HEADER_ORIGIN = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36";
    public static final String WALMART = "https://www.walmart.com";
    public static final String WALMART_SEARCH = WALMART + "/search?q=";
    public static final String PRICE_REGEX = "^(?:[$](\\s{0,1})|)[+-]?(\\d{1,3},?(\\d{3},?)*\\d{3}(\\.\\d{0,2})?|\\d{1,3}(\\.\\d{0,2})?|\\.\\d{1,2}?)$";
    public static final String TARGET = "https://www.target.com";
    public static final String TARGET_SEARCH = TARGET + "/s?searchTerm=";
    public static final String BESTBUY = "https://www.bestbuy.com";
    public static final String BESTBUY_SEARCH = BESTBUY + "/site/searchpage.jsp?st=";
}
