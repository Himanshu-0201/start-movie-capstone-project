package com.capstone.starMovie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.starMovie.exception.FavouriteMovieNotExistsException;
import com.capstone.starMovie.exception.MovieAlreadyExistsException;
import com.capstone.starMovie.exception.MovieNotExistsException;
import com.capstone.starMovie.model.FavouriteMovie;
import com.capstone.starMovie.service.FavouriteMovieServiceImpl;

@RestController
@RequestMapping("api/v1/user")
public class FavouriteMovieController {

	@Autowired
	FavouriteMovieServiceImpl favMovieSerice;

	// to get all favourite movie
	@GetMapping("/allFav")
	public ResponseEntity<?> fetchAllFavouriteMovie() {

		List<FavouriteMovie> list = favMovieSerice.getAllFavouriteMovies();
		return new ResponseEntity<List>(list, HttpStatus.OK);
	}

	// to add movie in favourite list
	@PostMapping("/add-fav-movie/{movieid}")
	public ResponseEntity<?> addFavMovie(@PathVariable("movieid") String id) {

		try {
			FavouriteMovie favMovie = favMovieSerice.addFavourite(id);
			return new ResponseEntity<FavouriteMovie>(favMovie, HttpStatus.CREATED);
		} catch (MovieNotExistsException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (MovieAlreadyExistsException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	// delete favourite movie by movieId
	@DeleteMapping("/delete/{movieid}")
	public ResponseEntity<?> deleteFavouriteMovieById(@PathVariable("movieid") String id) {

		try {
			FavouriteMovie result = favMovieSerice.deleteFavouriteMovieById(id);
			return new ResponseEntity<String>("Movie deleted", HttpStatus.OK);
		} catch (FavouriteMovieNotExistsException e) {
			return new ResponseEntity<String>("Movie Not Found", HttpStatus.CONFLICT);
		}
	}

	// to delete all movie from favourite movie list
	@DeleteMapping("/delete-all")
	public ResponseEntity<?> deleteFavouriteAllMovie() {

		favMovieSerice.deleteAllFavouriteMovie();
		return new ResponseEntity<String>("all Movie deleted", HttpStatus.OK);

	}

}
