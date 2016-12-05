<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>Assignments Page</title>
</head>
<body>

	<h1>Assignments</h1>

	<div id="assignment-box">

		<h3>Add Assignment</h3>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

		<form name='assignmentForm'
			action="<c:url value='/assignments' />" method='POST'>

			<table>
				<tr>
					<td>Name:</td>
					<td><input type='text' name='name'></td>
				</tr>
				<tr>
					<td>Course ID:</td>
					<td><input type='text' name='courseId'></td>
				</tr>
				<tr>
					<td>Due date:</td>
					<td><input type='text' name='dueDate' /></td>
				</tr>
				<tr>
					<td>Status:</td>
					<td><input type='text' name='status' /></td>
				</tr>
				<tr>
					<td>Total score:</td>
					<td><input type='text' name='totalScore' /></td>
				</tr>
				<tr>
					<td>Weight:</td>
					<td><input type='text' name='weight' /></td>
				</tr>
				<tr>
					<td>File ID:</td>
					<td><input type='text' name='fileId' /></td>
				</tr>
				<tr>
					<td colspan='2'><input name="submit" type="submit"
						value="submit" /></td>
				</tr>
			</table>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
	</div>

</body>
</html>