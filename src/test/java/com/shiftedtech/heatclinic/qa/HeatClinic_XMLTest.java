package com.shiftedtech.heatclinic.qa;

import io.restassured.RestAssured;
import io.restassured.http.*;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.path.xml.XmlPath.from;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

/**
 * Created by Debajyoti Paul on 5/25/2018 at 1:08 PM
 */
public class HeatClinic_XMLTest {

    @BeforeMethod
    public void setUp(){
        RestAssured.baseURI = "http://heatclinic.shiftedtech.com";
        RestAssured.port = 80;
        RestAssured.basePath = "/api/v1/";
    }

    // Verify status code (200) if response is received properly
    @Test
    public void searchStatusCode(){
        given()
          .log().all()
          .contentType(ContentType.XML)
          .accept(ContentType.XML)
          .param("q", "hot")
       .when()
          .get("/catalog/search")
       .then()
          .log().body()
          .statusCode(200);

    }

    // Same test like above but done in a less abstract way. (Fluent Interface is more abstract)
    @Test
    public void searchStatusCode2(){
        RequestSpecification request = given()
                                        .log().all()
                                        .contentType(ContentType.XML)
                                        .accept(ContentType.XML)
                                        .param("q", "hot");

        Response response = request.get("/catalog/search");
        System.out.println(response.prettyPrint());

        ValidatableResponse validatableResponse = response.then();

        validatableResponse.statusCode(200);

    }

    // Verify the search result (in this case, verifying total products)
    // We can perform multiple verification in the same test. In this case, we are verifying both status code and total products
    @Test
    public void searchForProducts(){
        given()
           .log().all()
           .contentType(ContentType.XML)
           .accept(ContentType.XML)
           .param("q", "hot")
       .when()
           .get("/catalog/search")
       .then()
           .log().body()
           .statusCode(200)
           .body(hasXPath("/searchResults/totalResults", containsString("14")));
    }

    @Test
    public void searchForProducts2(){

        RequestSpecification request = given()
                                          .log().all()
                                          .contentType(ContentType.XML)
                                          .accept(ContentType.XML)
                                          .param("q", "hot");

        Response response = request.when().get("/catalog/search");
        System.out.println(response.prettyPrint());

        ValidatableResponse responseToValidate = response.then();

        responseToValidate.statusCode(200);
        responseToValidate.body(hasXPath("/searchResults/products/product[1]/id", equalToIgnoringCase("13")));

    }

    @Test
    public void searchForProducts3(){

        RequestSpecification request = given()
                                         .log().all()
                                         .contentType(ContentType.XML)
                                         .accept(ContentType.XML)
                                         .param("q", "hot");

        Response response = request.when().get("/catalog/search");
        System.out.println(response.prettyPrint());

        ValidatableResponse responseToValidate = response.then();
        responseToValidate.statusCode(200);
        responseToValidate.body(hasXPath("/searchResults/totalResults",containsString("14")));
        responseToValidate.body(hasXPath("/searchResults/products/product/id[text()='13']"));
        responseToValidate.body(hasXPath("/searchResults/products/product[1]/id",equalToIgnoringCase("13")));
        responseToValidate.body(hasXPath("/searchResults/products/product[1]/name", equalToIgnoringCase("Bull Snort Smokin' Toncils Hot Sauce")));

    }

    @Test
    public void searchForAllProducts(){
        RequestSpecification request = given()
                                         .log().all()
                                         .contentType(ContentType.XML)
                                         .accept(ContentType.XML)
                                         .param("q", "hot");

        Response response = request.when().get("/catalog/search");
        String textInBody = response.asString();
        System.out.println("Body -- " + textInBody);

        List<String> products = from(textInBody).getList("searchResults.products.product.name");
        System.out.println("Product Count -- " + products.size());
        assertThat(products.size(), equalTo(14));

        String[] actualProducts = new String[products.size()];
        actualProducts = products.toArray(actualProducts);

        String[] expectedProducts = {
                                        "Bull Snort Smokin' Toncils Hot Sauce",
                                        "Hoppin' Hot Sauce",
                                        "Roasted Garlic Hot Sauce",
                                        "Scotch Bonnet Hot Sauce",
                                        "Hurtin' Jalepeno Hot Sauce",
                                        "Blazin' Saddle XXX Hot Habanero Pepper Sauce",
                                        "Dr. Chilemeister's Insane Hot Sauce",
                                        "Cool Cayenne Pepper Hot Sauce",
                                        "Day of the Dead Habanero Hot Sauce",
                                        "Day of the Dead Chipotle Hot Sauce",
                                        "Armageddon The Hot Sauce To End All",
                                        "Bull Snort Cowboy Cayenne Pepper Hot Sauce",
                                        "Roasted Red Pepper & Chipotle Hot Sauce",
                                        "Day of the Dead Scotch Bonnet Hot Sauce"
                                    };

        assertThat(expectedProducts, arrayContainingInAnyOrder(actualProducts));

    }

    @Test
    public void searchHeaderStatusCode(){

        given()
          .log().all()
          .contentType(ContentType.XML)
          .accept(ContentType.XML)
          .param("q", "hot")
       .when()
          .get("/catalog/search")
       .then()
          .log().body()
          .statusCode(200)
          .header("content-type", "application/xml;charset=UTF-8")
          .contentType(startsWith("application/xml"));

    }

    @Test
    public void printHeadersAndCookies(){
        RequestSpecification request = given()
                                        .log().all()
                                        .contentType(ContentType.XML)
                                        .accept(ContentType.XML)
                                        .param("q", "hot");

        Response response = request.when().get("/catalog/search");
        String textInBody = response.asString();
        System.out.println(textInBody);

        Headers headers = response.getHeaders();
        List<Header> headerList = headers.asList();
        int count = 1;
        for (Header h: headerList){
            System.out.println("Header #" + count + " -- " + h);
            count++;
        }

        System.out.println("************** Cookies *****************");
        Cookies cookies = response.getDetailedCookies();
        List<Cookie> cookieList = cookies.asList();
        int counter = 1;
        for(Cookie c: cookieList){
            System.out.println("Cookie #" + counter + " -- " + c);
        }

    }

    @AfterMethod
    public void tearDown(){
        System.out.println("*********** Test Finished **************");
    }

}
