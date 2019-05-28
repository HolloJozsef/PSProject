package net.javaguides.springboot.springsecurity.service;

import net.javaguides.springboot.springsecurity.model.Flight;
import net.javaguides.springboot.springsecurity.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.thymeleaf.expression.Lists;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class FlightServiceImpl {
    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> getFlights(String departure, String destination, String period){
        return flightRepository.findAllByDepartureAndDestinationAndPeriod(departure,destination,period);
    }
}
