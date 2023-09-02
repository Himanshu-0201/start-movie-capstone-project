package com.capstone.starMovie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.starMovie.exception.MovieAlreadyExistsException;
import com.capstone.starMovie.exception.MovieNotExistsException;
import com.capstone.starMovie.model.Movie;
import com.capstone.starMovie.service.MovieServiceImpl;

@RestController
@RequestMapping("api/v1/admin")
public class MovieController {

	@Autowired
	MovieServiceImpl movieService;

	// to add new movie
	@PostMapping("/addmovie")
	public ResponseEntity<?> addBlog(@RequestBody Movie movie) {
		try {
			Movie movie1 = movieService.addMovie(movie);
			return new ResponseEntity<Movie>(movie1, HttpStatus.CREATED);
		} catch (MovieAlreadyExistsException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	// to get all movie
	@GetMapping("viewallmovie")
	public ResponseEntity<?> fetchAllMovie() {
		List<Movie> list = movieService.getAllMovies();
		return new ResponseEntity<List>(list, HttpStatus.OK);
	}

	// to update movie
	@PutMapping("/update")
	public ResponseEntity<?> updateMovie(@RequestBody Movie movie) {
		try {
			Movie movie1 = movieService.updateMovie(movie);
			return new ResponseEntity<Movie>(movie1, HttpStatus.OK);
		} catch (MovieNotExistsException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (MovieAlreadyExistsException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	// to delete the movie by specific id
	@DeleteMapping("/delete/{movieid}")
	public ResponseEntity<?> deleteBlog(@PathVariable("movieid") String id) {
		try {
			Movie result = movieService.deleteMovie(id);
			return new ResponseEntity<String>("Movie deleted", HttpStatus.OK);
		} catch (MovieNotExistsException e) {
			return new ResponseEntity<String>("Movie Not Found", HttpStatus.CONFLICT);
		}
	}

	// to get movie by it's name
//    @GetMapping("/movie/{moviename}")
//    public ResponseEntity<?> getMovieByMovieName(@PathVariable("moviename") String movieName){
//    	
//    	
//    	try {
//    		Movie movie1  = movieService.getMovieByMovieName(movieName);
//    		return new ResponseEntity<Movie>(movie1, HttpStatus.OK);
//    	}
//    	catch (MovieNotExistsException e) {
//    		return new ResponseEntity<String>("Movie Not Found", HttpStatus.CONFLICT);
//    	}
//    	catch()
//    }

	// to get movies by it's category

//    @GetMapping("/movie-category/{moviecategory}")
//	public ResponseEntity<?> getMoviesByCategory(@PathVariable("moviecategory") String movieCategory){
//	    	
//    	  List<Movie> list = movieService.getMoviesByCategory(movieCategory);
//          return new ResponseEntity<List>(list, HttpStatus.OK);
//          
//	    }

}
