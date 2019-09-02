<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

* {
	box-sizing: border-box;
}

.form-inline {
	display: flex;
	flex-flow: row wrap;
	align-items: center;
}

.form-inline label {
	margin: 5px 10px 5px 0;
}

.form-inline input {
	vertical-align: middle;
	margin: 5px 10px 5px 0;
	padding: 10px;
	background-color: #fff;
	border: 1px solid #ddd;
}

.form-inline button {
	padding: 10px 20px;
	background-color: dodgerblue;
	border: 1px solid #ddd;
	color: white;
	cursor: pointer;
}

.form-inline button:hover {
	background-color: royalblue;
}

@media ( max-width : 800px) {
	.form-inline input {
		margin: 10px 0;
	}
	.form-inline {
		flex-direction: column;
		align-items: stretch;
	}
}
</style>
<title>Search</title>
</head>
<body>
	<%-- <c:if test="${empty film }"> --%>
	<form:form class="form-inline" action="createFilm.do" method="POST" modelAttribute="film">
		<input type='hidden' name='id' value='${film.id }'>
		<br>

		<form:label path="title">Title:</form:label>
		<form:input path="title" name="title" value="${film.title }" />
		<form:errors path="title" />
		<br />
		<div class="form-group">
		<form:label path="description">Description</form:label>
		<form:input path="description" value="${film.description }" />
		<form:errors path="description" />
		<br /></div>
		<form:label path="release_year">Release Year(1901 to 2155):</form:label>
		<form:input type="number" min='1901' max='2155' path="release_year"
			value="${film.release_year }" />
		<form:errors path="release_year" />
		<br />
		<div class="form-row align-items-center">
			<div class="form-group col-auto">
				<form:label path="language_id">Language</form:label>
				<br> <input type="radio" name="language_id" value="1"
					${language == '1' ? 'checked' : ''}>English <br>
				<input type="radio" name="language_id" value="2"
					${language == '2' ? 'checked' : ''}>Italian <br>
				<input type="radio" name="language_id" value="3"
					${language == '3' ? 'checked' : ''}>Japanese <br>
				<input type="radio" name="language_id" value="4"
					${language == '4' ? 'checked' : ''}>Mandarin <br>
				<input type="radio" name="language_id" value="5"
					${language == '5' ? 'checked' : ''}>French <br>
				<input type="radio" name="language_id" value="6"
					${language == '6' ? 'checked' : ''}>German 
				<form:errors path="language_id" />
			</div>

			<div class="form-group col-auto">
				<br> <br>
				<form:label path="rental_duration">Rental Duration(Days):</form:label>
				<form:input type="number" min='1' max='10' path="rental_duration"
					value="${film.rental_duration }" />
				<form:errors path="rental_duration" />
				<br>
				<form:label path="rental_rate">Rental Rate</form:label>
				<form:input type="number" min="0.00" step="0.01" path="rental_rate"
					value="${film.rental_rate }" />
				<form:errors path="rental_rate" />
				<br>
				<form:label path="length">Length</form:label>
				<form:input type="time" path="length" value="${film.length }" />
				<form:errors path="length" />
				<br>
				<form:label path="replacement_cost">Replacement Cost:</form:label>
				<form:input type="number" min="0.00" step="0.01"
					path="replacement_cost" value="${film.replacement_cost }" />
				<form:errors path="replacement_cost" />
			</div>
		</div>
				<form:label path="rating"> Rating:</form:label>
		<br /><select name='rating'>
			<option value="G">G</option>
			<option value="PG">PG</option>
			<option value="PG13">PG13</option>
			<option value="R">R</option>
			<option value="NC17">NC17</option>
			</datalist>
		</select>
		<br>
				<form:label path="replacement_cost">Category:</form:label>
		<select name='categories'>
			<option value="1" ${film.category_id == '1' ? 'checked' : ''}>Action</option>
			<option value="2" ${film.category_id == '2' ? 'checked' : ''}>Animation</option>
			<option value="3" ${film.category_id == '3' ? 'checked' : ''}>Children</option>
			<option value="4" ${film.category_id == '4' ? 'checked' : ''}>Classics</option>
			<option value="5" ${film.category_id == '5' ? 'checked' : ''}>Comedy</option>
			<option value="6" ${film.category_id == '6' ? 'checked' : ''}>Documentary</option>
			<option value="7" ${film.category_id == '7' ? 'checked' : ''}>Drama</option>
			<option value="8" ${film.category_id == '8' ? 'checked' : ''}>Family</option>
			<option value="9" ${film.category_id == '9' ? 'checked' : ''}>Foreign</option>
			<option value="10" ${film.category_id == '10' ? 'checked' : ''}>Games</option>
			<option value="11" ${film.category_id == '11' ? 'checked' : ''}>Horror</option>
			<option value="12" ${film.category_id == '12' ? 'checked' : ''}>Music</option>
			<option value="13" ${film.category_id == '13' ? 'checked' : ''}>New</option>
			<option value="14" ${film.category_id == '14' ? 'checked' : ''}>Sci-Fi</option>
			<option value="15" ${film.category_id == '15' ? 'checked' : ''}>Sports</option>
			<option value="16" ${film.category_id == '16' ? 'checked' : ''}>Travel</option>
			</datalist>
		</select>
		<form:label path="special_features">Special Features:</form:label>
		<form:input type="text" path="special_features"
			value="${film.special_features }" />
		<form:errors path="special_features" />
		<br>
		
		<input type="hidden" name="id" value="${film.id }">
		<button type='submit'>Submit</button>
	</form:form>
</body>
</html>