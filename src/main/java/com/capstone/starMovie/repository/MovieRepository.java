package com.capstone.starMovie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.starMovie.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
	// search movie by movie name
	Movie findByMovienameIgnoreCase(String movieName);

	// search movie by category
	List<Movie> findByCategoryIgnoreCase(String category);
}
