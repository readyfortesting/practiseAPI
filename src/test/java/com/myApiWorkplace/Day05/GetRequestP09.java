package com.myApiWorkplace.Day05;

import com.myApiWorkplace.TestBase.DummyTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequestP09 extends DummyTestBase {
    /* http://dummy.restapiexample.com/api/v1/employees  url'sinde bulunan

    1- Butun calisanlarin isimlerini consola yazdiralim
    2- 3.calisan kisinin ismini consola yazdiralim.
    3- Ilk 5 calisanin adini consola yazdiralim.
    4- En son calisanin adini consola yazdiralim.          */

    @Test
    public void test9(){
        //*** v1'e kadar parametre degismedigi icin sadece employee icin 1 parametre olusturduk*****

     spec03.pathParam("parametre1","employees");

        Response response=given().accept("application/json")
                .spec(spec03)
                .when()
                .get("/{parametre1}");

     //   response.prettyPrint();

        //1- Butun calisanlarin isimlerini consola yazdiralim
        JsonPath jsonPath=response.jsonPath();
        System.out.println(jsonPath.getList("data.employee_name"));
      //[Tiger Nixon, Garrett Winters, Ashton Cox, Cedric Kelly, Airi Satou, Brielle Williamson, Herrod Chandler, Rhona Davidson, Colleen Hurst, Sonya Frost, Jena Gaines, Quinn Flynn, Charde Marshall, Haley Kennedy, Tatyana Fitzpatrick, Michael Silva, Paul Byrd, Gloria Little, Bradley Greer, Dai Rios, Jenette Caldwell, Yuri Berry, Caesar Vance, Doris Wilder]


       // 2- 3.calisan kisinin ismini consola yazdiralim.
        System.out.println(jsonPath.getString("data[2].employee_name"));  // Ashton Cox
       // System.out.println(jsonPath.getString("data.employee_name[2]")); => bu sekilde de calisir.


        // 3- Ilk 5 calisanin adini consola yazdiralim.
       System.out.println(jsonPath.getList("data[0,1,2,3,4].employee_name"));
       // System.out.println(jsonPath.getList("data.employee_name[0,1,2,3,4]")); => bu sekilde de calisir.
        //[Tiger Nixon, Garrett Winters, Ashton Cox, Cedric Kelly, Airi Satou]


        //4- En son calisanin adini consola yazdiralim.
        System.out.println(jsonPath.getString("data.employee_name[-1]"));
        //Doris Wilder       -1 en son data'yi verir.

        //DOGRULAMA
        Assert.assertEquals(200,response.getStatusCode());

        //3.calisan kisinin ismini consola yazdiralim.
        Assert.assertEquals("Ashton Cox",jsonPath.getString("data[2].employee_name"));

//4- En son calisanin adini consola yazdiralim.
        Assert.assertEquals("Doris Wilder ",jsonPath.getString("data.employee_name[-1]"));
    }
}
