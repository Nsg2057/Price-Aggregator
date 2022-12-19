package edu.nyu.nsg2057.webscraper.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class HTTPCaller {
    public static String restGetCall(String uri) throws IOException {
        URL url = new URL(uri);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        String cookieValue = "_abck=4AD4DB09F439B128C5C78CC4EB126485~-1~YAAQ25w6FwVmlBWFAQAAm1MZIwlqg5kMw3lyAihXOJ0xnGy+hPsjEDnF7o1DL59wfEB/qx8H1kzFZlxMZNKly5Pzb4vAxjAUyyJ9i3UXZNyTQKSJ7C/2YjNwd1wA19osaOzKEzvuR8zZES6RQniOfhyfP8nkMUfMDMRd5N/PKrRHxpZVLnviIkSXuDYW1tdXdGXK95a8sfEua9ljL8iVwwnZfx4rhNAbdiOEvQOVjmSQLyrH7sy1gDHG7wZfS/k+jAaPmiL6TjZmqj9F/KLpRXYLYUgn3CtQlcB/LS2aOfHERanIM5mWjAsuc87876JUs5voLibMNC0MAUhwHWiIEEsa6KCO4P87vrAJves=~-1~-1~-1; ak_bmsc=A844AE7AA2198F1207238290A682F44D~000000000000000000000000000000~YAAQ9Po7F65wNhuFAQAAflLYIhJk1h5nlkL3Ve+g0yAfeJMRZg3lx3jOB2DgqZptgIXajVkVvfP4N7CdxSZG5Vs3qgQ5suI+21pz1CoeGl9n3sCN9xbCeBjM9xfCvhgiYHrgsbx6oODZfQq4mXac5cDqJKB1Gd9EOACXZFWeUwScUx5rZcJszZN/SNPAJnMbwtDWhdIm5q03Sx4RvR6LD5V+rR8qgktVRqK7x9AtdDvKKFk0DXwfIOHO/nmW/T6O/0BPNh1824BCtjWBNEOHRKmjAtwujHHn1jx2YJaaH704Gs817f+v5EGjcluxWRtG4MLleuETqx1dfx2e8QSPnpm2TNmeqAIzuQGQAos46Fdr4OEqxttVeObVpzXaU+xSf49mSvBrjtWb9P6t3T/5kHk=; bm_sv=7F76808DA09E2195DF070C25A1BDB965~YAAQ25w6FwZmlBWFAQAAm1MZIxIYeGDjWApV6sInVNQyHmgOikxDMWvWw9nheHH5zfXfSI1m+kZuzErN2ZQIt6wnPEwtN/QgufTVJIlcGqyZW5aU6a8ZLWZDSBTR2g1U2vF2l7ku8/Jxb8W/w/NcHWQWxH3XAEjB33ij6pbJ1UXP6Wm1oE83VmI8ta3PhNoh0HizAaKPldPswRQbp/ApaEOARhiLT8QvKK7N3suxPT4oe3LKtxfxmt0pLXPEwxYxDQ==~1; bm_sz=679A9BAE15F8446F08893D1901AF69B0~YAAQzJw6Fzv9/hiFAQAAzHa/IhJ0na1tJQ8sLn/3oEUzzekZrNzS+jz5JkTnYoIb291mjeYNLHelTcoqK+8IDYe1GjLFSJhnHdfwn4ngfjvgs/JYG8CYAuzlhm0U0iRNSV4ikyAAQtc4DJm0snQvgPzqRChc1z8zhGrJ83Dk4XeDG9l8F50ryGJStFyzC2KrJaCo64s+Io6aNs+hq0AWj6GvVo51dQeW/Uxx/ySIZw3n8OgJtE8/2dEz+7zViV2JKp8BfF+F8jAYwysSpairNVyfKTrTQx+ODG0sYCFGjw1nTr26C8nOqAr9xt5v6J98G57lPYjGWoH4lzU=~3160113~4601651; C_LOC=NY; CriteoSessionUserId=fc97f5ca6a2a4713b7687e10cda64637; JSESSIONID=0000EuxJavjrAt-PSEeuyhjrkMl:1g39pinon; WAREHOUSEDELIVERY_WHS=%7B%22distributionCenters%22%3A%5B%221260-3pl%22%2C%221321-wm%22%2C%221474-3pl%22%2C%22283-wm%22%2C%22561-wm%22%2C%22725-wm%22%2C%22731-wm%22%2C%22757-wm%22%2C%22758-wm%22%2C%22759-wm%22%2C%22794-wm%22%2C%22847_0-cor%22%2C%22847_0-cwt%22%2C%22847_0-edi%22%2C%22847_0-ehs%22%2C%22847_0-membership%22%2C%22847_0-mpt%22%2C%22847_0-spc%22%2C%22847_0-wm%22%2C%22847_1-edi%22%2C%22847_d-fis%22%2C%22847_NA-cor%22%2C%22847_NA-pharmacy%22%2C%22847_NA-wm%22%2C%22951-wm%22%2C%22952-wm%22%2C%229847-wcs%22%5D%2C%22groceryCenters%22%3A%5B%22729-bd%22%5D%7D; WC_ACTIVEPOINTER=-1%2C10301; WC_AUTHENTICATION_-1002=-1002%2CtMlGzWl0cXkJgxNmSw2bN88J4PwPTaLQto2HrkgAI8Q%3D; WC_GENERIC_ACTIVITYDATA=[28991221701%3Atrue%3Afalse%3A0%3AtvFegP95eGDXx3u1dt1a%2BE5LlxHfk%2BPpuBW38NwJ5%2FI%3D][com.ibm.commerce.context.entitlement.EntitlementContext|120587%253B120572%253B120611%253B120563%253B120565%253B120570%253B120571%253B120567%253B120568%253B120569%253B120566%253B120757%253B120754%253B120752%253B120758%253B120753%253B120756%253B120755%253B120751%253B120765%253B120762%253B120763%253B120761%253B120573%253B120574%253B4000000000000101005%253B60515%253B4000000000000001002%26null%26null%26-2000%26null%26null%26null][com.ibm.commerce.context.audit.AuditContext|1671312173632-282855][com.ibm.commerce.context.globalization.GlobalizationContext|-1%26USD%26-1%26USD][com.ibm.commerce.store.facade.server.context.StoreGeoCodeContext|null%26null%26null%26null%26null%26null][com.ibm.commerce.context.experiment.ExperimentContext|null][com.ibm.commerce.context.ExternalCartContext|null][com.costco.commerce.member.context.ProfileApiTokenCustomContext|null][com.ibm.commerce.giftcenter.context.GiftCenterContext|null%26null%26null][com.costco.pharmacy.commerce.common.context.PharmacyCustomContext|null%26null%26null%26null%26null%26null][com.ibm.commerce.catalog.businesscontext.CatalogContext|10701%26null%26false%26false%26false][CTXSETNAME|Store][com.ibm.commerce.context.base.BaseContext|10301%26-1002%26-1002%26-1]; WC_PERSISTENT=jtWch4nYQfVfYdwc%2Fepe4Je9WInP9pSe88jRyeYDwmU%3D%3B2022-12-17+13%3A22%3A53.884_1671312173632-282855_10301_-1002%2C-1%2CUSD%2Cp9Jl4qhhxRC3i%2Bp7AKx5J4oAB3jklmRSWtQUGYzhmdzH%2FcdyupXh7u3zIl7JFyeDFLvflS9tyxtWnOCmgStsTw%3D%3D_10301; WC_SESSION_ESTABLISHED=true; WC_USERACTIVITY_-1002=-1002%2C10301%2Cnull%2Cnull%2Cnull%2Cnull%2Cnull%2Cnull%2Cnull%2Cnull%2C24264840%2C8aSG4ggGSnF%2B4EfxEhUvdO3%2FCo98rSkw9zpaewed0gXLvpp96lv5YsvRFVehhAiYTZhEL%2Fq7Z9c%2BqY00RcmOf7PjckOebJ1U6gpTqBtW2TrIp7Lzu65fLe7%2Fs3ojUAwN88nYIJEMiof2%2FwiuR5dK7oa55pWjpwQrtBeBAs5pbn4CKoxCdyr4tQoxbRsEE4rQdO9SrrFHkzoc%2Fa4ZIWVH3qukQAGsqzI3y%2BZ%2B8zr1FRCfrF5UbunUUJiZLP%2FhRbS5; akaas_AS01=2147483647~rv=31~id=ea137df0ad50b8e09b2439dbfa7d57e4; akavpau_zezxapz5yf=1671331440~id=3bcf64fd88323c637533a62d344048db; client-zip-short=11201; invCheckPostalCode=11201; invCheckStateCode=NY";
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Cookie", cookieValue);
        int responseCode = connection.getResponseCode();
        StringBuilder sb = new StringBuilder();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
        }
        return sb.toString();
    }

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
