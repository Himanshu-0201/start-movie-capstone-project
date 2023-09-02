package com.capstone.starMovie.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.starMovie.model.FavouriteMovie;

@Repository
public interface FavouriteMovieRespository extends JpaRepository<FavouriteMovie, String> {
	// search favourite movie by movieid not by favourite movie id
	Optional<FavouriteMovie> findByMovieid(String movieid);
}
