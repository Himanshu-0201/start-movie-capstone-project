package com.capstone.starMovie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.starMovie.exception.MovieNotExistsException;
import com.capstone.starMovie.model.Movie;
import com.capstone.starMovie.repository.MovieRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private MovieRepository movierepository;

	// to get movie by movie name
	@Override
	public Movie getMovieByMovieName(String movieName) throws MovieNotExistsException {
		// TODO Auto-generated method stub

		Movie movie1 = movierepository.findByMovienameIgnoreCase(movieName);

		if (movie1 == null) {
			throw new MovieNotExistsException("movie doesn't exist in database");
		} else
			return movie1;

	}

	// to get movies by category;
	@Override
	public List<Movie> getMoviesByCategory(String movieCategory) {
		// TODO Auto-generated method stub
		return movierepository.findByCategoryIgnoreCase(movieCategory);

	}

}
