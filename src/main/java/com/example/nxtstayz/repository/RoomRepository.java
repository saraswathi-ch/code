package com.example.nxtstayz.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.nxtstayz.model.Hotel;
import com.example.nxtstayz.model.Room;

public interface RoomRepository {
    ArrayList<Room> getRooms();

    Room getRoomById(int roomId);

    Room addRoom(Room room);

    Room updateRoom(int roomId, Room room);

    void deleteRoom(int roomId);

    List<Room> getRoomHotels(int roomId);
}