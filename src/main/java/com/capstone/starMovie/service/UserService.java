package com.capstone.starMovie.service;

import java.util.List;

import com.capstone.starMovie.exception.MovieNotExistsException;
import com.capstone.starMovie.model.Movie;

public interface UserService {

	// to get movie by movie name
	public Movie getMovieByMovieName(String movieName) throws MovieNotExistsException;

	// to get movies by category
	public List<Movie> getMoviesByCategory(String movieDescription);

}
