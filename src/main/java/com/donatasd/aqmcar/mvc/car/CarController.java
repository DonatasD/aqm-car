package com.donatasd.aqmcar.mvc.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getCars(Model model) {
        List<Car> cars = carService.findAll();
        model.addAttribute("car", new Car());
        model.addAttribute("listOfCars", cars);
        return "car/carsDetails";
    }
}
