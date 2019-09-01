package com.skilldistillery.film.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.film.database.DatabaseAccessorObject;

class DAOtests {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

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