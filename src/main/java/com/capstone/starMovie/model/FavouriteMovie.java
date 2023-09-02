package com.capstone.starMovie.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FavouriteMovie {
	@Id
	@Column(length = 20)
	private String favId;

	@Column(length = 50)
	private String moviename;
	@Column(length = 50)
	private String poster;
	@Column(length = 50)
	private String relaseDate;
	@Column(length = 20)
	private String movieid;

	public FavouriteMovie() {
		super();
	}

	public FavouriteMovie(String favId, String moviename, String poster, String relaseDate, String movieid) {
		super();
		this.favId = favId;
		this.moviename = moviename;
		this.poster = poster;
		this.relaseDate = relaseDate;
		this.movieid = movieid;
	}

	public String getFavId() {
		return favId;
	}

	public void setFavId(String favId) {
		this.favId = favId;
	}

	public String getMoviename() {
		return moviename;
	}

	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getRelaseDate() {
		return relaseDate;
	}

	public void setRelaseDate(String relaseDate) {
		this.relaseDate = relaseDate;
	}

	public String getMovieid() {
		return movieid;
	}

	public void setMovieid(String movieid) {
		this.movieid = movieid;
	}

}
