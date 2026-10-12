package com.example.movie_sv.controller;

import com.example.movie_sv.model.Movie;
import com.example.movie_sv.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(name = "Movie API", description = "Quản lý thông tin phim (Search, Filter, Sort, Pagination)")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @Operation(summary = "Lấy danh sách phim v1 (Hỗ trợ tìm kiếm, lọc, phân trang và sắp xếp)")
    @GetMapping("/v1/movies")
    public ResponseEntity<Page<Movie>> getAllMoviesV1(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre,
            Pageable pageable) {
        Page<Movie> movies = movieService.getMovies(title, genre, pageable);
        return ResponseEntity.ok(movies);
    }

    @Operation(summary = "Thử nghiệm API Version 2")
    @GetMapping("/v2/movies")
    public ResponseEntity<Page<Movie>> getAllMoviesV2(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre,
            Pageable pageable) {
        Page<Movie> movies = movieService.getMovies(title, genre, pageable);
        return ResponseEntity.ok(movies);
    }

    @Operation(summary = "Lấy thông tin phim theo ID")
    @GetMapping("/v1/movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Thêm mới một bộ phim")
    @PostMapping("/v1/movies")
    public ResponseEntity<Movie> createMovie(@Valid @RequestBody Movie movie) {
        Movie createdMovie = movieService.createMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovie);
    }

    @Operation(summary = "Cập nhật thông tin phim")
    @PutMapping("/v1/movies/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @Valid @RequestBody Movie movieDetails) {
        return movieService.updateMovie(id, movieDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Xóa phim theo ID")
    @DeleteMapping("/v1/movies/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        if (movieService.deleteMovie(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}