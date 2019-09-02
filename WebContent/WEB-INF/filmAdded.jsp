<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Results</title>
<style type="text/css">
body { 
    padding-top: 65px; 
}
</style>
</head>

<nav class="navbar fixed-top navbar-light bg-light"> <a
	href="index.html"><button class="btn btn-primary">
		<i class="fa fa-home"></i> Home
	</button></a>
<form action="searchKeyword.do" method="GET">
	Search by keyword:<br> <input type="text" name="keyword"
		maxlength="10"> <input type="submit" value="Search" />
</form>
<form action="searchID.do" method="GET">
	Search by ID:<br> <input type="number" Min='1' name="IDsearch" required>
	<input type="submit" value="Search" />
</form>
<form action="creationForm.do" method="GET">
	<br> <input type="submit" value="Create New Film" />
</form>
<!-- <a class="navbar-brand" href="#">
    <img src="/docs/4.0/assets/brand/bootstrap-solid.svg" width="30" height="30" alt="">
  </a> --> </nav>

<body>

	<c:choose>
		<c:when test="${not empty film}">
	
	${film}  Film was added
	<div class="btn-group" role="group">
				<form method="get" action="viewDetails.do">
					<button class="btn btn-secondary" type="submit">View Films
						Details</button>
					<input type="hidden" name="id" value="${film.id }">
				</form>
				<form method="get" action="editFilm.do">
					<button class="btn btn-secondary" type="submit">Edit Film
						Data</button>
					<input type="hidden" name="id" value="${film.id }">
				</form>
				<form method="get" action="deleteFilm.do">
					<button class="btn btn-secondary" type="submit">Delete
						Film</button>
					<input type="hidden" name="id" value="${film.id }">
				</form>
			</div>
		</c:when>
		<c:when test="${empty film }">Film was not added</c:when>

	</c:choose>
</body>
</html>