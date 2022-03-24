package com.techProEd.TestData;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DummyTestData {

        /* http://dummy.restapiexample.com/api/v1/employees url'ine bir istek gonderildiginde
    - status kodun 200 oldugunu,
    - 5.calisan isminin "Airi Satou"
    - Calisan sayisinin 24 oldugunu,
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

   public HashMap<String, Object> setUpTestData(){
      //Yaslar Key Value formatinda olmadiklari icin List<> olustururuz.  En ICTEN DISA dogru gideriz. Son olusturacagimiz Map HEPSINI icine ALACAK.
       List<Integer> yaslar= new ArrayList<Integer>();
       yaslar.add(40);
       yaslar.add(21);
       yaslar.add(19);

       //11.calisanin bilgileri icin MAP olusturalim
       HashMap<String,Object> onbirinci=new HashMap<String, Object>();
       onbirinci.put("id",11);
       onbirinci.put("employee_name","Jena Gaines");
       onbirinci.put("employee_salary",90560);
       onbirinci.put("employee_age",30);
       onbirinci.put("profile_image","");

       //Task'da verilen butun bilgileri kapsamasi icin yine HasMap kullaniriz.
       HashMap<String,Object> expectedData=new HashMap<String, Object>();   // Icten Disa dogru Map yaptik. EN DISTAKI DATA'miz BU olacak.
              // - status kodun 200 oldugunu,
       expectedData.put("statusCode",200);
             // - 5.calisan isminin "Airi Satou" oldugunu
       expectedData.put("besincicalisan","Airi Satou");
            //- Calisan sayisinin 24 oldugunu
       expectedData.put("calisansayisi",24);
           //- Sondan 2.calisanin maasinin 106450 oldugunu
       expectedData.put("sondanikincicalisanmaasi",106450);
          //- 40,21 ve 19 yaslarinda calisanlar olup olmadigini
       expectedData.put("arananyaslar",yaslar); // yaslar   bunu en basta Array olarak atamistik onunla burda birlestirdik=>>>
          //- 11.calisanin bilgileri
       expectedData.put("onbirincicalisan",onbirinci);


       return expectedData;


    }
     /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
Status kodun 200 olduğunu,
En yüksek maaşın 725000 olduğunu,
En küçük yaşın 19 olduğunu,
İkinci en yüksek maaşın 675000
olduğunu test edin.
     */

    public HashMap<String, Integer> setUpTestData02(){

        HashMap<String,Integer> expectedData=new HashMap<String, Integer>();
        expectedData.put("statusCode",200);
        expectedData.put("enYuksekMaas",725000);
        expectedData.put("enKucukYas",19);
        expectedData.put("ikinciYuksekMaas",675000);
        return expectedData;


    }



}



