package com.techProEd.TestBase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonPlaceHolderTestBase {

  protected   RequestSpecification spec01;

  @Before
    public void setUp(){

      spec01=new RequestSpecBuilder().
              setBaseUri("https://jsonplaceholder.typicode.com").
              build();

  }

}
//protected yaptik kendi package'daki TUM CHILD'lar ulassin diye .