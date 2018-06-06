package com.shiftedtech.heatclinic.qa;

import com.google.gson.Gson;
import com.shiftedtech.heatclinic.qa.Model.Product;
import com.shiftedtech.heatclinic.qa.Model.RetailPrice;
import com.shiftedtech.heatclinic.qa.Model.SearchResults;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by Debajyoti Paul on 5/26/2018 at 3:55 PM
 */
public class HeatClinic_JsonTest extends ScriptBase {

    @Test
    public void test1(){
        given()
          .log().all()
          .contentType(ContentType.JSON)
          .accept(ContentType.JSON)
          .param("q", "hot")
        .when()
          .get("/catalog/search/")
        .then()
          .log().body()
          .statusCode(200)
          .body("page", equalTo(1))
          .body("pageSize", equalTo(15))
          .body("totalResults", equalTo(14))
          .body("totalPages", equalTo(1));
    }

    @Test
    public void test2(){
        RequestSpecification request = given()
                                        .log().all()
                                        .contentType(ContentType.JSON)
                                        .accept(ContentType.JSON)
                                        .param("q", "hot");

        Response response = request.when().get("/catalog/search/");

        ValidatableResponse responseToValidate = response.then().log().body();

        responseToValidate.body("product[0].name", equalTo("Bull Snort Smokin' Toncils Hot Sauce"));
        responseToValidate.body("product.name", hasItems("Bull Snort Smokin' Toncils Hot Sauce",
                                                            "Hoppin' Hot Sauce",
                                                            "Roasted Garlic Hot Sauce",
                                                            "Scotch Bonnet Hot Sauce",
                                                            "Hurtin' Jalepeno Hot Sauce",
                                                            "Blazin' Saddle XXX Hot Habanero Pepper Sauce",
                                                            "Dr. Chilemeister's Insane Hot Sauce", "" +
                                                                    "Cool Cayenne Pepper Hot Sauce",
                                                            "Day of the Dead Habanero Hot Sauce",
                                                            "Day of the Dead Chipotle Hot Sauce",
                                                            "Armageddon The Hot Sauce To End All",
                                                            "Bull Snort Cowboy Cayenne Pepper Hot Sauce",
                                                            "Roasted Red Pepper & Chipotle Hot Sauce",
                                                            "Day of the Dead Scotch Bonnet Hot Sauce"));
    }

    @Test
    public void test3(){
        RequestSpecification request = given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .param("q", "hot");

        Response response = request.when().get("/catalog/search/");
        String jsonString = response.asString();
        System.out.println("Json String is : " + jsonString);


//        int totalPages = JsonPath.from(jsonString).get("totalPages");  // We can use both "from" and "with" methods in xml and json formats
        int totalPages = JsonPath.with(jsonString).get("totalPages");
        System.out.println("totalPages:" + totalPages);
        assertThat(totalPages,equalTo(1));

//        String  productName = JsonPath.from(jsonString).get("product[0].name");
        String  productName = JsonPath.with(jsonString).get("product[0].name");
        System.out.println("productName: " + productName );
        assertThat(productName, Matchers.equalToIgnoringCase("Bull Snort Smokin' Toncils Hot Sauce"));

    }

    @Test
    public void test4(){
        RequestSpecification request = given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .param("q", "hot")
                .param("pageSize", "5")
                .param("page", "1");

        Response response = request.when().get("/catalog/search/");
        String jsonString = response.asString();
        System.out.println("Json String is : " + jsonString);
        System.out.println("*******************************************************************");

        Gson gson = new Gson();
        SearchResults searchResults = gson.fromJson(jsonString, SearchResults.class);
        Assert.assertNotNull(searchResults);    //TestNg Assertion
        assertThat(searchResults.getTotalPages(), equalTo(3));  //Hamcrest Assertion

        List<Product> productList = searchResults.getProduct();
        for(Product p: productList){
            System.out.println(p.getName() + " -- " + p.getRetailPrice().getAmount() + " -- " + p.getRetailPrice().getCurrency());
        }
        System.out.println("*******************************************************************");

        String productName = productList.get(0).getName();
        System.out.println("Product #1's Name is : " + productName);
        assertThat(productName, equalToIgnoringCase("Bull Snort Smokin' Toncils Hot Sauce"));
        System.out.println("*******************************************************************");

        RetailPrice retailPrice = productList.get(0).getRetailPrice();
        System.out.println(retailPrice.getAmount() + " -- " + retailPrice.getCurrency());


    }

}
