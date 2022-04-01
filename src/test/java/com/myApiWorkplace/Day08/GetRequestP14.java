package com.myApiWorkplace.Day08;

import com.myApiWorkplace.TestBase.DummyTestBase;
import com.myApiWorkplace.TestData.DummyTestData;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequestP14 extends DummyTestBase {
    /* http://dummy.restapiexample.com/api/v1/employees url'ine bir istek gonderildiginde
    - status kodun 200 oldugunu,
    - 5.calisan isminin "Airi Satou"
    - Sondan 2.calisanin maasinin 106450 oldugunu
    - 40,21 ve 19 yaslarinda calisanlar olup olmadigini
    - 11.calisanin bilgilerinin
    {
                "id": 11,
            "employee_name": "Jena Gaines",
            "employee_salary": 90560,
            "employee_age": 30,
            "profile_image": ""
        },
  gibi oldugunu test edin.
     */

@Test
    public void test14(){
    spec03.pathParam("parametre1","employees");

    //DummyTestData class'ina ulasabilmek icin bir OBJE uretiriz.
    DummyTestData expectedObje=new DummyTestData();

    //expectedObje uzerinden setUpTestData()'ya ulastim,
    // bana Map dondurdugu icin  onune HashMap tanimlariz.
    // Obje uzerinden ulastigim classi (DummyTestData'yi)-> expectedDataMap'e atmis oldum.
  HashMap<String,Object> expectedDataMap=expectedObje.setUpTestData();
    System.out.println("Expected Data Map: "+expectedDataMap);   // Expected Data Map: {arananYaslar=[40, 21, 19], besinciCalisan=Airi Satou, sondanIkinciCalisanMaasi=106450, calisanSayisi=24, onbirinciCalisan={profile_image=, employee_name=Jena Gaines, employee_salary=90560, id=11, employee_age=30}, statusCode=200}


    //REQUEST GONDERILIR.
    Response response=given().
            accept("application/json")
            .spec(spec03)
            .when()
            .get("/{parametre1}");
    response.prettyPrint();

    //De-Serialization
    //1.Nested MAP olustururuz.

    HashMap<String,Object> actualDataMap=response.as(HashMap.class);
    System.out.println(actualDataMap);

//  - status kodun 200 oldugunu,
    Assert.assertEquals(expectedDataMap.get("statusCode"),response.getStatusCode());

// - 5.calisan isminin "Airi Satou"
    Assert.assertEquals(expectedDataMap.get("besincicalisan"),
            ((Map)((List)actualDataMap.get("data")).get(4)).get("employee_name"));


//çalışan sayısının 24 olduğunu,

    Assert.assertEquals(expectedDataMap.get("calisansayisi"),
            ((List)actualDataMap.get("data")).size());


    //Sondan 2. çalışanın maaşının 106450 olduğunu

    //önce actual datadan bize dönen listin size ini almalıyız

    int datasize= ((List) actualDataMap.get("data")).size();

    Assert.assertEquals(expectedDataMap.get("sondanikincicalisanmaasi"),
            ((Map)((List<?>) actualDataMap.get("data")).get(datasize-2)).get("employee_salary"));


    //40,21 ve 19 yaslarında çalışanlar olup olmadığını

    List<Integer> actualYasListesi=new ArrayList<Integer>();

    for (int i=0; i<datasize;i++){

        actualYasListesi.add((Integer) ((Map)((List) actualDataMap.get("data")).get(i)).get("employee_age") );

    }

    Assert.assertTrue(actualYasListesi.containsAll((List)expectedDataMap.get("arananyaslar")));
/*
        11. Çalışan bilgilerinin
        {
 “id”:”11”
            "employee_name": "Jena Gaines",
                "employee_salary": "90560",
                "employee_age": "30",
                "profile_image": "" }
    } gibi olduğunu test edin.*/

    Assert.assertEquals(((Map)expectedDataMap.get("onbirincicalisan")).get("employee_name"),
            ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("employee_name"));


    Assert.assertEquals(((Map<?, ?>) expectedDataMap.get("onbirincicalisan")).get("employee_salary"),

            ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("employee_salary"));

    Assert.assertEquals(((Map<?, ?>) expectedDataMap.get("onbirincicalisan")).get("employee_age"),
            ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("employee_age"));

    Assert.assertEquals(((Map<?, ?>) expectedDataMap.get("onbirincicalisan")).get("profile_image"),
            ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("profile_image"));




}

}

