package services;

import api.BookingData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.PropertiesParser;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiService {
    public String getLogin() {
        PropertiesParser propertiesParser = new PropertiesParser();
        String[] listOfProperties = propertiesParser.parsProperties("login");
        return listOfProperties[0];
    }

    public String getPassword() {
        PropertiesParser propertiesParser = new PropertiesParser();
        String[] listOfProperties = propertiesParser.parsProperties("password");
        return listOfProperties[0];
    }

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
                .auth().preemptive().basic(getLogin(), getPassword())
                .delete(url);
    }

    public Response putRequest(String url, BookingData requestPutBooking) {
        return given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .auth().preemptive().basic(getLogin(), getPassword())
                .contentType(ContentType.JSON)
                .body(requestPutBooking)
                .when()
                .put(url);
    }

    public List<Integer> getFields(Response response, String fieldName) {
        return response
                .then()
                .extract().body().jsonPath().getList(fieldName, Integer.class);
    }

    public String getBookingField(Response response, String fieldName) {
        return response
                .then()
                .extract().body().jsonPath().getObject(fieldName, String.class);
    }

    public BookingData getBookingById(Response response) {
        return response
                .then()
                .extract().body().jsonPath().getObject(".", BookingData.class);
    }

    public BookingData getBooking(Response response, String fieldName) {
        return response
                .then()
                .extract().body().jsonPath().getObject(fieldName, BookingData.class);
    }

    public Integer getPostedBookingId(Response response) {
        return response
                .then()
                .extract().body().jsonPath().getObject("bookingid", Integer.class);
    }
}
