<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search</title>
</head>
<body>
	<%-- <c:if test="${empty film }"> --%>
	<form:form action="createFilm.do" method="PUT" modelAttribute="film">
		<input type='hidden' name='id' value='${film.id }'>
		<br>

		<form:label path="title">Title:${film.title }</form:label>
		<form:input path="title" name="title" value="${film.title }" />
		<form:errors path="title" />
		<br />
		<form:label path="description">Description</form:label>
		<form:input path="description" value="${film.description }" />
		<form:errors path="description" />
		<br />
		<form:label path="release_year">Release Year(1901 to 2155):</form:label>
		<form:input type="number" min='1901' max='2155' path="release_year"
			value="${film.release_year }" />
		<form:errors path="release_year" />
		<br />
		<form:label path="language_id">Language</form:label>
		<br>
		<input type="radio" name="language_id" value="1" checked> English<br>
		<input type="radio" name="language_id" value="2"> Italian<br>
		<input type="radio" name="language_id" value="3"> Japanese<br>
		<input type="radio" name="language_id" value="4"> Mandarin<br>
		<input type="radio" name="language_id" value="5"> French<br>
		<input type="radio" name="language_id" value="6"> German<br>
		<form:errors path="language_id" />
		<br>
		<form:label path="rental_duration">Rental Duration:</form:label>
		<form:input type="time" min='1' max='100' path="rental_duration"
			value="${film.rental_duration }" />
		<form:errors path="rental_duration" />
		<br />
		<form:label path="rental_rate">Rental Rate</form:label>
		<form:input type="number" min="0.00" step="0.01" path="rental_rate"
			value="${film.rental_rate }" />
		<form:errors path="rental_rate" />
		<br />
		<form:label path="length">Length</form:label>
		<form:input type="time" path="length" value="${film.length }" />
		<form:errors path="length" />
		<br />
		<form:label path="replacement_cost">Replacement Cost:</form:label>
		<form:input type="number" min="0.00" step="0.01"
			path="replacement_cost" value="${film.replacement_cost }" />
		<form:errors path="replacement_cost" />
		<%-- <br />
		<form:label path="rating">Rating</form:label>
		<form:input type="text" path="rating" value="${film.rating }" />
		<form:errors path="rating" />
		<br /> --%>
		<br>
		<!-- <input type="hidden" list="rating" name="rating"> -->
		Rating:<select name='rating'>
		<option value="G">G</option>
		<option value="PG">PG</option>
		<option value="PG13">PG13</option>
		<option value="R">R</option>
		<option value="NC17">NC17</option>
		</datalist>
		</select>
		<br>
		
		
		<%-- <form:label path="special_features">Special Features:</form:label>
		<form:input type="text" path="special_features"
			value="${film.special_features }" />
		<form:errors path="special_features" />
		<input type="hidden" name="id" value="${film.id }"> --%>

		<input type="hidden" list="categories" name="categories">
		Category:<select name='categories'>
		<option value="1">Action</option>
		<option value="2">Animation</option>
		<option value="3">Children</option>
		<option value="4">Classics</option>
		<option value="5">Comedy</option>
		<option value="6">Documentary</option>
		<option value="7">Drama</option>
		<option value="8">Family</option>
		<option value="9">Foreign</option>
		<option value="10">Games</option>
		<option value="11">Horror</option>
		<option value="12">Music</option>
		<option value="13">New</option>
		<option value="14">Sci-Fi</option>
		<option value="15">Sports</option>
		<option value="16">Travel</option>
		</datalist>
		</select>
		<br>
		
		<input type="checkbox" name='special_features' value='Trailers'>Trailers
		<input type="checkbox" name='special_features' value='Commentaries'>Commentaries
		<input type="checkbox" name='special_features' value='Deleted Scenes'>Deleted Scenes
		<input type="checkbox" name='special_features' value='Behind the Scenes'>Behind the Scenes
		<br>
		
		
		
		
		<input type="submit" value="Submit" />
	</form:form>
</body>
</html>