package com.example.nxtstayz.model;

import com.example.nxtstayz.controller.HotelController;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int roomId;

    @Column(name = "name")
    private String roomName;

    @Column(name = "type")
    private String type;

    @Column(name = "price")
    private Double price;

    @ManyToMany(mappedBy = "rooms")
    @JsonIgnoreProperties("rooms")
    private List<Hotel> hotels;

    public Room() {
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomNumber() {
        return roomName;
    }

    public void setRoomNumber(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomType() {
        return type;
    }

    public void setRoomType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Hotel> getHotel() {
        return hotels;
    }

    public void setHotel(List<Hotel> hotels) {
        this.hotels = hotels;
    }
}