package com.skilldistillery.film.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;


import com.skilldistillery.film.database.DatabaseAccessorObject;

class DAOtests {



	@Test
	void testFindFilmById() {
		DatabaseAccessorObject dao = new DatabaseAccessorObject();
		Film result = dao.findFilmById(1000);
		assertFalse(result.equals(null));

//		fail("Not yet implemented");
	}

	@Test
	void testFindCreatedFilmById() {
		DatabaseAccessorObject dao = new DatabaseAccessorObject();
		Film result = dao.findFilmById(1024);
		assertFalse(result.equals(null));

//		fail("Not yet implemented");
	}

	@Test
	void testAddFilm() {
		fail("Not yet implemented");
	}

//	@Test
//	void testFindFilmsByWord() {
//		DatabaseAccessorObject dao = new DatabaseAccessorObject();
//		List<Film> result = dao.findFilmsByWord("dino");
//		assertFalse(result.equals(null));
////		fail("Not yet implemented");
//	}

	@Test
	void testFindCreatedFilmsByWord() {
		DatabaseAccessorObject dao = new DatabaseAccessorObject();
		List<Film> result = dao.findCreatedFilmsByWord("dino");
		assertFalse(result.equals(null));
//		fail("Not yet implemented");
	}

	@Test
	void testFindActorById() {
		DatabaseAccessorObject dao = new DatabaseAccessorObject();
		Actor result = dao.findActorById(4);
		assertFalse(result.equals(null));
//		fail("Not yet implemented");
	}

	@Test
	void testFindActorsByFilmId() {
		DatabaseAccessorObject dao = new DatabaseAccessorObject();
		List<Actor> result = dao.findActorsByFilmId(4);
		assertFalse(result.equals(null));
//		fail("Not yet implemented");
	}

	@Test
	void testUpdateFilm() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteFilm() {
		DatabaseAccessorObject dao = new DatabaseAccessorObject();
		dao.deleteFilm(1025);

	}
}