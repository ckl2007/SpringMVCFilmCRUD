<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search</title>
</head>
<body>
	<c:choose>
		<%-- <c:when test="${empty film}">Film not Found</c:when> --%>

		<c:when test="${film.id == 0}">Film not inserted into database</c:when>
		<c:when test="${not empty film}">

	${film}<br>
	<div class="btn-group" role="group">
			<form method="GET" action="viewDetails.do">
				<button class="btn btn-secondary" type="submit">View Films Details</button>
				<input type="hidden" name="id" value="${film.id }">
			</form>
			<form method="GET" action="editFilm.do">
				<button class="btn btn-secondary" type="submit">Edit Film Data</button>
				<input type="hidden" name="id" value="${film.id }">
			</form>
			<form method="POST" action="deleteFilm.do">
				<button class="btn btn-secondary" type="submit">Delete Film</button>
				<input type="hidden" name="id" value="${film.id }">
			</form>
			</div>
		</c:when>
		<c:when test="${not empty filmList}">
			<ul>
				<c:forEach var="film" items="${filmList }">
					<li>${film.title }</li>
					<div class="btn-group" role="group">
					<form method="get" action="editFilm.do">
						<button class="btn btn-secondary" type="submit">Edit Film Data</button>
						<input type="hidden" name="id" value="${film.id }">
					</form>
					<form method="get" action="viewDetails.do">
						<button class="btn btn-secondary" type="submit">View Films Details</button>
						<input type="hidden" name="id" value="${film.id }">
					</form>
					<form method="POST" action="deleteFilm.do">
						<button class="btn btn-secondary" type="submit">Delete Film</button>
						<input type="hidden" name="id" value="${film.id }">
					</form>
					</div>
				</c:forEach>
			</ul>
		</c:when>
		<c:when test="${deletion ==true }">
		Film Successfully Deleted
		Error in film deletetion
		</c:when>
	</c:choose>
</body>
</html>