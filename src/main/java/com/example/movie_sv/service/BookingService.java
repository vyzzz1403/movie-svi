package com.example.movie_sv.service;

import com.example.movie_sv.model.Booking;
import com.example.movie_sv.repository.BookingRepository;
import com.example.movie_sv.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final MovieRepository movieRepository;
    private final BookingProducer bookingProducer; // Tiêm BookingProducer vào đây

    public BookingService(BookingRepository bookingRepository, 
                          MovieRepository movieRepository, 
                          BookingProducer bookingProducer) {
        this.bookingRepository = bookingRepository;
        this.movieRepository = movieRepository;
        this.bookingProducer = bookingProducer;
    }

    public Booking createBooking(Booking booking) {
        boolean movieExists = movieRepository.existsById(booking.getMovieId());
        if (!movieExists) {
            throw new RuntimeException("Phim với ID " + booking.getMovieId() + " không tồn tại!");
        }

        booking.setStatus("CONFIRMED");
        Booking savedBooking = bookingRepository.save(booking);

        // Gọi Producer để bắn message qua RabbitMQ
        String message = "Đã đặt vé thành công! Movie ID: " + savedBooking.getMovieId() 
                         + ", Số lượng: " + savedBooking.getQuantity() 
                         + ", Tổng tiền: " + savedBooking.getTotalPrice();
        
        bookingProducer.sendBookingEvent(message);

        return savedBooking;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}