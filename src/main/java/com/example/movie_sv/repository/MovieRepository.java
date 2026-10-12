package com.example.movie_sv.repository;

import com.example.movie_sv.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT m FROM Movie m WHERE " +
           "(:title is null or lower(m.title) like lower(concat('%', :title, '%'))) AND " +
           "(:genre is null or lower(m.genre) like lower(concat('%', :genre, '%')))")
    Page<Movie> searchAndFilterMovies(@Param("title") String title, 
                                      @Param("genre") String genre, 
                                      Pageable pageable);
}