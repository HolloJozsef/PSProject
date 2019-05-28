package net.javaguides.springboot.springsecurity.web;

import net.javaguides.springboot.springsecurity.config.SecurityConfiguration;
import net.javaguides.springboot.springsecurity.model.Flight;
import net.javaguides.springboot.springsecurity.model.Order;
import net.javaguides.springboot.springsecurity.repository.FlightRepository;
import net.javaguides.springboot.springsecurity.repository.OrderRepository;
import net.javaguides.springboot.springsecurity.repository.UserRepository;
import net.javaguides.springboot.springsecurity.service.FlightServiceImpl;
import net.javaguides.springboot.springsecurity.service.OrderService;
import net.javaguides.springboot.springsecurity.web.dto.FlightDetailsInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
public class MainController {

    public static String userEmail;
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepository orderRepository;

    OrderService orderService;
    private FlightServiceImpl flightServiceImpl;
    @GetMapping("/")
    public String root(Authentication authentication) {
        userEmail=authentication.getName();
        System.out.println(authentication.getName());
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {

        return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }


    @GetMapping("/flight-details")
    public String flightDetails(FlightDetailsInput flightDetailsInput, Model model){
        System.out.println(flightDetailsInput.toString());
        List<Flight> flights=flightRepository.findAllByDepartureAndDestinationAndPeriod(flightDetailsInput.getOrigin(), flightDetailsInput.getDestination(), flightDetailsInput.getDatepicker());
        model.addAttribute("flights",flights);
        return "flight-details";
    }

    @RequestMapping(path="/order/{flightId}",produces = "text/html")
    public String orderDetails(@PathVariable("flightId") long flightID,Model model){
        System.out.println(flightRepository.findById(flightID).toString());
        System.out.println(userRepository.findByEmail(userEmail).toString());
        Order order=new Order(flightRepository.findById(flightID),userRepository.findByEmail(userEmail));
        System.out.println(order.toString());
        orderRepository.save(order);
        model.addAttribute("flight",flightRepository.findById(flightID));
        model.addAttribute("user",userRepository.findByEmail(userEmail));
        model.addAttribute("order",order);
        return "order_details";
    }
    @RequestMapping(path="/order/return/{flightId}",produces = "text/html")
    public String orderDetailsReturn(@PathVariable("flightId") long flightID,Model model) {
        Flight flight=flightRepository.findById(flightID);
      // List <Flight> departure=flightRepository.findByDestination(flight.getDeparture());
      //  String destination=flightRepository.findByDeparture(flight.getDestination()).getDeparture();
        List<Flight> returnFlights=flightRepository.findByDepartureAndDestination(flight.getDestination(),flight.getDeparture());
        model.addAttribute("returnFlights",returnFlights);
        return "return_flight";
    }
}
