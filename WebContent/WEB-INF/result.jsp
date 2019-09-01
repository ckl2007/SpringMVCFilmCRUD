<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search</title>
</head>
<body>
	<c:choose>	
	<%-- <c:when test="${empty film}">Film not Found</c:when> --%>
	
	<c:when test="${film.id == 0}">Film not inserted into database</c:when>
		<c:when test="${not empty film}">

	${film}
	<form method="get" action="editFilm.do">
		<button type="submit">Edit Film Data</button>
		<input type="hidden" name="id" value="${film.id }">
	</form>
	<form method="get" action="deleteFilm.do">
		<button type="submit">Delete Film</button>
		<input type="hidden" name="id" value="${film.id }">
	</form>
		</c:when>
		<c:when test="${not empty filmList}">
			<ul>
				<c:forEach var="film" items="${filmList }">
					<li>${film.title }</li>
					<form method="get" action="editFilm.do">
						<button type="submit">Edit Film Data</button>
						<input type="hidden" name="id" value="${film.id }">
					</form>
					<form method="get" action="deleteFilm.do">
						<button type="submit">Delete Film</button>
						<input type="hidden" name="id" value="${film.id }">
					</form>

				</c:forEach>
			</ul>
		</c:when>
	</c:choose>
</body>
</html>