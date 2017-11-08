package com.donatasd.aqmcar.mvc.booking;

import com.donatasd.aqmcar.mvc.authentication.user.User;
import com.donatasd.aqmcar.mvc.authentication.user.UserService;
import com.donatasd.aqmcar.mvc.car.Car;
import com.donatasd.aqmcar.mvc.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class BookingController {

    private BookingService bookingService;
    private CarService carService;
    private UserService userService;
    private BookingValidator bookingValidator;

    @Autowired
    public BookingController(BookingService bookingService,
                             CarService carService,
                             UserService userService,
                             BookingValidator bookingValidator) {
        this.bookingService = bookingService;
        this.carService = carService;
        this.userService = userService;
        this.bookingValidator = bookingValidator;
    }



    @InitBinder
    protected void initBinder(final WebDataBinder webDataBinder) {
        webDataBinder.addValidators(this.bookingValidator);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, false));
    }


    @RequestMapping(value = {"/", "/bookings"}, method = RequestMethod.GET)
    public String getBookings(Model model) {
        User user = userService.findCurrentUser();
        List<Booking> bookings = bookingService.findAllByUser(user);
        List<Car> cars = carService.findAll();
        model.addAttribute("bookings", bookings);
        model.addAttribute("booking", new Booking());
        model.addAttribute("cars", cars);
        return "bookings";
    }

    @RequestMapping(value = {"/bookings/{id}/delete"}, method = RequestMethod.POST)
    public String deleteBooking(@PathVariable("id") Long id) {
        bookingService.delete(id);
        return "redirect:/bookings";
    }

    @RequestMapping(value = {"/bookings"}, method = RequestMethod.POST)
    public String createBooking(@Valid @ModelAttribute("booking") Booking booking,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "booking is invalid");
            return "redirect:/bookings";
        }
        User user = userService.findCurrentUser();
        booking.setUser(user);
        bookingService.save(booking);
        return "redirect:/bookings";
    }
}
