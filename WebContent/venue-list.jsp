<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: #767676">
			<div>
				<a href="#" class="navbar-brand"> Venue Management</a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Venue</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Venue</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
					New Venue</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Address</th>
						<th>Contact Number</th>
						<th>Maximum Capacity</th>
						<th>Charge Per Hour</th>
						
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="venue" items="${listVenue}">

						<tr>
							<td><c:out value="${venue.id}" /></td>
							<td><c:out value="${venue.name}" /></td>
							<td><c:out value="${venue.address}" /></td>
							<td><c:out value="${venue.phone}" /></td>
							<td><c:out value="${venue.capacity}" /></td>
							<td><c:out value="${venue.charge}" /></td>
							<td><a href="edit?id=<c:out value='${venue.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; 
							<a href="delete?id=<c:out value='${venue.id}' />" title="delete" class="delete" onclick="return confirm('Are you sure you want to delete this item')">Delete</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>
