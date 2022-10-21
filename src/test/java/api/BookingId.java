package api;

public class BookingId {
    private Integer bookingid;

    public BookingId(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public BookingId() {
    }

    public Integer getBookingid() {
        return bookingid;
    }

    @Override
    public String toString() {
        return "Root{" +
                "bookingid=" + bookingid +
                '}';
    }
}
