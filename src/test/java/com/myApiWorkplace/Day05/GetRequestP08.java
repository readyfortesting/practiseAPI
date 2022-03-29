package com.myApiWorkplace.Day05;

import com.myApiWorkplace.TestBase.HerokuAppTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequestP08 extends HerokuAppTestBase {
    /*  https://restful-booker.herokuapp.com/booking/5  url'ine
    accept type'i "application/json" olan GET requesti yolladigimda

    - HTTP Status kodunun 200
    - Content type'inin "application/json" oldugunu
    ve response body'sinin asagidaki gibi oldugunu test edin

    {
     "firstname": "Jim",
    "lastname": "Smith",
    "totalprice": 125,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2020-12-10",
        "checkout": "2021-12-02"
    }
}
       */


    @Test
    public void test08(){

        /********  ------JSON Path methodu ---------- *********
        response.jsonPath(); methodu JsonPath class'indan obje ureterek
        response uzerinden JsonPath class'indaki methodlari kullanmamizi saglar. */

        spec02.pathParams("parametre1","booking","parametre2",5);

        Response response=given().
                accept("application/json").
                spec(spec02).
                when()
                .get("/{parametre1}/{parametre2}");
        response.prettyPrint();


        //Json Path ile Dogrulama
        JsonPath jsonPath=response.jsonPath();



        //Matcher class kullanarak status code ve ContentType assert edebiliriz.
       // response.then().assertThat().statusCode(200).contentType(ContentType.JSON);

        //Matcher class kullanmak istemezsek status code ve ContentType assert icin
        Assert.assertEquals(200,response.getStatusCode());


        //Junit'de ONCE expected SONRA actual data yazilir.
        //Json Path ile Dogrulama
        Assert.assertEquals( "Jim",jsonPath.getString("firstname"));
        Assert.assertEquals("Smith",jsonPath.getString("lastname"));
        Assert.assertEquals(125,jsonPath.getInt("totalprice"));
        Assert.assertEquals(true,jsonPath.getBoolean("depositpaid"));
        Assert.assertEquals("2020-12-10",jsonPath.getString("bookingdates.checkin"));
        Assert.assertEquals("2021-12-02",jsonPath.getString("bookingdates.checkout"));




        /*********   TEST PASSED *******

         "firstname": "Jim",
         "lastname": "Smith",
         "totalprice": 125,
         "depositpaid": true,
         "bookingdates": {
         "checkin": "2020-12-10",
         "checkout": "2021-12-02"

         */
    }
}
