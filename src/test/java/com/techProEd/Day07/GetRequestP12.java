package com.techProEd.Day07;

import com.techProEd.TestBase.JsonPlaceHolderTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequestP12 extends JsonPlaceHolderTestBase {
    /* https://jsonplaceholder.typicode.com/todos/2
    url'ine istek gonderildiginde, donen RESPONSE'un
    => status code'unun 200
    donen BODY'de
    => "completed": degerinin "false"
    => "title": degerinin "quis ut nam facilis et officia qui"
    => "userId" sinin 1 ve
      HEADER degerlerinden
    => "Via" degerinin "1.1 vegur" ve
    => "Server" degerinin "cloudflare" oldugunu test edin.    */

    @Test
    public void test12(){

        /****** bundan sonra EXPECTED DATA'larimizi TEST DATA PACKAGE'larinda tutacagiz*****/

         //****     Expected Data demek Test DATA****

        /* Spec olusturacagiz
         * Expected data olusturecagiz (Test data olusturacagiz)
        1.asama= Key Value seklinde oldugu icin MAP seklinde olusturmaya karar verdik.
        2.asama= Map'in data type ne olacak? String var,int var,boolean var response'le donen body;de hepsi var.
        Hepsini icine OBJECT olmasina karar verdik. Map'in data type Object olmali hepsini icine almasi icin.

        3. HashMap<String,Object> expectedData=new HashMap<String,Object>();
           expectedData.put("statuscode",200);    *  HasMap'e degerleri put() ile yazariz
           expectedData.put("Via",1.1);
          ve icine bize verilen Testten 1 Key degeri ("statuscode") 1 Value degeri (200)  mesela yazariz.

     * HashMap elemanlari RASTGELE dondurur,sirlamanin onemi yoktur,testte verilen datalari karisik da yazabiliriz!!!

         * Request Gonderecegiz
         * Expected datayi
         *  1 - Matcher class ile Assert edecegiz
         *  2 - Json Path ile Assert edecegiz   (JsonPath  ile Sadece BODY'den gelenleri assert edebiliriz )     */

        //*** header ve status code ASSERT etmek icin RESPONSE kullanmak zorundayiz!!!!!!

        //https://jsonplaceholder.typicode.com/todos/2
        //1.ADIM:  Spec olusturacagiz
        spec01.pathParams("parametre1","todos","parametre2",2);

      //2.ADIM:
       /* * Expected data olusturecagiz (Test data olusturacagiz)
        1.asama= Key Value seklinde oldugu icin MAP seklinde olusturmaya karar verdik.
        2.asama= Map'in data type ne olacak? String var,int var,boolean var response'le donen body;de hepsi var.
        Hepsini icine OBJECT olmasina karar verdik. Map'in data type Object olmali hepsini icine almasi icin. */

        HashMap<String,Object> expectedData=new HashMap<String,Object>();
        expectedData.put("statusCode",200);   //HashMap'e deger yazarken put() methodunu kullaniriz.
        expectedData.put("via","1.1 vegur");
        expectedData.put("Server","cloudflare");
        expectedData.put("userId",1);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("completed",false);

        System.out.println(expectedData); //Map'leri yazdirirken herhangi bir method kullanmiyoruz.
        //{Server=cloudflare, completed=false, title=quis ut nam facilis et officia qui, userId=1, statusCode=200, via=1.1 vegur}

        //3.ADIM. REQUEST GONDERME
        Response response=given().
                accept("application/json").
                spec(spec01).
                when().
                get("/{parametre1}/{parametre2}"); //Parametre degerlerini alabilmek icin { } curly brace icine aliriz.

        response.prettyPrint();
        /*{  "userId": 1,
             "id": 2,
             "title": "quis ut nam facilis et officia qui",
             "completed": false                  }  */

    //4.ADIM. ASSERTION yapma.
        // 1.Assertion yondemi --- MATCHERS class ile Assertion yaptik.
        response.then().assertThat().statusCode((int) expectedData.get("statusCode")).  //Yukarida olusturdugumuz Map'ten status kodu cagirdik,hata verince TYPE CASTING yapariz.
        headers("via",equalTo(expectedData.get("via")),
                "server",equalTo(expectedData.get("Server"))).
        body("userId",equalTo(expectedData.get("userId")),
                "title",equalTo(expectedData.get("title")),
                "completed",equalTo(expectedData.get("completed")));

           //2.Assertion yondemi JsonPath ile Assertion (JsonPath Assertopn'da CASTING gerekmiyor)
        JsonPath jsonPath=response.jsonPath();
        Assert.assertEquals(expectedData.get("statusCode"),response.statusCode()); //1.kisma expected data,2.kisma Actual data yazilir.
        Assert.assertEquals(expectedData.get("via"),response.getHeader("via"));
        Assert.assertEquals(expectedData.get("Server"),response.getHeader("Server"));
        Assert.assertEquals(expectedData.get("userId"),jsonPath.getInt("userId"));
        Assert.assertEquals(expectedData.get("title"),jsonPath.getString("title"));
        Assert.assertEquals(expectedData.get("completed"),jsonPath.getBoolean("completed"));

/* ---- JSON PATH icine asagidaki gibi SADECE BODY'den donen degerler girer!!!!

           /*{  "userId": 1,
             "id": 2,
             "title": "quis ut nam facilis et officia qui",
             "completed": false                  }  */

        //3.Assertion yondemi DE-SERIALIZATION.




    }
}
