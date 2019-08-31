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

<form:form action="createFilm.do" method="POST" modelAttribute="film">
		<form:label path="title">title:</form:label>
		<form:input path="title" />
		<form:errors path="title" />
		<br />
		<form:label path="description">description</form:label>
		<form:input path="description" />
		<form:errors path="description" />
		<br />
		<form:label path="language_id">language</form:label>
		<form:input path="language_id" />
		<form:errors path="language_id" />
		<br />
		<form:label path="release_year">release_year:</form:label>
		<form:input path="release_year" />
		<form:errors path="release_year" />
		<input type="submit" value="Submit" />
	</form:form>
	
</body>
</html>