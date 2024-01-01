package com.example.nxtstayz.controller;

import com.example.nxtstayz.model.Hotel;
import com.example.nxtstayz.model.Room;
import com.example.nxtstayz.service.HotelJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HotelController {
    @Autowired
    private HotelJpaService hotelJpaService;

    @GetMapping("/hotels/rooms")
    public ArrayList<Hotel> getHotels() {
        return hotelJpaService.getHotels();
    }

    @GetMapping("/hotels/rooms/{roomId}")
    public Hotel getHotelById(@PathVariable("hotelId") int hotelId) {
        return hotelJpaService.getHotelById(hotelId);
    }

    @PostMapping("/hotels/rooms")
    public Hotel addHotel(@RequestBody Hotel hotel) {
        return hotelJpaService.addHotel(hotel);
    }

    @PutMapping("/hotels/rooms/{roomId}")
    public Hotel updateHotel(@PathVariable("hotelId") int hotelId, @RequestBody Hotel hotel) {
        return hotelJpaService.updateHotel(hotelId, hotel);
    }

    @DeleteMapping("/hotels/rooms/{roomId}")
    public void deleteHotel(@PathVariable("hotelId") int hotelId) {
        hotelJpaService.deleteHotel(hotelId);
    }

    @GetMapping("/rooms/{roomId}/hotel")
    public List<Room> getHotelRooms(@PathVariable("hotelId") int hotelId) {
        return hotelJpaService.getHotelRooms(hotelId);
    }
}