package com.techProEd.Day12;

import com.techProEd.TestBase.DummyTestBase;
import com.techProEd.TestData.DummyTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PostRequest01 extends DummyTestBase {
     /*
    http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak
{
 "name":"Burak Akyuz",
 "salary":"35000,
 "age":"39",
  }
gönderildiğinde,Status kodun 200 olduğunu ve dönen response body nin ,
{
 "status": "success",
 "data": {
 “id”:…
 },
 "message": "Successfully! Record has been added."
 }
olduğunu test edin
     */

    @Test
    public void test(){
        //1- Spec olusturulur path paramla birlikte.
         spec03.pathParam("parametre1","create");

         //DummyTestData classindaki Request body ve Expected datayi cagirmak icin ONCE OBJE uretmemiz gerek.
        DummyTestData obje=new DummyTestData();
           //Artik Olusturdugumuz obje uzerinden setUpRequestBody'e ulasabilirim
          // setUpRequestBody() bana MAP dondurecegi icin bunu bir MAP'e atariz.

    HashMap<String,String> requestBodyMap= obje.setUpRequestBody(); //REQUEST gonderecegim.      Olusturdugum requestBodyMap ile de bunu requestBodyMap'e atamis oldum!

        // Yukarida olusturdugumuz obje uzerinden expectedData'yi cagiririz.
    HashMap<String,Object> expectedDataMap=obje.setUpExpectedData();// RESPONSE'dan donecek olan .  bu metodda HashMap dondurur,HashMap'in datatype'da ve String,Obje olarak belirlemistik.


        // POST ile parametreyi gonderirken Response'un icinde arti olarak bir de BODY kullanacagiz.
        Response response=given().
                 accept("application/json")
                .spec(spec03)
                .auth().basic("admin","password123")  //degisiklik yapabilmek icin Authentication parola ve sifre gondermeliyiz!!!!!
                .body(requestBodyMap) //olusturdugum requestBodyMap'i de POST yaparken body icinde gondeririz.
                .when()
                .post("/{parametre1}");
        response.prettyPrint();

        //dESERIALIZATION
        HashMap<String,Object> actualDataMap=response.as(HashMap.class);
        Assert.assertEquals(expectedDataMap.get("statusCode"),response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("status"),actualDataMap.get("status"));
        Assert.assertEquals(expectedDataMap.get("message"),actualDataMap.get("message"));

        //JsonPath ile
        JsonPath json=response.jsonPath();
        Assert.assertEquals(expectedDataMap.get("status"),json.getString("status"));
        Assert.assertEquals(expectedDataMap.get("message"),json.getString("message"));



    }

}
