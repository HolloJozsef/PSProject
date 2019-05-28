package net.javaguides.springboot.springsecurity.web.dto;

public class FlightDetailsInput {
    public String origin;
    public String destination;
    public String period;

    public FlightDetailsInput(String origin, String destination, String period) {
        this.origin = origin;
        this.destination = destination;
        this.period = period;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDatepicker() {
        return period;
    }

    public void setDatepicker(String datepicker) {
        this.period = datepicker;
    }

    @Override
    public String toString() {
        return "FlightDetailsInput{" +
                "origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", datepicker='" + period + '\'' +
                '}';
    }
}