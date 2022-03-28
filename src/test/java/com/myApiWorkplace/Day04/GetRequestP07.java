package com.myApiWorkplace.Day04;

import com.myApiWorkplace.TestBase.JsonPlaceHolderTestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequestP07 extends JsonPlaceHolderTestBase {
    /*  https://jsonplaceholder.typicode.com/todos/123  url'ine
    accept type'i "application/json" olan GET requesti yolladigimda
    gelen response'un
    - status kodunun 200
    - content type'inin "application/json"
    - Headers'daki "Server"in "cloudflare"
    - response body'deki "userId" nin 7
    - "title" in "esse et quis iste est earum aut impedit"
    - "completed" bolumunun "false" oldugunu test edin.     */

    /********  ---------- IMPORTANT ------------- ********
     Bir Utilities package olusturalim, icinde her bir baseUrl icin bir TestBase class'i olsun
     hangi baseUrl'i kullanmak istersek onun child'ini olusturup testlerimizi yapalim.   */

@Test
    public void test07(){
    spec01.pathParams("parametre1","todos",
              "parametre2",123);


    Response response=given()
            .accept("application/json")
            .spec(spec01)
            .when()
            .get("/{parametre1}/{parametre2}");

    response.prettyPrint();

    // Dogrulama
    response.then()
            .assertThat()
            .statusCode(200)                                                                      // - status kodunun 200 oldugunu
            .contentType("application/json")                                                     // content type'inin "application/json"
            .header("Server",equalTo("cloudflare"))                                   //  Headers'daki "Server"in "cloudflare" oldugunu
            .body("userId",equalTo(7),                                               // response body'deki "userId" nin 7 oldugunu
                 "title",equalTo("esse et quis iste est earum aut impedit"),   //"title" in "esse et quis iste est earum aut impedit"
                    "completed",equalTo(false));                                      // "completed" bolumunun "false" oldugunu test edin.


/*                  ---Test PASSED --------
       {
                 "userId": 7,
                 "id": 123,
                 "title": "esse et quis iste est earum aut impedit",
                 "completed": false
         }


 */





}









}