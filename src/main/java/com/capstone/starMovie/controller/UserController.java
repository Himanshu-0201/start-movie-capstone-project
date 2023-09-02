package com.capstone.starMovie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.starMovie.exception.MovieNotExistsException;
import com.capstone.starMovie.model.Movie;
import com.capstone.starMovie.service.UserServiceImpl;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

	@Autowired
	UserServiceImpl userService;

	// to get movie by it's name
	@GetMapping("/movie/{moviename}")
	public ResponseEntity<?> getMovieByMovieName(@PathVariable("moviename") String movieName) {

		try {
			Movie movie1 = userService.getMovieByMovieName(movieName);
			return new ResponseEntity<Movie>(movie1, HttpStatus.OK);
		} catch (MovieNotExistsException e) {
			return new ResponseEntity<String>("Movie Not Found", HttpStatus.CONFLICT);
		}

	}

	// to get movies by it's category
	@GetMapping("/movie-category/{moviecategory}")
	public ResponseEntity<?> getMoviesByCategory(@PathVariable("moviecategory") String movieCategory) {

		List<Movie> list = userService.getMoviesByCategory(movieCategory);
		return new ResponseEntity<List>(list, HttpStatus.OK);

	}

}
