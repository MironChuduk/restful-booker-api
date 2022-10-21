package services;

import api.BookingData;
import api.BookingId;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiService {
    public int getStatusCode(Response res) {
        return res.statusCode();
    }

    public Response getRequest(String url) {
        return given()
                .when()
                .contentType(ContentType.JSON)
                .get(url);
    }

    public Response postRequest(String url, BookingData requestPostBooking) {
        return given()
                .contentType(ContentType.JSON)
                .body(requestPostBooking)
                .when()
                .post(url);
    }

    public Response deleteRequest(String url) {
        return given()
                .header("Content-Type", "application/json")
                .auth().preemptive().basic("admin", "password123")
                .delete(url);
    }

    public Response putRequest(String url, BookingData requestPutBooking) {
        return given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .auth().preemptive().basic("admin", "password123")
                .contentType(ContentType.JSON)
                .body(requestPutBooking)
                .when()
                .put(url);
    }

    public List<BookingId> getBookingIds(Response response) {
        return response
                .then()
                .extract().body().jsonPath().getList(".", BookingId.class);
    }

    public BookingData getBookingById(Response response) {
        return response
                .then()
                .extract().body().jsonPath().getObject(".", BookingData.class);
    }

    public String getFirstNameFromBookingById(Response response) {
        return response
                .then()
                .extract().body().jsonPath().getObject("firstname", String.class);
    }

    public BookingData getPostedBooking(Response response) {
        return response
                .then()
                .extract().body().jsonPath().getObject("booking", BookingData.class);
    }

    public Integer getPostedBookingId(Response response) {
        return response
                .then()
                .extract().body().jsonPath().getObject("bookingid", Integer.class);
    }

    public BookingData getUpdatedBooking(Response response) {
        return response
                .then()
                .extract().body().jsonPath().getObject(".", BookingData.class);
    }

    public String getFirstNameFromUpdatedBooking(Response response) {
        return response
                .then()
                .extract().body().jsonPath().getObject("firstname", String.class);
    }
}
