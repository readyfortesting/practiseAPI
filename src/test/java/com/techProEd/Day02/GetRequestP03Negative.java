package com.techProEd.Day02;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequestP03Negative {
    /* https://restful-booker.herokuapp.com/booking/1001  url'ine
    -> accept type'i "application/json" olan GET requesti yolladigimda gelen response'un
    => Status Code'un 400 oldugunu
    => Response body'sinin "Not Found" icerdigini
    => Response body'sinin "API" icermedigini test edin.   */

@Test
    public void negativeTest(){

         // Step 1 > URL olustur
    String url="https://restful-booker.herokuapp.com/booking/1001";

       // Step 2- Expected result olusturulur.

    // Step 3- Request gonderilir.
    Response response=given()
             .accept(ContentType.JSON).
             when().
             get(url);
    response.prettyPrint();

     // 4- Actual result olusturulur.

    //   5- Assertion (Dogrulama yapilir).
            // => Status Code'un 400 oldugunu dogrulayin
    response.then().assertThat().statusCode(404);
           //=> Response body'sinin "Not Found" icerdigini dogrulayin
    Assert.assertTrue(response.asString().contains("Not Found"));
          // => Response body'sinin "API" icermedigini dogrulamak icin response.asString() kullanmaliyiz contains ile birlikte kullanabilmek icin.
    Assert.assertFalse(response.asString().contains("API"));


    /*  -----------IMPORTANT    asString()---------------
   ****  asString() methodu ile json formatinda gelen response'u string'e cevirdik !!

   Bir data'nin icerip icermedigini dogrulamak icin
   response.asString() kullanmaliyiz contains ile birlikte kullanabilmek icin.
    contains yazmazsak asString() methodu GELMEZ.!!!
     */


}

}
