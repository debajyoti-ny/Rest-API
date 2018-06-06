package com.shiftedtech.heatclinic.qa;

import io.restassured.RestAssured;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Created by Debajyoti Paul on 5/26/2018 at 3:55 PM
 */
public class ScriptBase {

    @BeforeMethod
    public void setUp(){
        RestAssured.baseURI = "http://heatclinic.shiftedtech.com";
        RestAssured.port = 80;
        RestAssured.basePath = "/api/v1/";
    }

    @AfterMethod
    public void tearDown(){
        System.out.println("************ Test Finished *************");
    }

}
