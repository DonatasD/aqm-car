package com.donatasd.aqmcar.mvc.booking;

import com.donatasd.aqmcar.mvc.authentication.user.User;
import com.donatasd.aqmcar.mvc.car.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BookingService {

    private BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public List<Booking> findAllByUser(User user) {
        return bookingRepository.findAllByUser(user);
    }

    public void delete(Long id) {
        bookingRepository.delete(id);
    }

    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> findBookingsByCarAndBetweenTimeInterval(Car car, Date startInterval, Date endInterval) {
        return bookingRepository.findFirstByCarAndBookedFromAfterAndBookedToBefore(car, startInterval, endInterval);
    }
}
