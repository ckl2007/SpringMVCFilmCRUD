<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search</title>
</head>
<body>

<form:form action="filmAdded.do" method="POST" modelAttribute="film">
		<input type='hidden' name='id' value='${film.id }'><br>
		
		<form:label path="title">Title:${film.title }</form:label>
		<form:input path="title" name="title" value="${film.title }"/>
		<form:errors path="title" />
		<br />
		<form:label path="description">Description</form:label>
		<form:input path="description" value="${film.description }"/>
		<form:errors path="description" />
		<br />
		<form:label path="release_year">Release_year:</form:label>
		<form:input type="number" path="release_year" value="${film.release_year }"/>
		<form:errors path="release_year" />
		<br />
		<form:label path="language_id">Language ID</form:label>
		<form:input type="number" path="language_id" value="${film.language_id }"/>
		<form:errors path="language_id" />
		<br>
		<form:label path="rental_duration">Rental Duration:</form:label>
		<form:input type="time" min='1' max='10' path="rental_duration" value="${film.rental_duration }"/>
		<form:errors path="rental_duration" />
		<br />
		<form:label path="rental_rate">Rental Rate</form:label>
		<form:input type="number" min="0.00" step="0.01" path="rental_rate" value="${film.rental_rate }"/>
		<form:errors path="rental_rate" />
		<br />
		<form:label path="length">Length</form:label>
		<form:input type="time" path="length" value="${film.length }"/>
		<form:errors path="length" />
		<br />
		<form:label path="replacement_cost">Replacement Cost:</form:label>
		<form:input type="number" min="0.00" step="0.01" path="replacement_cost" value="${film.replacement_cost }"/>
		<form:errors path="replacement_cost" />
		<br />
		<form:label path="rating">Rating</form:label>
		<form:input type="text" path="rating" value="${film.rating }"/>
		<form:errors path="rating" />
		<br />
		<form:label path="special_features">Special Features:</form:label>
		<form:input type="text" path="special_features" value="${film.special_features }"/>
		<form:errors path="special_features" />
		
		<input type="submit" value="Submit" />
	</form:form>

</body>
</html>