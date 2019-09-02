package com.skilldistillery.film.database;

import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

//With the Actor class implemented, uncomment the 
//two commented methods in the DatabaseAccessor interface.

public interface DatabaseAccessor {
	public Film findFilmById(int filmId);
	public List<Film> findFilmsByWord(String keyword);
	public Actor findActorById(int actorId);
	public List<Actor> findActorsByFilmId(int filmId);
	public int  addFilm(Film film);
//	public Map<String, Double> filmReplacementCost();
//	public void addActor(String fname, String lname);
	public void updateFilm(Film f);
	public Film findCreatedFilmById(int filmId);
	public List<Film> findCreatedFilmsByWord(String keyword);
	public Boolean deleteFilm(int filmID);
}
