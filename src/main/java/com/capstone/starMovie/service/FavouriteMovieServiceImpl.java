package com.capstone.starMovie.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.starMovie.exception.FavouriteMovieNotExistsException;
import com.capstone.starMovie.exception.MovieAlreadyExistsException;
import com.capstone.starMovie.exception.MovieNotExistsException;
import com.capstone.starMovie.model.FavouriteMovie;
import com.capstone.starMovie.model.Movie;
import com.capstone.starMovie.repository.FavouriteMovieRespository;
import com.capstone.starMovie.repository.MovieRepository;

@Service
public class FavouriteMovieServiceImpl implements FavouriteMovieService {

	@Autowired
	FavouriteMovieRespository favMovRepo;

	@Autowired
	MovieRepository movieRepo;

	// to get all favourite movie
	@Override
	public List<FavouriteMovie> getAllFavouriteMovies() {
		// TODO Auto-generated method stub
		return favMovRepo.findAll();
	}

	// to add movie in favourite movie list
	@Override
	public FavouriteMovie addFavourite(String movieid) throws MovieNotExistsException, MovieAlreadyExistsException {

		Optional<Movie> optionalMovie = movieRepo.findById(movieid);
		Optional<FavouriteMovie> optionalFavMovie = favMovRepo.findByMovieid(movieid);
		FavouriteMovie favMovie1;

		if (optionalMovie.isPresent() == false) {

			throw new MovieNotExistsException("Movie doesn't exists in database");

		} else if (optionalFavMovie.isPresent()) {

			throw new MovieAlreadyExistsException("movie is already exits in favourite list");

		} else {
			Movie movie = optionalMovie.get();

			LocalDateTime currentTimestamp = LocalDateTime.now();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String formattedTimestamp = currentTimestamp.format(formatter);
			String favId = formattedTimestamp;

			favMovie1 = new FavouriteMovie(favId, movie.getMoviename(), movie.getPoster(), movie.getRelaseDate(),
					movie.getMovieid());
			return favMovRepo.save(favMovie1);
		}

	}

	// to delete movie from the favourite movie list
	@Override
	public FavouriteMovie deleteFavouriteMovieById(String movieid) throws FavouriteMovieNotExistsException {
		// TODO Auto-generated method stub
		Optional<FavouriteMovie> optionalMovie = favMovRepo.findByMovieid(movieid);

		if (optionalMovie.isPresent()) {
			FavouriteMovie favMovie = optionalMovie.get();
			favMovRepo.deleteById(favMovie.getFavId());
			return favMovie;
		} else
			throw new FavouriteMovieNotExistsException("Movie is not in favourite movie list");

	}

	// to delete all movie from the favourite movie list
	@Override
	public void deleteAllFavouriteMovie() {
		// TODO Auto-generated method stub
		favMovRepo.deleteAll();

	}

}
