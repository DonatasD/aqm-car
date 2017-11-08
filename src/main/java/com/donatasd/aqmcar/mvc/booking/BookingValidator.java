package com.donatasd.aqmcar.mvc.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class BookingValidator implements Validator {

    private BookingService bookingService;

    @Autowired
    public BookingValidator(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Booking.class .equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Booking booking = (Booking) o;
        if (!booking.getBookedTo().after(booking.getBookedFrom())) {
            errors.reject("booking.invalid");
        }
        List<Booking> conflictingBookings = bookingService
                .findBookingsByCarAndBetweenTimeInterval(booking.getCar(),
                        booking.getBookedFrom(),
                        booking.getBookedTo()
                );
        if (!conflictingBookings.isEmpty()) {
            errors.reject("booking.conflict");
        }
    }
}
