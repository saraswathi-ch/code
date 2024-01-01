package com.example.nxtstayz.service;

import com.example.nxtstayz.model.Hotel;
import com.example.nxtstayz.model.Room;
import com.example.nxtstayz.repository.HotelJpaRepository;
import com.example.nxtstayz.repository.RoomJpaRepository;
import com.example.nxtstayz.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoomJpaService implements RoomRepository {

    @Autowired
    private HotelJpaRepository hotelJpaRepository;

    @Autowired
    private RoomJpaRepository roomJpaRepository;

    public ArrayList<Room> getRooms() {
        List<Room> roomList = roomJpaRepository.findAll();
        ArrayList<Room> rooms = new ArrayList<>(roomList);
        return rooms;
    }

    public Room getRoomById(int roomId) {
        try {
            Room room = roomJpaRepository.findById(roomId).get();
            return room;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Room addRoom(Room room) {
        List<Integer> hotelIds = new ArrayList<>();
        for (Hotel hotel : room.getHotel()) {
            hotelIds.add(hotel.getHotelId());
        }

        List<Hotel> hotels = hotelJpaRepository.findAllById(hotelIds);
        room.setHotel(hotels);

        for (Hotel hotel : hotels) {
            hotel.getRooms().add(room);
        }

        Room savedRoom = roomJpaRepository.save(room);
        hotelJpaRepository.saveAll(hotels);

        return savedRoom;
    }

    public Room updateRoom(int roomId, Room room) {
        try {
            Room newRoom = roomJpaRepository.findById(roomId).get();
            if (room.getRoomNumber() != null) {
                newRoom.setRoomNumber(room.getRoomNumber());
            }
            if (room.getRoomType() != null) {
                newRoom.setRoomType(room.getRoomType());
            }
            if (room.getPrice() != null) {
                newRoom.setPrice(room.getPrice());
            }
            if (room.getHotel() != null) {
                List<Hotel> hotels = newRoom.getHotel();
                for (Hotel hotel : hotels) {
                    hotel.getRooms().remove(newRoom);
                }
                hotelJpaRepository.saveAll(hotels);
                List<Integer> newHotelIds = new ArrayList<>();
                for (Hotel hotel : room.getHotel()) {
                    newHotelIds.add(hotel.getHotelId());
                }
                List<Hotel> newHotels = hotelJpaRepository.findAllById(newHotelIds);
                for (Hotel hotel : newHotels) {
                    hotel.getRooms().add(newRoom);
                }
                hotelJpaRepository.saveAll(newHotels);
                newRoom.setHotel(newHotels);
            }
            return roomJpaRepository.save(newRoom);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void deleteRoom(int roomId) {
        try {
            Room room = roomJpaRepository.findById(roomId).get();

            List<Hotel> hotels = room.getHotel();
            for (Hotel hotel : hotels) {
                hotel.getRooms().remove(room);
            }

            hotelJpaRepository.saveAll(hotels);

            roomJpaRepository.deleteById(roomId);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    public List<Room> getRoomHotels(int roomId) {
        try {
            Room room = roomJpaRepository.findById(roomId).get();
            return room.getHotel();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}