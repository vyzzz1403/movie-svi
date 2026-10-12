package com.example.movie_sv.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Số ghế không được để trống")
    private String seatNumber; // Ví dụ: A1, A2, B5...

    @NotBlank(message = "Phòng chiếu không được để trống")
    private String room; // Ví dụ: Room 1, Room 2...

    @NotBlank(message = "Trạng thái ghế không được để trống")
    private String status; // AVAILABLE, RESERVED, BOOKED

    public Seat() {}

    public Seat(String seatNumber, String room, String status) {
        this.seatNumber = seatNumber;
        this.room = room;
        this.status = status;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }
    public String getRoom() { return room; }
    public void setRoom(String room) { this.room = room; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
