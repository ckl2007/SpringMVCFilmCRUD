package com.skilldistillery.film.database;

import java.util.List;
import java.util.Map;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

//With the Actor class implemented, uncomment the 
//two commented methods in the DatabaseAccessor interface.

public interface DatabaseAccessor {
	public Film findFilmById(int filmId);
	public List<Film> findFilmsByWord(String keyword);
	public Actor findActorById(int actorId);
	public List<Actor> findActorsByFilmId(int filmId);
	public void  addFilm(Film film);
//	public Map<String, Double> filmReplacementCost();
//	public void addActor(String fname, String lname);

}
