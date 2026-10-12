package com.example.movie_sv.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "ID phim không được để trống")
    private Long movieId;

    @NotNull(message = "ID người dùng không được để trống")
    private Long userId;

    @Min(value = 1, message = "Số lượng vé ít nhất phải là 1")
    private int quantity;

    private double totalPrice;

    private LocalDateTime bookingTime;

    private String status; // CONFIRMED, CANCELLED

    public Booking() {
        this.bookingTime = LocalDateTime.now();
    }

    public Booking(Long movieId, Long userId, int quantity, double totalPrice, String status) {
        this.movieId = movieId;
        this.userId = userId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.bookingTime = LocalDateTime.now();
        this.status = status;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getMovieId() { return movieId; }
    public void setMovieId(Long movieId) { this.movieId = movieId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    public LocalDateTime getBookingTime() { return bookingTime; }
    public void setBookingTime(LocalDateTime bookingTime) { this.bookingTime = bookingTime; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}