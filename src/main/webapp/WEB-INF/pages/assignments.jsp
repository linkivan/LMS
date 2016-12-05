<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>Assignments</title>
</head>
<body>
	<table>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Due data</th>
			<th>Total</th>
			<th>Weight</th>
		</tr>
		<c:forEach var="assignments" items="${assignemts}">
		<tr>
			<td>${assignments.id}</td>
			<td>${assignments.name}</td>
			<td>${assignments.dueDate}</td>
			<td>${assignments.weight}</td>
			<td>
				<a href="<c:url value='/assignment/${assignments.id}' />">View</a>
			</td>
		</tr>
		</c:forEach>
	</table>
</body>