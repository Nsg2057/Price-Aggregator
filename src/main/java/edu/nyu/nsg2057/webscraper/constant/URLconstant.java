package edu.nyu.nsg2057.webscraper.constant;

public class URLconstant {
    public static final String AMAZON = "https://www.amazon.com";
    public static final String AMAZON_LIST = AMAZON +"/s?k=";

    public static final String HEADER_ORIGIN = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36";
    public static final String WALMART = "https://www.walmart.com";
    public static final String WALMART_SEARCH = WALMART + "/search?q=";
    public static final String PRICE_REGEX = "^(?:[$](\\s{0,1})|)[+-]?(\\d{1,3},?(\\d{3},?)*\\d{3}(\\.\\d{0,2})?|\\d{1,3}(\\.\\d{0,2})?|\\.\\d{1,2}?)$";
    public static final String TRAGET = "https://www.target.com";
    public static final String TRAGET_SEARCH = TRAGET + "/s?searchTerm=";
    public static final String BESTBUY = "https://www.bestbuy.com";
    public static final String BESTBUY_SEARCH = BESTBUY + "/site/searchpage.jsp?st=";

    //    public static final String WALLMART_SEARCH =WALLMART + "/orchestra/snb/graphql/Search/6cd18892fe783d20ed381043aca66e315d1b75cdcfb55fa36eb1de06405464d2/search?variables=";
//    public static final String WALLMART_GQL =  "{\"id\":\"\",\"dealsId\":\"\",\"query\":\"<modelID>\",\"page\":1,\"prg\":\"desktop\",\"catId\":\"\",\"facet\":\"\",\"sort\":\"best_match\",\"rawFacet\":\"\",\"seoPath\":\"\",\"ps\":40,\"ptss\":\"\",\"trsp\":\"\",\"beShelfId\":\"\",\"recall_set\":\"\",\"module_search\":\"\",\"min_price\":\"\",\"max_price\":\"\",\"storeSlotBooked\":\"\",\"additionalQueryParams\":{\"hidden_facet\":null,\"translation\":null,\"isMoreOptionsTileEnabled\":true},\"searchArgs\":{\"query\":\"<modelID>\",\"cat_id\":\"\",\"prg\":\"desktop\",\"facet\":\"\"},\"fitmentFieldParams\":{\"powerSportEnabled\":true},\"fitmentSearchParams\":{\"id\":\"\",\"dealsId\":\"\",\"query\":\"<modelID>\",\"page\":1,\"prg\":\"desktop\",\"catId\":\"\",\"facet\":\"\",\"sort\":\"best_match\",\"rawFacet\":\"\",\"seoPath\":\"\",\"ps\":40,\"ptss\":\"\",\"trsp\":\"\",\"beShelfId\":\"\",\"recall_set\":\"\",\"module_search\":\"\",\"min_price\":\"\",\"max_price\":\"\",\"storeSlotBooked\":\"\",\"additionalQueryParams\":{\"hidden_facet\":null,\"translation\":null,\"isMoreOptionsTileEnabled\":true},\"searchArgs\":{\"query\":\"<modelID>\",\"cat_id\":\"\",\"prg\":\"desktop\",\"facet\":\"\"},\"cat_id\":\"\",\"_be_shelf_id\":\"\"},\"enableFashionTopNav\":false,\"enablePortableFacets\":true,\"enableFacetCount\":true,\"fetchMarquee\":true,\"fetchSkyline\":true,\"fetchGallery\":false,\"fetchSbaTop\":true,\"tenant\":\"WM_GLASS\",\"enableFlattenedFitment\":true,\"pageType\":\"SearchPage\"}";

}
