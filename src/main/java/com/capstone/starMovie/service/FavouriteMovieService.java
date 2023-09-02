package com.capstone.starMovie.service;

import java.util.List;

import com.capstone.starMovie.exception.FavouriteMovieNotExistsException;
import com.capstone.starMovie.exception.MovieAlreadyExistsException;
import com.capstone.starMovie.exception.MovieNotExistsException;
import com.capstone.starMovie.model.FavouriteMovie;

public interface FavouriteMovieService {

	// to get all favourite movie list
	public List<FavouriteMovie> getAllFavouriteMovies();

	// to add movie in favourite movie list
	public FavouriteMovie addFavourite(String movieid) throws MovieNotExistsException, MovieAlreadyExistsException;

	// to delete movie from the favourite movie list
	public FavouriteMovie deleteFavouriteMovieById(String movieid) throws FavouriteMovieNotExistsException;

	// to delete all movie from the favourite movie list
	public void deleteAllFavouriteMovie();
}
