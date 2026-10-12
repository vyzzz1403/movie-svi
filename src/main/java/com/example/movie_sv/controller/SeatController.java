package com.example.movie_sv.controller;

import com.example.movie_sv.model.Seat;
import com.example.movie_sv.service.SeatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/seats")
@Tag(name = "Seat API", description = "Quản lý và kiểm tra trạng thái ghế (Hỗ trợ PATCH)")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @Operation(summary = "Lấy danh sách tất cả ghế")
    @GetMapping
    public ResponseEntity<List<Seat>> getAllSeats() {
        return ResponseEntity.ok(seatService.getAllSeats());
    }

    @Operation(summary = "Thêm mới một ghế")
    @PostMapping
    public ResponseEntity<Seat> createSeat(@Valid @RequestBody Seat seat) {
        Seat createdSeat = seatService.createSeat(seat);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSeat);
    }

    @Operation(summary = "Cập nhật một phần trạng thái ghế bằng PATCH method")
    @PatchMapping("/{id}")
    public ResponseEntity<Seat> updateSeatStatus(@PathVariable Long id, @RequestBody Map<String, String> updates) {
        String newStatus = updates.get("status");
        return seatService.patchSeatStatus(id, newStatus)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
