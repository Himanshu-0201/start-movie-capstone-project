package com.capstone.starMovie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.starMovie.exception.MovieAlreadyExistsException;
import com.capstone.starMovie.exception.MovieNotExistsException;
import com.capstone.starMovie.model.FavouriteMovie;
import com.capstone.starMovie.model.Movie;
import com.capstone.starMovie.repository.FavouriteMovieRespository;
import com.capstone.starMovie.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movierepository;

	@Autowired
	private FavouriteMovieRespository favMovieRepo;

	// to add movie
	@Override
	public Movie addMovie(Movie movie) throws MovieAlreadyExistsException {
		// TODO Auto-generated method stub

		Movie movie1;
		Optional<Movie> optionMovieById = movierepository.findById(movie.getMovieid());

		if (optionMovieById.isPresent()) {
			throw new MovieAlreadyExistsException("movie with same movieid is already exists in database!");
		} else {
			Movie existMovie = movierepository.findByMovienameIgnoreCase(movie.getMoviename());

			if (existMovie != null) {
				throw new MovieAlreadyExistsException("movie with same name is already exists in database!");
			} else {
				movie1 = movierepository.save(movie);

			}

		}

		return movie1;

	}

	// to get all movie
	@Override
	public List<Movie> getAllMovies() {
		// TODO Auto-generated method stub
		return movierepository.findAll();
	}

	// to update movie
	@Override
	public Movie updateMovie(Movie movie) throws MovieNotExistsException, MovieAlreadyExistsException {
		// TODO Auto-generated method stub
		Optional<Movie> optionMovie = movierepository.findById(movie.getMovieid());

		if (optionMovie.isPresent()) {

			Movie existMovie = movierepository.findByMovienameIgnoreCase(movie.getMoviename());

			// movie exist but same movie id that means it's the same movie that we are
			// updating
			if (existMovie != null && existMovie.getMovieid() != movie.getMovieid()) {
				throw new MovieAlreadyExistsException(
						"you can't update movie because movie is already present with same name");
			} else {

				// to update movie in favourite movie list
				Optional<FavouriteMovie> optionFavMovie = favMovieRepo.findByMovieid(movie.getMovieid());

				if (optionFavMovie.isPresent()) {
					FavouriteMovie existFavMovie = optionFavMovie.get();
					FavouriteMovie favMovie = new FavouriteMovie(existFavMovie.getFavId(), movie.getMoviename(),
							movie.getPoster(), movie.getRelaseDate(), movie.getMovieid());
					favMovieRepo.save(favMovie);
				}

				return movierepository.save(movie);
			}

		} else
			throw new MovieNotExistsException("movie doesn't exist in database");

	}

	// to delete movie by it's id
	@Override
	public Movie deleteMovie(String movieid) throws MovieNotExistsException {
		// TODO Auto-generated method stub
		Optional<Movie> optionMovie = movierepository.findById(movieid);

		// this method is used to delete movie from the favourite list also
		Optional<FavouriteMovie> optionFavMovie = favMovieRepo.findByMovieid(movieid);
		if (optionFavMovie.isPresent()) {
			FavouriteMovie favMovie = optionFavMovie.get();
			favMovieRepo.deleteById(favMovie.getFavId());
		}

		if (optionMovie.isPresent()) {
			movierepository.deleteById(movieid);
			return optionMovie.get();
		} else
			throw new MovieNotExistsException("movie doesn't exist in database");

	}

	// to get movie by it's name
//	@Override
//	public Movie getMovieByMovieName(String movieName) throws MovieNotExistsException {
//		// TODO Auto-generated method stub
//		
//		Movie movie1 = movierepository.findByMovienameIgnoreCase(movieName);
//		
//		if(movie1 == null) {
//			throw new MovieNotExistsException("movie doesn't exist in database"); 
//		}
//		else
//			return movie1;
//	}

	// to get movies by categegory;
//	@Override
//	public List<Movie> getMoviesByCategory(String movieCategory) {
//		// TODO Auto-generated method stub
//		return movierepository.findByCategoryIgnoreCase(movieCategory);
//		
//	}

}
