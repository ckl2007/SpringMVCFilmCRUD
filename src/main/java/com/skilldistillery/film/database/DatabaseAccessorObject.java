package com.skilldistillery.film.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

@Component
public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private static final String user = "student";
	private static final String pass = "student";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		String sqltxt = "select film.id, film.title, film.description, film.release_year, language.name, film.language_id"
				+ ", film.rental_duration, film.rental_rate, film.length, film.replacement_cost, film.rating"
				+ ", film.special_features, category.name, film_category.category_id from film JOIN film_category on film.id = film_category.film_id "
				+ "JOIN language on film.language_id = language.id JOIN category on film_category.category_id = category.id "
				+ "where film.id = ?";
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement stmt = conn.prepareStatement(sqltxt);) {
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				film = new Film(); // Create the object
				// Here is our mapping of query columns to our object fields:
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setRelease_year(rs.getInt("release_year"));
				film.setRental_duration(rs.getInt("rental_duration"));
				film.setRental_rate(rs.getDouble("rental_rate"));
				film.setLength(rs.getInt("length"));
				film.setReplacement_cost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
				film.setSpecial_features(rs.getString("special_features"));
				film.setLanguage(rs.getString("language.name"));
				film.setCategory(rs.getString("category.name"));
				film.setLanguage_id(Integer.parseInt(rs.getString("language_id")));
				film.setCategory_id(Integer.parseInt(rs.getString("category_id")));
				film.setActors(findActorsByFilmId(filmId));
//				film.setLocationsWithCondition(inventoryMaps(film.getId()));
			}

		} catch (SQLException e) {
			System.err.println(e);
		}
		return film;
	}

	public Film findCreatedFilmById(int filmId) {
		Film film = null;
		String sqltxt = "select * from film where id = ?";
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement stmt = conn.prepareStatement(sqltxt);) {
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				film = new Film(); // Create the object
				// Here is our mapping of query columns to our object fields:
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setRelease_year(rs.getInt("release_year"));
				film.setRental_duration(rs.getInt("rental_duration"));
				film.setRental_rate(rs.getDouble("rental_rate"));
				film.setLength(rs.getInt("length"));
				film.setReplacement_cost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
				film.setSpecial_features(rs.getString("special_features"));
				film.setLanguage_id(rs.getInt("language_id"));
//				film.setLocationsWithCondition(inventoryMaps(film.getId()));
			}

		} catch (SQLException e) {
			System.err.println(e);
		}
		return film;
	}
	
//                  
	public int addFilm(Film film) {
		String sql = "insert into film (title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		int keyval = 0;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); // Start transaction
			PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			st.setString(1, film.getTitle());
			st.setString(2, film.getDescription());
			st.setInt(3, film.getRelease_year());
			st.setInt(4, film.getLanguage_id());
			st.setInt(5, film.getRental_duration());
			st.setDouble(6, film.getRental_rate());
			st.setInt(7, film.getLength());
			st.setDouble(8, film.getReplacement_cost());
			st.setString(9, film.getRating());
			st.setString(10, film.getSpecial_features());
			st.executeUpdate();

			ResultSet keys = st.getGeneratedKeys();
			if (keys.next()) {
				keyval = keys.getInt(1);
				int newFilmId = keys.getInt(1);
//                film.setId(newFilmId);
				List<Actor> actors = findActorsByFilmId(newFilmId);
				if (actors != null && actors.size() > 0) {
					sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
					st = conn.prepareStatement(sql);
					for (Actor actor : actors) {
						st.setInt(1, newFilmId);
						st.setInt(2, actor.getId());
						st.executeUpdate();
					}
				}
				if (film.getCategory_id() != 0) {
					sql = "INSERT INTO film_category (film_id, category_id) VALUES (?,?)";
					st = conn.prepareStatement(sql);
					st.setInt(1, newFilmId);
					st.setInt(2, film.getCategory_id());
					st.executeUpdate();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return keyval;
	}

	public List<Film> findFilmsByWord(String keyword) {
		Film film = null;
		List<Film> films = new ArrayList<>();
		String sqltxt = "select film.id, film.title, film.description, film.release_year, language.name"
				+ ", film.rental_duration, film.rental_rate, film.length, film.replacement_cost, film.rating"
				+ ", film.special_features, category.name from film JOIN film_category on film.id = film_category.film_id "
				+ "JOIN language on film.language_id = language.id JOIN category on film_category.category_id = category.id "
				+ "where title like ? or description like ?"; //OUTER IS THE SOLUTION: not learned yet
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement stmt = conn.prepareStatement(sqltxt);) {
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				film = new Film(); // Create the object
				// Here is our mapping of query columns to our object fields:
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setRelease_year(rs.getInt("release_year"));
				film.setRental_duration(rs.getInt("rental_duration"));
				film.setRental_rate(rs.getDouble("rental_rate"));
				film.setLength(rs.getInt("length"));
				film.setReplacement_cost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
				film.setSpecial_features(rs.getString("special_features"));
				film.setLanguage(rs.getString("language.name"));
				film.setCategory(rs.getString("category.name"));
//				film.setLocationsWithCondition(inventoryMaps(film.getId()));
				films.add(film);

			}

		} catch (SQLException e) {
			System.err.println(e);
		}
		return films;
	}
	public List<Film> findCreatedFilmsByWord(String keyword) {
		Film film = null;
		List<Film> films = new ArrayList<>();
		String sqltxt = "select film.id, film.title, film.description, film.release_year, language_id"
				+", film.rental_duration, film.rental_rate, film.length, film.replacement_cost, film.rating"
				+", film.special_features from film "
				+ "where title like ? or description like ?";
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement stmt = conn.prepareStatement(sqltxt);) {
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				film = new Film(); // Create the object
				// Here is our mapping of query columns to our object fields:
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setRelease_year(rs.getInt("release_year"));
				film.setRental_duration(rs.getInt("rental_duration"));
				film.setRental_rate(rs.getDouble("rental_rate"));
				film.setLength(rs.getInt("length"));
				film.setReplacement_cost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
				film.setSpecial_features(rs.getString("special_features"));
				film.setLanguage(rs.getString("language_id"));
//				film.setLocationsWithCondition(inventoryMaps(film.getId()));
				films.add(film);
			}
			
		} catch (SQLException e) {
			System.err.println(e);
		}
		return films;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		String sqltxt = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement stmt = conn.prepareStatement(sqltxt);) {
			stmt.setInt(1, actorId);
			ResultSet actorResult = stmt.executeQuery();
			if (actorResult.next()) {
				actor = new Actor(); // Create the object
				// Here is our mapping of query columns to our object fields:
				actor.setId(actorResult.getInt(1));
				actor.setFirstName(actorResult.getString(2));
				actor.setLastName(actorResult.getString(3));
			}

		} catch (SQLException e) {
			System.err.println(e);
		}
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		Actor actor = null;
		String sqltxt = "SELECT id, first_name, last_name from actor"
				+ " JOIN film_actor on actor.id = film_actor.actor_id where film_actor.film_id = ?";
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement stmt = conn.prepareStatement(sqltxt);) {
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				actor = new Actor(); // Create the object
				actor.setId(rs.getInt("id"));
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
				actors.add(actor);
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
		return actors;
	}

	@Override
	public void updateFilm(Film film) {

		boolean update = false;
		Connection conn = null;
		String sqltxt = "update film set title = ?, description = ?, release_year = ?, language_id = ?, rental_duration = ?"
				+ ", rental_rate = ?, length = ?, replacement_cost = ?, rating = ?, special_features = (?) where id = ?";
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); // Start transaction
			PreparedStatement st = conn.prepareStatement(sqltxt, Statement.RETURN_GENERATED_KEYS);

			st.setString(1, film.getTitle());
			st.setString(2, film.getDescription());
			st.setInt(3, film.getRelease_year());
			st.setInt(4, film.getLanguage_id());
			st.setInt(5, film.getRental_duration());
			st.setDouble(6, film.getRental_rate());
			st.setInt(7, film.getLength());
			st.setDouble(8, film.getReplacement_cost());
			st.setString(9, film.getRating());
			st.setString(10, film.getSpecial_features());
			st.setInt(11, film.getId());
			st.executeUpdate();

			ResultSet keys = st.getGeneratedKeys();

			if (keys.next()) {
				System.out.println("ID: " + keys.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public String deleteFilm(int filmId) {
		boolean deleteSuccess = false;
		Connection conn = null;
		String sqltxt = "DELETE FROM film_category WHERE film_id = ?";
		String sqltxt2 = "DELETE FROM film WHERE id = ?";
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); // Start transaction
			PreparedStatement st = conn.prepareStatement(sqltxt, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, filmId);
			st.executeUpdate();
			st = conn.prepareStatement(sqltxt2, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, filmId);
			st.executeUpdate();
			deleteSuccess = true;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			deleteSuccess = false;
		} finally {
			try {
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		String status = "Delete status: "+ deleteSuccess;
		return status;
	}


//	private Map<Integer, Map<Integer, String>> inventoryMaps(int filmId) {
//		Map<Integer, Map<Integer, String>> locsInventory = new HashMap<>();
//		Map<Integer, String> invIdMedia = new HashMap<>();
//		Set<Integer> storeSet = new HashSet<>();
//		int oldStore = 0;
//		String sqltxt = "SELECT * from inventory_item where film_id = ?";
//		try (Connection conn = DriverManager.getConnection(URL, user, pass);
//				PreparedStatement stmt = conn.prepareStatement(sqltxt);) {
//			stmt.setInt(1, filmId);
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				int invID = rs.getInt(1);
//				String mediaCond = rs.getString(4);
//				int storeID = rs.getInt(3);
//				if (storeSet.contains(storeID)) {
//					invIdMedia.put(invID, mediaCond);
//					oldStore = storeID;
//
//				} else {
//					locsInventory.put(oldStore, invIdMedia);
//					storeSet.add(storeID);
//					invIdMedia = new HashMap<>();
//					invIdMedia.put(invID, mediaCond);
//
//				}
//
//			}
//			locsInventory.put(oldStore, invIdMedia);
//
//		} catch (SQLException e) {
//			System.err.println(e);
//		}
//		locsInventory.remove(0);
//		return locsInventory;
//	}

//	@Override
//	public Map<String, Double> filmReplacementCost() {
//		double totCost = 0;
//		Map<String, Double> replacementList = new HashMap<>();
//		List<Double> replacementCostList = new ArrayList<>();
//		List<String> titleList = new ArrayList<String>();
//		String sqltxt = "select film.title, film.replacement_cost from film";
//		try (Connection conn = DriverManager.getConnection(URL, user, pass);
//				PreparedStatement stmt = conn.prepareStatement(sqltxt);) {
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				titleList.add(rs.getString("title"));
//				replacementCostList.add(rs.getDouble("replacement_cost"));
//			}
//			for (double cost : replacementCostList) {
//				for (String string2 : titleList) {
//					replacementList.put(string2, cost);
//					System.out.println(string2 + ": " + cost);
//				}
//				totCost += cost;
//			}
//			System.out.printf("Total replacement cost : $%.2f", totCost);
//		} catch (SQLException e) {
//			System.err.println(e);
//		}
//		return replacementList;
//	}
//
//	@Override
//	public void addActor(String fName, String lName) {
//		String sqltxt = "INSERT INTO actor(first_name, last_name) values(?,?)";
//		Connection conn = null;
//		try {
//			conn = DriverManager.getConnection(URL, user, pass);
//			PreparedStatement stmt = conn.prepareStatement(sqltxt, Statement.RETURN_GENERATED_KEYS);
//			stmt.setString(1, fName);
//			stmt.setString(2, lName);
//
//			conn.setAutoCommit(false); // Start transaction
//			int uc = stmt.executeUpdate();
//			ResultSet keys = stmt.getGeneratedKeys();
//
//			if (keys.next()) {
//				System.out.println("ID: " + keys.getInt(1));
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//			try {
//				conn.rollback();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		} finally {
//			try {
//				conn.commit();
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//	}
}
