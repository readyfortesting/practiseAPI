package com.techProEd.TestData;

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

    public HashMap<String, Object> setUpTestData() {
        HashMap<String, Object> bookingDates = new HashMap<String, Object>();
        bookingDates.put("checkin", "2021-07-04");
        bookingDates.put("checkout", "2021-09-02");

        HashMap<String, Object> expectedData = new HashMap<String, Object>();
        expectedData.put("firstname", "Sally");
        expectedData.put("lastname", "Ericsson");
        expectedData.put("totalprice", 613);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingDates); // yukarida olusturdugumuz bookingdate;yi yazdik.


        return expectedData;

    }
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







