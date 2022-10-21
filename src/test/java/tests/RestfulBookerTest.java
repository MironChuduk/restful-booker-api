package tests;

import api.*;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RestfulBookerTest {

    @Test
    public void checkApiResponse() {
        List<BookingId> books = given()
                .when()
                .contentType(ContentType.JSON)
                .get("https://restful-booker.herokuapp.com/booking")
                .then()
                .extract().body().jsonPath().getList(".", BookingId.class);
        System.out.println(books);
    }

    @Test
    public void checkBookById() {
        BookingData bookingData = given()
                .when()
                .contentType(ContentType.JSON)
                .get("https://restful-booker.herokuapp.com/booking/3595")
                .then()
                .extract().body().jsonPath().getObject(".", BookingData.class);
    }

    @Test
    public void checkAuth() {
        User user = new User();
        Token token = user.createToken("admin", "password123");
        System.out.println(token.getToken());
    }

    @Test
    public void successBooking() {
        RequestedBookingData.Bookingdates bookingdates = new RequestedBookingData
                .Bookingdates("2018-01-01", "2019-01-01");
        RequestedBookingData requestRequestedBookingData = new RequestedBookingData.BookingDataBuilder()
                .setFirstname("Miron")
                .setLastname("Chuduk")
                .setTotalprice(191)
                .setDepositpaid(true)
                .setBookingdates(bookingdates)
                .setAdditionalneeds("Breakfast")
                .build();
        System.out.println(requestRequestedBookingData);
        Integer num = given()
                .contentType(ContentType.JSON)
                .body(requestRequestedBookingData)
                .when()
                .post("https://restful-booker.herokuapp.com/booking")
                .then()
                .extract().body().jsonPath().getObject("bookingid", Integer.class);
        BookingData createdRequestedBookingData = given()
                .when()
                .contentType(ContentType.JSON)
                .get("https://restful-booker.herokuapp.com/booking/" + num)
                .then()
                .extract().body().jsonPath().getObject(".", BookingData.class);
        System.out.println(createdRequestedBookingData);
    }

    @Test
    public void updateBooking() {
        RequestedBookingData.Bookingdates bookingdates = new RequestedBookingData
                .Bookingdates("2018-01-01", "2019-01-01");
        RequestedBookingData requestRequestedBookingData = new RequestedBookingData.BookingDataBuilder()
                .setFirstname("Alex")
                .setLastname("Green")
                .setTotalprice(124)
                .setDepositpaid(true)
                .setBookingdates(bookingdates)
                .setAdditionalneeds("Breakfast")
                .build();
        System.out.println(requestRequestedBookingData);
        BookingData bookingData = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .auth().preemptive().basic("admin", "password123")
                .contentType(ContentType.JSON)
                .body(requestRequestedBookingData)
                .when()
                .put("https://restful-booker.herokuapp.com/booking/121")
                .then()
                .contentType(ContentType.JSON)
                .extract().body().jsonPath().getObject(".", BookingData.class);
        System.out.println(bookingData);
    }

    @Test
    public void partialUpdateBooking() {
        BookingData bookingData = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .auth().preemptive().basic("admin", "password123")
                .body("{\"firstname\" : \"James\", \"lastname\" : \"Brown\"}")
                .when()
                .patch("https://restful-booker.herokuapp.com/booking/121")
                .then()
                .contentType(ContentType.JSON)
                .extract().body().jsonPath().getObject(".", BookingData.class);
        System.out.println(bookingData);
    }

    @Test
    public void deleteBooking() {
        int responseStatusCode = given()
                .header("Content-Type", "application/json")
                .auth().preemptive().basic("admin", "password123")
                .delete("https://restful-booker.herokuapp.com/booking/123")
                .statusCode();
        Assert.assertEquals(responseStatusCode, 201);
    }
}
