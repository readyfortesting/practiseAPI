package com.myApiWorkplace.Day07;

import com.myApiWorkplace.TestBase.DummyTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequestP11 extends DummyTestBase {
     /* http://dummy.restapiexample.com/api/v1/employees
    url'ine bir istek gonderildiginde donen Response'un
    => Status code'un 200,

   1 => 10'dan buyuk tum id'leri ekrana yazdirin ve 10'dan buyuk 14 id oldugunu
   2 => 30'dan kucuk tum yaslari ekrana yazdirin ve
         bu yaslarin icinde en buyuk yasin 23 oldugunu test edin,
   3 => Maasi 350000 den buyuk olan tum employee name'leri ekrana yazdirin ve
         bunlarin icerisinde "Charde Marshall" oldugunu test edin.     */

    @Test
    public void test11(){
        spec03.pathParam("Parametre1","employees");

        Response response=given().
                accept("application/json")
                .spec(spec03)
                .when()
                .get("/{Parametre1}");
       // response.prettyPrint();

        JsonPath jsonPath=response.jsonPath();
        //Json path ile status code dogrulamasi yapamiyoruz cunku status code Headers bolumunde
        // JsonPath sadece body bolumunu dogrulayabiliyor.

        //=> Status code'un 200 oldugunu dogrulayiniz.( Status code'u Response'dan dogruluyoruz)
        Assert.assertEquals(200,response.getStatusCode());

       // 1** => 10'dan buyuk tum id'leri ekrana yazdirin ve 10'dan buyuk 14 id oldugunu
 //For Each yapip tek tek karsilastirmak yerine GROOVY dilini kullanip 1 satirda islemleri halledebiliriz.
        //butun degerler data'nin altinda oldugu icin data. diyerek altindaki degerlere ulasmaya baslariz

        //butun data icinde id'si 10'dan buyuk olan id'leri getir demek!!
      // Ekrana yazdirabilmek icin Jsonpath'dan gelen butun id'leri List'e atariz.

         List<Integer> idList=jsonPath.getList("data.findAll{it.id>10}.id");
        System.out.println(idList); // [11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24]

        // 10'dan buyuk 14 id oldugunu dogrulayin
        Assert.assertEquals(14,idList.size());


        //** 2=> 30'dan kucuk tum yaslari ekrana yazdirin ve bu yaslarin icinde en buyuk yasin 23 oldugunu test edin,

        //List'in icine her zaman wrapper class atariz,burda da int dondurecegi icin Integer kullandik.
        List<Integer> otuzdanKucukYaslar=jsonPath.getList("data.findAll{it.employee_age<30}.employee_age");
        System.out.println(otuzdanKucukYaslar); // [22, 23, 22, 19, 21, 23]

       //   bu yaslarin icinde en buyuk yasin 23 oldugunu test edin,

     //Collections.sort methodunu kullanarak kucukten buyuge siralariz ve en son sayi en buyuk olacagi icin son indexi aliriz.

        Collections.sort(otuzdanKucukYaslar);
       // otuzdanKucukYaslar.get(otuzdanKucukYaslar.size()-1); bunu Assert etmek icin
        //Assert.assertEquals(23,otuzdanKucukYaslar.get(otuzdanKucukYaslar.size()-1)); boyle yapinca HATA verdi
        //bunu DUZELTMEK icin CASTING yapariz. ((Integer) 23, yaparak Wrapper class'a ceviririz.


        Assert.assertEquals((Integer) 23,otuzdanKucukYaslar.get(otuzdanKucukYaslar.size()-1));
       // Assert.assertEquals( 23,(int)otuzdanKucukYaslar.get(otuzdanKucukYaslar.size()-1)); => Bu yontem de Dogru


        //3 => Maasi 350000 den buyuk olan tum employee name'leri ekrana yazdirin ve

   //employee_name istedigi icin List<String> assign etmemiz gerekir!!!!!
         List<String>  yuksekMaasAlanlarListesi= jsonPath.getList("data.findAll{it.employee_salary>350000}.employee_name");
         System.out.println(yuksekMaasAlanlarListesi);
         //[Cedric Kelly, Brielle Williamson, Charde Marshall, Tatyana Fitzpatrick, Paul Byrd, Yuri Berry]

        // bunlarin icerisinde "Charde Marshall" oldugunu test edin.
        Assert.assertTrue(yuksekMaasAlanlarListesi.contains("Charde Marshall"));



    }
}

