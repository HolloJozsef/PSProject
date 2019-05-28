package net.javaguides.springboot.springsecurity.repository;

import net.javaguides.springboot.springsecurity.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface FlightRepository  extends JpaRepository<Flight, Long> {
    List<Flight> findAllByDepartureAndDestinationAndPeriod(String departure, String destination, String period);
    Flight findById(long id);
    Flight findByDeparture(String departure);
    List<Flight> findByDestination(String destination);
    List<Flight> findByDepartureAndDestination(String departure,String destination);
}
