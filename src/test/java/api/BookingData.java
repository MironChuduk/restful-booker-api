package api;

import utils.PropertiesParser;

import java.util.Objects;

public class BookingData {
    private String firstname;
    private String lastname;
    private Integer totalprice;
    private Boolean depositpaid;
    private BookingData.Bookingdates bookingdates;
    private String additionalneeds;

    public static class Bookingdates {
        public String checkin;
        public String checkout;

        public Bookingdates(String checkin, String checkout) {
            this.checkin = checkin;
            this.checkout = checkout;
        }


        public Bookingdates() {
        }

        @Override
        public String toString() {
            return "Bookingdates{" +
                    "checkin='" + checkin + '\'' +
                    ", checkout='" + checkout + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Bookingdates that = (Bookingdates) o;
            return Objects.equals(checkin, that.checkin) && Objects.equals(checkout, that.checkout);
        }

        @Override
        public int hashCode() {
            return Objects.hash(checkin, checkout);
        }
    }

    public BookingData(String firstname, String lastname, Integer totalprice, Boolean depositpaid,
                       BookingData.Bookingdates bookingdates, String additionalneeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
        this.additionalneeds = additionalneeds;
    }

    public BookingData() {

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

    public Boolean getDepositpaid() {
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

    private BookingData(BookingData.BookingDataBuilder builder) {
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
        public BookingData.Bookingdates bookingdates;
        public String additionalneeds;

        public static class Bookingdates {
            public String checkin;
            public String checkout;
        }

        public BookingData.BookingDataBuilder setFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public BookingData.BookingDataBuilder setLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public BookingData.BookingDataBuilder setTotalprice(Integer totalprice) {
            this.totalprice = totalprice;
            return this;
        }

        public BookingData.BookingDataBuilder setDepositpaid(Boolean depositpaid) {
            this.depositpaid = depositpaid;
            return this;
        }

        public BookingData.BookingDataBuilder setBookingdates(BookingData.Bookingdates bookingdates) {
            this.bookingdates = bookingdates;
            return this;
        }

        public BookingData.BookingDataBuilder setAdditionalneeds(String additionalneeds) {
            this.additionalneeds = additionalneeds;
            return this;
        }

        public BookingData build() {
            return new BookingData(this);
        }
    }

    public static BookingData getBookingFromProperties(String key) {
        PropertiesParser propertiesParser = new PropertiesParser();
        String[] listOfProperties = propertiesParser.parsProperties(key);
        BookingData.Bookingdates bookingDates = new BookingData
                .Bookingdates(listOfProperties[4], listOfProperties[5]);
        return new BookingDataBuilder()
                .setFirstname(listOfProperties[0])
                .setLastname(listOfProperties[1])
                .setTotalprice(Integer.valueOf(listOfProperties[2]))
                .setDepositpaid(Boolean.valueOf(listOfProperties[3]))
                .setBookingdates(bookingDates)
                .setAdditionalneeds(listOfProperties[6])
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingData that = (BookingData) o;
        return Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(totalprice, that.totalprice) && Objects.equals(depositpaid, that.depositpaid) && Objects.equals(bookingdates, that.bookingdates) && Objects.equals(additionalneeds, that.additionalneeds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, totalprice, depositpaid, bookingdates, additionalneeds);
    }
}
