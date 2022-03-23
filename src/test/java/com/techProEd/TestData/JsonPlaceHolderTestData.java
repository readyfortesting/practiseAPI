package com.techProEd.TestData;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {

    public Map<String, Object> setUpTestData() {


        HashMap<String, Object> expectedData = new HashMap<String, Object>();
        expectedData.put("statusCode", 200);   //HashMap'e deger yazarken put() methodunu kullaniriz.
        expectedData.put("via", "1.1 vegur");
        expectedData.put("Server", "cloudflare");
        expectedData.put("userId", 1);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("completed", false);

/* Burda bir method olusturacagiz bu method bize MAP dondurur
onun icin public Map<String,Object> setUpTestData() dedik

}
 */
 return expectedData;
    }
}