package tests;

import api.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.ApiService;

import java.util.List;

public class RestfulBookerTest {

    private ApiService apiService = new ApiService();
    private Response response;

    private enum Urls {
        MAIN_URL("https://restful-booker.herokuapp.com/booking/");
        private String url;

        Urls(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }

    @Test
    public void getRequestTest() {
        response = apiService.getRequest(Urls.MAIN_URL.getUrl());
        Assert.assertEquals(apiService.getStatusCode(response), 200);
        List<Integer> bookingIdList = apiService.getFields(response, "bookingid");
        System.out.println(bookingIdList.get(0));

        BookingData expectedBookingData = BookingData.getBookingFromProperties("expectedBooking354");

        String validId = "354";
        response = apiService.getRequest(Urls.MAIN_URL.getUrl() + validId);
        Assert.assertEquals(apiService.getStatusCode(response), 200);
        BookingData bookingData = apiService.getBookingById(response);
        Assert.assertTrue(bookingData.equals(expectedBookingData));
        Assert.assertEquals(apiService.getBookingField(response, "firstname"), "Howard");

        String defunctId = "1";
        response = apiService.getRequest(Urls.MAIN_URL.getUrl() + defunctId);
        Assert.assertEquals(apiService.getStatusCode(response), 404);

        String invalidId = "-12qw";
        response = apiService.getRequest(Urls.MAIN_URL.getUrl() + invalidId);
        Assert.assertEquals(apiService.getStatusCode(response), 404);
    }

    @Test
    public void crudOperationTest() {
        BookingData requestPostBooking = BookingData.getBookingFromProperties("requestPostBooking");
        BookingData requestPutBooking = BookingData.getBookingFromProperties("requestPutBooking");

        Integer createdBookingId;

        response = apiService.postRequest(Urls.MAIN_URL.getUrl(), requestPostBooking);
        Assert.assertEquals(response.statusCode(), 200);
        BookingData responseBooking = apiService.getBooking(response, "booking");
        Assert.assertTrue(requestPostBooking.equals(responseBooking));

        createdBookingId = apiService.getPostedBookingId(response);
        response = apiService
                .putRequest(Urls.MAIN_URL.getUrl() + createdBookingId, requestPutBooking);
        Assert.assertEquals(response.statusCode(), 200);
        responseBooking = apiService.getBooking(response, ".");
        Assert.assertEquals(apiService.getBookingField(response, "firstname"), "Alex");
        response = apiService.getRequest(Urls.MAIN_URL.getUrl() + createdBookingId);
        BookingData expectedBooking = apiService.getBookingById(response);
        Assert.assertTrue(responseBooking.equals(expectedBooking));

        response = apiService.deleteRequest(Urls.MAIN_URL.getUrl() + createdBookingId);
        Assert.assertEquals(response.statusCode(), 201);

        response = apiService.getRequest(Urls.MAIN_URL.getUrl() + createdBookingId);
        Assert.assertEquals(response.statusCode(), 404);
    }
}
