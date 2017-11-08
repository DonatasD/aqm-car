package com.donatasd.aqmcar.mvc.booking;

import com.donatasd.aqmcar.mvc.authentication.user.User;
import com.donatasd.aqmcar.mvc.car.Car;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "booked_from")
    @NotNull
    private Date bookedFrom;

    @Column(name = "booked_to")
    @NotNull
    private Date bookedTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    @NotNull
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBookedFrom() {
        return bookedFrom;
    }

    public void setBookedFrom(Date bookedFrom) {
        this.bookedFrom = bookedFrom;
    }

    public Date getBookedTo() {
        return bookedTo;
    }

    public void setBookedTo(Date bookedTo) {
        this.bookedTo = bookedTo;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
