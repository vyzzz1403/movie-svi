package com.example.movie_sv.controller;

import com.example.movie_sv.model.Booking;
import com.example.movie_sv.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Booking API", description = "Quản lý đặt vé xem phim")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Operation(summary = "Tạo mới một vé đặt (Kiểm tra phim tồn tại, trả về 201 Created)")
    @PostMapping("/bookings")
    public ResponseEntity<?> createBooking(@Valid @RequestBody Booking booking) {
        try {
            Booking newBooking = bookingService.createBooking(booking);
            return ResponseEntity.status(HttpStatus.CREATED).body(newBooking);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Lấy danh sách vé theo User ID (Nested Resource API)")
    @GetMapping("/users/{userId}/bookings")
    public ResponseEntity<List<Booking>> getBookingsByUser(@PathVariable Long userId) {
        List<Booking> bookings = bookingService.getBookingsByUserId(userId);
        return ResponseEntity.ok(bookings);
    }
}