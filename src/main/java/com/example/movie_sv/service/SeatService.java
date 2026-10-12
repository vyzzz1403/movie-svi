package com.example.movie_sv.service;

import com.example.movie_sv.model.Seat;
import com.example.movie_sv.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    public Seat createSeat(Seat seat) {
        return seatRepository.save(seat);
    }

    // Xử lý cập nhật một phần trạng thái ghế (PATCH)
    public Optional<Seat> patchSeatStatus(Long id, String status) {
        return seatRepository.findById(id).map(seat -> {
            if (status != null && !status.isEmpty()) {
                seat.setStatus(status);
            }
            return seatRepository.save(seat);
        });
    }
}
