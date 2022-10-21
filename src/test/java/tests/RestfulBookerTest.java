package tests;

import api.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.ApiService;

import java.util.List;

public class RestfulBookerTest {

    ApiService apiService = new ApiService();
    Response response;

    @Test
    public void getRequestTest() {
        response = apiService.getRequest("https://restful-booker.herokuapp.com/booking");
        Assert.assertEquals(apiService.getStatusCode(response), 200);
        List<BookingId> bookingIdList = apiService.getBookingIds(response);

        BookingData expectedBookingData = BookingData.getBookingFromProperties("expectedBooking3595");

        String validId = "3595";
        response = apiService.getRequest("https://restful-booker.herokuapp.com/booking/" + validId);
        Assert.assertEquals(apiService.getStatusCode(response), 200);
        BookingData bookingData = apiService.getBookingById(response);
        Assert.assertTrue(bookingData.equals(expectedBookingData));

        String defunctId = "1";
        response = apiService.getRequest("https://restful-booker.herokuapp.com/booking/" + defunctId);
        Assert.assertEquals(apiService.getStatusCode(response), 404);

        String invalidId = "-12qw";
        response = apiService.getRequest("https://restful-booker.herokuapp.com/booking/" + invalidId);
        Assert.assertEquals(apiService.getStatusCode(response), 404);
    }

    @Test
    public void crudOperationTest() {
        BookingData requestPostBooking = BookingData.getBookingFromProperties("requestPostBooking");
        BookingData requestPutBooking = BookingData.getBookingFromProperties("requestPutBooking");

        Integer createdBookingId;

        response = apiService.postRequest("https://restful-booker.herokuapp.com/booking", requestPostBooking);
        Assert.assertEquals(response.statusCode(), 200);
        BookingData responseBooking = apiService.getPostedBooking(response);
        Assert.assertTrue(requestPostBooking.equals(responseBooking));

        createdBookingId = apiService.getPostedBookingId(response);

        response = apiService
                .putRequest("https://restful-booker.herokuapp.com/booking/" + createdBookingId, requestPutBooking);
        Assert.assertEquals(response.statusCode(), 200);
        responseBooking = apiService.getUpdatedBooking(response);
        response = apiService.getRequest("https://restful-booker.herokuapp.com/booking/" + createdBookingId);
        BookingData expectedBooking = apiService.getBookingById(response);
        Assert.assertTrue(responseBooking.equals(expectedBooking));

        response = apiService.deleteRequest("https://restful-booker.herokuapp.com/booking/" + createdBookingId);
        Assert.assertEquals(response.statusCode(), 201);

        response = apiService.getRequest("https://restful-booker.herokuapp.com/booking/" + createdBookingId);
        Assert.assertEquals(response.statusCode(), 404);
    }
}
