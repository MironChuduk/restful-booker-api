package api;

import java.util.Objects;

public class RequestedBookingData {
    private final String firstname;
    private final String lastname;
    private final Integer totalprice;
    private final Boolean depositpaid;
    private final Bookingdates bookingdates;
    private final String additionalneeds;

    public static class Bookingdates {
        public String checkin;
        public String checkout;

        @Override
        public String toString() {
            return "Bookingdates{" +
                    "checkin='" + checkin + '\'' +
                    ", checkout='" + checkout + '\'' +
                    '}';
        }

        public Bookingdates(String checkin, String checkout) {
            this.checkin = checkin;
            this.checkout = checkout;
        }
    }

    private RequestedBookingData(BookingDataBuilder builder) {
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.totalprice = builder.totalprice;
        this.depositpaid = builder.depositpaid;
        this.bookingdates = builder.bookingdates;
        this.additionalneeds = builder.additionalneeds;
    }

    public static class BookingDataBuilder {
        public String firstname;
        public String lastname;
        public Integer totalprice;
        public Boolean depositpaid;
        public RequestedBookingData.Bookingdates bookingdates;
        public String additionalneeds;

        public static class Bookingdates {
            public String checkin;
            public String checkout;
        }

        public BookingDataBuilder setFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public BookingDataBuilder setLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public BookingDataBuilder setTotalprice(Integer totalprice) {
            this.totalprice = totalprice;
            return this;
        }

        public BookingDataBuilder setDepositpaid(Boolean depositpaid) {
            this.depositpaid = depositpaid;
            return this;
        }

        public BookingDataBuilder setBookingdates(RequestedBookingData.Bookingdates bookingdates) {
            this.bookingdates = bookingdates;
            return this;
        }

        public BookingDataBuilder setAdditionalneeds(String additionalneeds) {
            this.additionalneeds = additionalneeds;
            return this;
        }

        public RequestedBookingData build() {
            return new RequestedBookingData(this);
        }
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Integer getTotalprice() {
        return totalprice;
    }

    public Boolean isDepositpaid() {
        return depositpaid;
    }

    public Bookingdates getBookingdates() {
        return bookingdates;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }


    @Override
    public String toString() {
        return "BookingData{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", totalprice=" + totalprice +
                ", depositpaid=" + depositpaid +
                ", bookingdates=" + bookingdates +
                ", additionalneeds='" + additionalneeds + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestedBookingData that = (RequestedBookingData) o;
        return Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(totalprice, that.totalprice) && Objects.equals(depositpaid, that.depositpaid) && Objects.equals(bookingdates, that.bookingdates) && Objects.equals(additionalneeds, that.additionalneeds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, totalprice, depositpaid, bookingdates, additionalneeds);
    }
}
