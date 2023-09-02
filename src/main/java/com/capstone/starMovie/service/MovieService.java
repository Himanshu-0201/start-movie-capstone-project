package com.capstone.starMovie.service;

import java.util.List;

import com.capstone.starMovie.exception.MovieAlreadyExistsException;
import com.capstone.starMovie.exception.MovieNotExistsException;
import com.capstone.starMovie.model.Movie;

public interface MovieService {

	// to get all movie list
	public List<Movie> getAllMovies();

	// to add movie
	public Movie addMovie(Movie movie) throws MovieAlreadyExistsException;

	// to update movie
	public Movie updateMovie(Movie movie) throws MovieNotExistsException, MovieAlreadyExistsException;

	// to delete movie by movieid
	public Movie deleteMovie(String movieid) throws MovieNotExistsException;

	// to get movie by movie name
//	public Movie getMovieByMovieName(String movieName) throws MovieNotExistsException;

	// to get movies by category
//	public  List<Movie> getMoviesByCategory(String movieDescription); 

}
