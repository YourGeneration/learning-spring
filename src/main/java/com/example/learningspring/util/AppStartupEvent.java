package com.example.learningspring.util;

import com.example.learningspring.data.Guest;
import com.example.learningspring.data.GuestRepository;
import com.example.learningspring.data.Reservation;
import com.example.learningspring.data.ReservationRepository;
import com.example.learningspring.data.Room;
import com.example.learningspring.data.RoomRepository;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent>{
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservaionRepository;

    public AppStartupEvent(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservaionRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservaionRepository = reservaionRepository;
    }    
    
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event){
        Iterable<Room> rooms = this.roomRepository.findAll();
        rooms.forEach(System.out::println);
        Iterable<Guest> guests = this.guestRepository.findAll();
        guests.forEach(System.out::println);
        Iterable<Reservation> reservations = this.reservaionRepository.findAll();
        reservations.forEach(System.out::println);
    }
}
