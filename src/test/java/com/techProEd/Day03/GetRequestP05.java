package com.techProEd.Day03;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequestP05 {
    /*  Task04 > https://restful-booker.herokuapp.com/booking/5
    url'ine accept type'i "application/json" olan GET requesti yolladigimda
    gelen response'un
    ->> status code'unun 200
    =>> Content type'in "application/json"
    =>> "firstname": "Eric"
    =>> "lastname": "Wilson",
    =>> "totalprice": 130,
    =>> "depositpaid": false,
    =>> "bookingdates": {
        "checkin": "2016-04-22",
        "checkout": "2019-08-28"  oldugunu test edin.      */

    @Test
    public void test05(){
        //Step 1 : URL olustur
        String url="https://restful-booker.herokuapp.com/booking/5";

        //Step 2 => Expected data olustur

        //Step 3 => Request olustur
   Response response=given().accept("application/json").when().get(url);
     response.prettyPrint();

        //Step 4 => Actual data olustur

        //Step 5 => Verifying.
       response.then()
               .assertThat()
               .statusCode(200)
               .contentType(ContentType.JSON)
               .body("firstname",equalTo("Eric"),
                       "lastname",equalTo("Wilson"),
                       "totalprice",equalTo(130),
                       "depositpaid",equalTo(false),
                       "bookingdates.checkin",equalTo("2016-04-22"),
                       "bookingdates.checkout",equalTo("2019-08-28"));
    }
}

