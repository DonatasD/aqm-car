package com.donatasd.aqmcar.mvc.booking;

import com.donatasd.aqmcar.mvc.authentication.user.User;
import com.donatasd.aqmcar.mvc.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByUser(User user);
    List<Booking> findFirstByCarAndBookedFromAfterAndBookedToBefore(Car car, Date startInterval, Date endInterval);
}
