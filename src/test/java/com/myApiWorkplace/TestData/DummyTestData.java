package com.myApiWorkplace.TestData;

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
        //HashMap<String, Integer> => yaptik cunku Task'da istenen bilgilerin hepsi int degerinde donecegi icin.
        //HashMap<String, Object> => donen response'larin String,int,boolean gibi farkli data type'lari oldugunda hepsini KAPSAMASI icin OBJECT yapariz.
        HashMap<String,Integer> expectedData=new HashMap<String, Integer>();
        expectedData.put("statusCode",200);
        expectedData.put("enYuksekMaas",725000);
        expectedData.put("enKucukYas",19);
        expectedData.put("ikinciYuksekMaas",675000);
        return expectedData;


    }

    // 1- Request body olusturduk.
    public HashMap<String, String> setUpRequestBody(){
        HashMap<String,String> requestBody=new HashMap<String,String>();
        requestBody.put("name","BurakAkyuz");
        requestBody.put("salary","35000");
        requestBody.put("age","39");

        return requestBody;

  //HashMap<String,String> Task'ta verilenlerin hepsi String oldugu icin String,String sectik.

    }
        //2- Expected data olusturulur.

    public HashMap<String, Object> setUpExpectedData(){
        //RequestBody'de olusturduklarimizi Burada expectedBody olarak olusturup karsilastiriyoruz.Assert ediyoruz.
        HashMap<String,Object> dataMap=new HashMap<String, Object>();
        dataMap.put("name","BurakAkyuz");
        dataMap.put("salary","35000");
        dataMap.put("age","39");

        HashMap<String,Object> expectedData=new HashMap<String, Object>();
        expectedData.put("statusCode",200);
        expectedData.put("status","success");
     //   expectedData.put("data",dataMap);
        expectedData.put("message","Successfully! Record has been added.");

        //hepsini kapsayan expectedData dondurur onun icin return type expectedData olur

return expectedData; // hata verir. methodun return type HashMap yazilinca duzelir.

/*
data          :>>     inner
expected      :>>     outer
expected HEPSINI KAPSAR!
 */



    }

    /*
    {
 "status": "success",
 "data": "2",
 "message": "Successfully! Record has been deleted"
 }
     */
public JSONObject setUpDeleteExpectedData(){
    JSONObject expectedData=new JSONObject();
    expectedData.put("status","success");
    expectedData.put("data","2");
    expectedData.put("message","Successfully! Record has been deleted");


    return expectedData;




}








}



