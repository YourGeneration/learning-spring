package com.example.learningspring.webservice;

import java.util.Date;
import java.util.List;

import com.example.learningspring.business.ReservationService;
import com.example.learningspring.business.RoomReservation;
import com.example.learningspring.data.Guest;
import com.example.learningspring.data.Room;
import com.example.learningspring.util.DateUtils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WebserviceController {
    private final DateUtils dateUtils;
    private final ReservationService reservationService;
    
    public WebserviceController(DateUtils dateUtils, ReservationService reservationService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
    }

    @RequestMapping(path="/reservations", method = RequestMethod.GET)
    public List<RoomReservation> getReservations(@RequestParam(value="date", required = false) String dateString){
        Date date = this.dateUtils.createDateFromDateString(dateString);
        return this.reservationService.getRoomReservationForDate(date);
    }

    @GetMapping("/guests")
    public List<Guest> getGuests(){
        return this.reservationService.getHotelGuests();
    }

    @PostMapping("/guests")
    @ResponseStatus(HttpStatus.CREATED)
    public void addGuests(@RequestBody Guest guest){
        this.reservationService.addGuests(guest);
    }

    @GetMapping("/rooms")
    public List<Room> getRooms(){
        return this.reservationService.getRooms();
    }

}
