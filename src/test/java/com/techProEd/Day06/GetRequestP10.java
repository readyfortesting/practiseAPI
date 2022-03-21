package com.techProEd.Day06;

import com.techProEd.TestBase.DummyTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequestP10 extends DummyTestBase {
    /* http://dummy.restapiexample.com/api/v1/employees
    url'ine bir istek gonderildiginde
   1 => Status code'un 200,
    Gelen body'de
   2 => 5.calisanin isminin "Airi Satou" oldugunu
   3 => 6.calisanin maasinin "372000" oldugunu,
   4 => Toplam 24 calisan oldugunu,
   5 => "Rhona Davidson"in employee'lerden biri oldugunu
   6 => 21,23,61 yaslarinda employeeler oldugunu test edin.     */

    @Test
    public void test10(){
        //****------ expected data'lari olusturmaya basliyoruz--------******//

        spec03.pathParams("parametre1","employees");

        Response response=given().
                accept("application/json")
                .spec(spec03)
                .when()
                .get("/{parametre1}");
      //  response.prettyPrint();

        JsonPath jsonPath=response.jsonPath();

        // 1 => Status code'un 200 oldugunu Assert edin. StatusCode dogrularken JsonPath KULLANAMIYORUZ!!!
       Assert.assertEquals(200,response.getStatusCode());
      // Assert.assertTrue(response.getStatusCode()==200); // Bu yontemle de status code'u dogrulayabiliriz.


        // 2 => 5.calisanin isminin "Airi Satou" oldugunu dogrulayiniz
        Assert.assertEquals("Airi Satou",jsonPath.getString("data[4].employee_name"));


       // 3 => 6.calisanin maasinin "372000" oldugunu dogrulayiniz
        Assert.assertEquals(372000,jsonPath.getInt("data[5].employee_salary"));

        // 4 => Toplam 24 calisan oldugunu dogrula,
        System.out.println(jsonPath.getList("data.id")); // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24]
        Assert.assertEquals(24,jsonPath.getList("data.id").size());

       // 5 => "Rhona Davidson"in employee'lerden biri oldugunu
        Assert.assertTrue(jsonPath.getList("data.employee_name").contains("Rhona Davidson"));
        //contains() methodu ile 1 kisinin icerip icermedigini dogrulayabiliriz.


        //  6 => 21,23,61 yaslarinda employeeler oldugunu test edin.

        List<Integer> arananYaslar=Arrays.asList(21,23,61);
      //  --- 2.yontem --------
      /*  List<Integer> arananYaslar=new ArrayList<Integer>();
        arananYaslar.add(21);
        arananYaslar.add(23);
        arananYaslar.add(61);

       */

      Assert.assertTrue(jsonPath.getList("data.employee_age").containsAll(arananYaslar));





    }
}
