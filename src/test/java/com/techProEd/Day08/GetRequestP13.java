package com.techProEd.Day08;

import com.techProEd.TestBase.HerOkuAppTestBase;
import com.techProEd.TestData.HerOkuAppTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequestP13 extends HerOkuAppTestBase {
    /*  https://restful-booker.herokuapp.com/booking/2  url'ine bir istek gonderildiginde
    donen response body;nin
{
     {
    "firstname": "Sally",
    "lastname": "Wilson",
    "totalprice": 849,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2015-08-04",
        "checkout": "2017-01-02"
    }
}
}    oldugunu test edin.  */

/* Nested Map olusturacagiz bookingdates ile,daha sonra baska bir Map olusturup icine atacagiz
yani Nested Map olusturmus olacagiz.

1- Test data classi  (HerOkuAppTestData) olusturacagiz.

2- HerOkuAppTestData classinda method olustururuz.
         public void setUpTestData(){

3-               (ONCE icteki Map'i olustururuz,yani bookingdates'ten baslariz.)
        HashMap<String,Object> bookingDates=new HashMap<String, Object>();
4-              bookingDates.put("checkin","2016-03-18");
                bookingDates.put("checkout","2021-12-26");

5-            (SONRA distaki Map'i olustururuz)
       HashMap<String,Object> expectedData=new HashMap<String, Object>();
               expectedData.put("firstname","Jim");
               expectedData.put( "lastname","Ericsson");
               expectedData.put("totalprice",497);
               expectedData.put("depositpaid",false);
               expectedData.put("bookingdates",bookingDates);

6-   return expectedData;
7-   public void setUpTestData(){  void sileriz ve yerine
   public HashMap<String, Object> setUpTestData() { YAPARIZ.

8-   public class GetRequestP13 classina geldik

          1- @Test public void test(){} olusturduk.

          2- extends HerOkuTestBase yapariz.

          3- spec olustur. (daha once vardi spec02)

          4- URL olustur
             spec02.pathParams("parametre1","booking","parametre2",1);

          5- expected data olustur. (HerOkuTestData clasinda olusturduk setUpTestData isminde,onu buraya cagirmamiz gerek)

          6- public class HerOkuAppTestData classindaki setUpTestData methoduna ulasabilmek icin bu classta OBJE uretmemiz gerekiyor.
               HerOkuAppTestData expectedObje=new HerOkuAppTestData();
               HashMap<String,Object> expectedDataMap=expectedObje.setUpTestData();

           7- Olusturdugum Map'i bu class'a cagirabilmisiyim onun icin yazdiririm.
                System.out.println(expectedDataMap);
          YAZDIRDI =>>>   expected Data Map{firstname=Jim, bookingdates={checkin=2016-03-18, checkout=2021-12-26}, totalprice=497, depositpaid=false, lastname=Ericsson}


9-            REQUEST GONDERILIR ve Yazdirilir prettyPrint ile.
       -  Response response=given().accept("application/json").spec(spec02).when().get("/{parametre1}/{parametre2}");
       -  response.prettyPrint();

10-              DE-SERIALIZATION yapmak icin once Map olustururuz
       -  HashMap<String,Object> actualDataMap=response.as(HashMap.class);
11-             Olusturdugumuz actualDataMap yazdirilir.
      - System.out.println("actual data Map : "+actualDataMap);

12-      ASSERTION  with DE-SERIALIZATION
             Assert.assertEquals(expectedDataMap.get("firstname"),actualDataMap.get("firstname"));
             Assert.assertEquals(expectedDataMap.get("lastname"),actualDataMap.get("lastname"));
             Assert.assertEquals(expectedDataMap.get("totalprice"),actualDataMap.get("totalprice"));
             Assert.assertEquals(expectedDataMap.get("depositpaid"),actualDataMap.get("depositpaid"));

           ------TYPE CASTING ((Map) yapiyoruz, get() mrethodunu 2.defa kullanabilmek icin TYPE CASTING ((Map) yapariz.--------
      Assert.assertEquals( ((Map)expectedDataMap.get("bookingdates")).get("checkin"),((Map)actualDataMap.get("bookingdates")).get("checkin"));
      Assert.assertEquals(((Map) expectedDataMap.get("bookingdates")).get("checkout"),((Map)actualDataMap.get("checkout")));


     }

 */

    @Test
    public void test(){
        spec02.pathParams("parametre1","booking","parametre2",2);

        HerOkuAppTestData expectedObje=new HerOkuAppTestData();
        HashMap<String,Object> expectedDataMap=expectedObje.setUpTestData();
        System.out.println("expected Data Map"+expectedDataMap);


      Response response=given().  //Gonderdigim Response'u requestimizin icine almis olduk!!!
              accept("application/json").
              spec(spec02).
              when().
              get("/{parametre1}/{parametre2}");
      response.prettyPrint();

         // 1.yol De-Serialization ile Assertion
           HashMap<String,Object> actualDataMap=response.as(HashMap.class);
          System.out.println("actual data Map : "+actualDataMap);


      // Sadece body Assert etmemizi istiyor Task, status code istememis.
        Assert.assertEquals(expectedDataMap.get("firstname"),actualDataMap.get("firstname"));
        Assert.assertEquals(expectedDataMap.get("lastname"),actualDataMap.get("lastname"));
        Assert.assertEquals(expectedDataMap.get("totalprice"),actualDataMap.get("totalprice"));
        Assert.assertEquals(expectedDataMap.get("depositpaid"),actualDataMap.get("depositpaid"));

        Assert.assertEquals( ((Map)expectedDataMap.get("bookingdates")).get("checkin"),((Map)actualDataMap.get("bookingdates")).get("checkin"));
        Assert.assertEquals(((Map) expectedDataMap.get("bookingdates")).get("checkout"),((Map)actualDataMap.get("checkout")));


        // 2.yol JsonPath ile Assertion
        JsonPath jsonPath=response.jsonPath();

        Assert.assertEquals(expectedDataMap.get("firstname"),jsonPath.getString("firstname"));
        Assert.assertEquals(expectedDataMap.get("lastname"),jsonPath.getString("lastname"));
        Assert.assertEquals(expectedDataMap.get("totalprice"),jsonPath.getInt("totalprice"));
        Assert.assertEquals(expectedDataMap.get("depositpaid"),jsonPath.getBoolean("depositpaid"));

        Assert.assertEquals(((Map) expectedDataMap.get("bookingdates")).get("checkin"),
                jsonPath.getString("bookingdates.checkin"));

        Assert.assertEquals(((Map) expectedDataMap.get("bookingdates")).get("checkout"),
                jsonPath.getString("checkout"));






    }




}