package com.techProEd.Day03;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequestP04 {

    /*  Task04 > https://restful-booker.herokuapp.com/booking/7
    url'ine accept type'i "application/json" olan GET requesti yolladigimda
    gelen response'un
    ->> status code'unun 200
    =>> Content type'in "application/json"
    =>> "firstname": "Mary"
    =>> "lastname": "Ericsson",
    =>> "totalprice": 250,
    =>> "depositpaid": false,
    =>> "bookingdates": {
        "checkin": "2020-11-17",
        "checkout": "2020-12-25"  oldugunu test edin.      */

@Test
    public void test04(){
    //Step 1=> URL(Endpoint) olustur
    String url="https://restful-booker.herokuapp.com/booking/7";

    //Step 2 => Expected data olustur

    //Step 3 => Request olustur
    Response response=given().
            accept("application/json").
            when().get(url);
    response.prettyPrint();

    //Step 4 => Actual data olustur

    //Step 5 => Verifying. Dogrula.  API datalarinizi nasil Assert edersiniz?
    //     => Yaygin kullanilan yontemlerden birisi Matcher class'tir.


    // -------------- Bu UZUN sekli, FAZLA KOD var-----------

     /*   response.then().assertThat().
               statusCode(200).
              contentType("application/json")
             .body("firstname", Matchers.equalTo("Mary"))
             .body("lastname",Matchers.equalTo("Ericsson"))
             .body("totalprice",Matchers.equalTo(250))
             .body("depositpaid",Matchers.equalTo(false))
             .body("bookingdates.checkin",Matchers.equalTo("2020-11-17"))
             .body("bookingdates.checkout",Matchers.equalTo("2020-12-25"));    */


     // -------------- Bu DAHA KISA sekli, AZ KOD var-----------//

            response.then()
                    .assertThat()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .body("firstname",equalTo("Susan"),
                            "lastname", equalTo("Jackson"),
                            "totalprice", equalTo(859),
                            "depositpaid", equalTo(false),
                            "bookingdates.checkin",equalTo("2020-12-01"),
                            "bookingdates.checkout",equalTo("2021-02-01"));









/*     ----------TEST PASSED ------------
{
    "firstname": "Mary",
    "lastname": "Ericsson",
    "totalprice": 250,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2020-11-17",
        "checkout": "2020-12-25"
    },
    "additionalneeds": "Breakfast"
}

Process finished with exit code 0

 */

}





}
