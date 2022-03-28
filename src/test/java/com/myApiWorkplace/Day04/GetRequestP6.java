package com.myApiWorkplace.Day04;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequestP6 {
    /* http://dummy.restapiexample.com/api/v1/employees  url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    ==> status kodunun 200
    ==> content type'inin "application/json"
    ==> employees sayisinin 24
    ==> employeelerden birinin "Ashton Cox"
    ==> ve yaslar gelen icinde 21,61,23 degerlerinden birinin oldugunu test edin.

    body("data.id").Matchers.hasSize("value")); key olarak verilen degiskenin sayisinin value'ya esit olup olmadigini kontrol eder.

    body("data.employee_name",Matchers.hasItem("value"));  key olarak verilen degiskenin aldigi degerlerin icinde value var mi diye kontrol eder.

    Value birden fazla ise hasItems kullanip value'lari virgulle yanyana yazilabilir.       */

    @Test
    public void test06(){
        String url="http://dummy.restapiexample.com/api/v1/employees ";

      Response response=given().accept("application/json").when().get(url);
      response.prettyPrint();

      /* ==> status kodunun 200
         ==> content type'inin "application/json"
         ==> employees sayisinin 24
         ==> employeelerden birinin "Ashton Cox"
         ==> ve  gelen yaslar icinde 21,61,23 degerlerinden birinin oldugunu test edin.    */

        response.then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data.id",hasSize(24),                       // employees sayisinin 24
                        "data.employee_name",hasItem("Tiger Nixon"),    //employeelerden birinin "Ashton Cox"
                        "data.employee_age",hasItems(21,61,23));    // gelen yaslar icinde 21,61,23 degerlerinden birinin oldugunu test edin.






    }
}
