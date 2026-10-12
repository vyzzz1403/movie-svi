package com.example.movie_sv.repository;

import com.example.movie_sv.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Hỗ trợ lấy danh sách vé theo user (phục vụ Nested Resource API)
    List<Booking> findByUserId(Long userId);
}