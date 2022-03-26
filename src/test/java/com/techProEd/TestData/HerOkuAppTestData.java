package com.techProEd.TestData;

import com.google.gson.JsonObject;
import org.json.JSONObject;

import java.util.HashMap;

public class HerOkuAppTestData {
   /* {
        "firstname": "Sally",
            "lastname": "Wilson",
            "totalprice": 849,
            "depositpaid": true,
            "bookingdates": {
        "checkin": "2015-08-04",
                "checkout": "2017-01-02"
    }
    }


    */

    public HashMap<String, Object> setUpTestData(){

        HashMap<String ,Object> bookingdates=new HashMap<String, Object>();
        bookingdates.put("checkin","2016-07-28");
        bookingdates.put("checkout","2020-03-26");

        HashMap<String ,Object> expectedData=new HashMap<String, Object>();
        expectedData.put("firstname","Sally");
        expectedData.put("lastname","Smith");
        expectedData.put("totalprice",716);
        expectedData.put("depositpaid",false);
        expectedData.put("bookingdates",bookingdates); // yukarida olusturdugumuz bookingdate;yi yazdik.


        return expectedData;

    }


  /*  https://restful-booker.herokuapp.com/booking/1  url'ine bir istek gonderildiginde
    donen response body;nin
{
     "firstname": "Jim",
    "lastname": "Ericsson",
    "totalprice": 497,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2016-03-18",
        "checkout": "2021-12-26"
    }
}    oldugunu test edin.  */

/* Nested Map olusturacagiz bookingdates ile,daha sonra baska bir Map olusturup icine atacagiz
yani Nested Map olusturmus olacagiz.

1- Test data classi  (HerOkuAppTestData) olusturacagiz.
2- HerOkuAppTestData classinda method olustururuz.
         public void setUpTestData(){

3-               (ONCE icteki Map'i olustururuz,yani bookingdates'ten baslariz.)
        HashMap<String,Object> bookingDates=new HashMap<String, Object>();
4-              bookingDates.put("checkin","2016-03-18");
                bookingDates.put("checkout","2021-12-26");

5-            (SONRA distaki Map'i olustururuz)
       HashMap<String,Object> expectedData=new HashMap<String, Object>();
               expectedData.put("firstname","Jim");
               expectedData.put( "lastname","Ericsson");
               expectedData.put("totalprice",497);
               expectedData.put("depositpaid",false);
               expectedData.put("bookingdates",bookingDates);  boylece ic ice MAP olusturmus olduk

               **Olusturdugumuz bookingDates ile expectedData.put("bookingdates.


6-   return expectedData;
7-   public void setUpTestData(){  void sileriz ve yerine
   public HashMap<String, Object> setUpTestData() { YAPARIZ.

 */
public JSONObject setUpTestAndRequestData(){
    //Json object olusturacagiz,once ictekinden baslanir bookingdates

    JSONObject bookingdates=new JSONObject(); //Burda datatype'ni (String,Object,Int,Boolean) yazmiyoruz o kendisi karar verecek.
    bookingdates.put("checkin","2021-01-05");   //JSONObject yapisi da degerleri Key Value seklinde alir.
    bookingdates.put("checkout","2021-01-10");

    JSONObject expectedRequest =new JSONObject();
    expectedRequest.put("firstname","Batch30");
    expectedRequest.put("lastname","bitti");
    expectedRequest.put("totalprice",123);
    expectedRequest.put("depositpaid",false);
    expectedRequest.put("bookingdates",bookingdates);

    return expectedRequest; // returnType : JSONObject olacak.
}






}