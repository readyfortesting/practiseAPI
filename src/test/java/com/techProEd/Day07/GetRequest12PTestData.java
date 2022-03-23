package com.techProEd.Day07;

import com.techProEd.TestBase.JsonPlaceHolderTestBase;
import com.techProEd.TestData.JsonPlaceHolderTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest12PTestData extends JsonPlaceHolderTestBase {
    @Test
    public void test12(){

        spec01.pathParams("parametre1","todos","parametre2",2);

        //TestData sayfasinda olusturdugumuz methoda ulasabilmek icin OBJE uretmemiz gerekiyor
        JsonPlaceHolderTestData expectedObje=new JsonPlaceHolderTestData(); // Olusturdugumuz expectedObje uzerinde TestData'daki setUpTestData() methoduna ulasiriz.
        HashMap<String,Object> expectedData= (HashMap<String, Object>) expectedObje.setUpTestData(); // TYPE CASTING istedi otomatik yaptik
        System.out.println(expectedData); //Olusturdugum TestData'yi ekrana da yazdirabiliriz.




        Response response=given().
                accept("application/json").
                spec(spec01).
                when().
                get("/{parametre1}/{parametre2}");

        response.prettyPrint();

        // 1.Assertion yondemi --- MATCHERS class ile Assertion yaptik.
        response.then().assertThat().statusCode((int) expectedData.get("statusCode")).  //Yukarida olusturdugumuz Map'ten status kodu cagirdik,hata verince TYPE CASTING yapariz.
                headers("via",equalTo(expectedData.get("via")),
                "server",equalTo(expectedData.get("Server"))).
                body("userId",equalTo(expectedData.get("userId")),
                        "title",equalTo(expectedData.get("title")),
                        "completed",equalTo(expectedData.get("completed")));

        //2.Assertion yondemi JsonPath ile Assertion (JsonPath Assertopn'da CASTING gerekmiyor)
        JsonPath jsonPath=response.jsonPath();
        Assert.assertEquals(expectedData.get("statusCode"),response.statusCode()); //1.kisma expected data,2.kisma Actual data yazilir.
        Assert.assertEquals(expectedData.get("via"),response.getHeader("via"));
        Assert.assertEquals(expectedData.get("Server"),response.getHeader("Server"));
        Assert.assertEquals(expectedData.get("userId"),jsonPath.getInt("userId"));
        Assert.assertEquals(expectedData.get("title"),jsonPath.getString("title"));
        Assert.assertEquals(expectedData.get("completed"),jsonPath.getBoolean("completed"));

        //3.Assertion yondemi DE-SERIALIZATION.




    }
}
