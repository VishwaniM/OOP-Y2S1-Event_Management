<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Event Management Application</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	
</head>
<body>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: #767676">
			<div>
				<a href="https://" class="navbar-brand"> Event Management </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Venue</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5 ">
		<div class="card">
			<div class="card-body ">
				<c:if test="${venue != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${venue == null}">
					<form action="insert" method="post">
				</c:if>

				
					<h2>
						<c:if test="${venue != null}">
            			Edit Venue
            		</c:if>
						<c:if test="${venue == null}">
            			Add New Venue
            		</c:if>
					</h2>
			

				<c:if test="${venue != null}">
					<input type="hidden" name="id" value="<c:out value='${venue.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Venue Name</label> <input type="text"
						value="<c:out value='${venue.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Venue Address</label> <input type="text"
						value="<c:out value='${venue.address}' />" class="form-control"
						name="address" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Contact Number (format:xxx-xxxxxxx):</label> <input type="tel"
						value="<c:out value='${venue.phone}' />" class="form-control"
						name="phone" pattern="^\d{3}-\d{7}$" required>
						
				</fieldset>
				
				<fieldset class="form-group">
					<label>Maximum Capacity</label> <input type="number"
						value="<c:out value='${venue.capacity}' />" class="form-control"
						name="capacity" required="required" >
				</fieldset>
				
				<fieldset class="form-group">
					<label>Charge Per Hour</label> <input type="number"
						value="<c:out value='${venue.charge}' />" class="form-control"
						name="charge" required="required">
				</fieldset>
				

				<button type="submit" class="btn btn-outline-primary">Add Venue</button>
				
				
			</div>
		</div>
	</div>
</body>
</html>