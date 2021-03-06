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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/MVCFilmSite/WebContent/index.html">
<style type="text/css">
body { 
    padding-top: 65px; 
}
blink {
        animation: blinker 0.6s linear infinite;
        color: #1c87c9;
       }
      @keyframes blinker {  
        50% { opacity: 0; }
       }
       .blink-one {
         animation: blinker-one 1s linear infinite;
       }
       @keyframes blinker-one {  
         0% { opacity: 0; }
       }
       .blink-two {
         animation: blinker-two 1.4s linear infinite;
       }
       @keyframes blinker-two {  
         100% { opacity: 0; }
       }
</style>
<title>Results</title>
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
		<%-- <c:when test="${empty film}">Film not Found</c:when> --%>

		<c:when test="${film.id == 0}">Film not inserted into database</c:when>
		<c:when test="${not empty film}">

	${film.title}<br>

			<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
			<div class="btn-group mr-2" role="group" aria-label="First group">
			<form method="get" action="editFilm.do">
				<button class="btn btn-secondary" type="submit">Edit Film
					Data</button>
				<input type="hidden" name="id" value="${film.id }">
			</form>
			</div>
			<div class="btn-group mr-2" role="group" aria-label="Second group">
			<form method="get" action="viewDetails.do">
				<button class="btn btn-success" type="submit">View Films
					Details</button>
				<input type="hidden" name="id" value="${film.id }">
			</form>
			</div>
			<div class="btn-group" role="group" aria-label="Third group">
			<form method="POST" action="deleteFilm.do">
				<button class="btn btn-danger" type="submit">Delete Film</button>
				<input type="hidden" name="id" value="${film.id }">
			</form>
			</div>
			</div>

		</c:when>
		<c:when test="${not empty filmList}">
			<ul>
				<c:forEach var="film" items="${filmList }">
					<li>${film.title }</li>
					<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
			<div class="btn-group mr-2" role="group" aria-label="First group">
			<form method="get" action="editFilm.do">
				<button class="btn btn-secondary" type="submit">Edit Film
					Data</button>
				<input type="hidden" name="id" value="${film.id }">
			</form>
			</div>
			<div class="btn-group mr-2" role="group" aria-label="Second group">
			<form method="get" action="viewDetails.do">
				<button class="btn btn-success" type="submit">View Films
					Details</button>
				<input type="hidden" name="id" value="${film.id }">
			</form>
			</div>
			<div class="btn-group" role="group" aria-label="Third group">
			<form method="POST" action="deleteFilm.do">
				<button class="btn btn-danger" type="submit">Delete Film</button>
				<input type="hidden" name="id" value="${film.id }">
			</form>
			</div>
			</div>
				</c:forEach>
			</ul>
		</c:when>
		<c:when test="${not empty deletedStatus}">
		${deletedStatus }
		</c:when>
		<c:otherwise><div class="text-center"><h1><blink>No Results</blink></h1></div></c:otherwise>
	</c:choose>
	
</body>
</html>