<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>Courses Page</title>
</head>
<body>

	<h1>Courses</h1>

	<div id="course-box">

		<h3>Add Course</h3>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

		<form name='courseForm'
			action="<c:url value='/courses' />" method='POST'>

			<table>
				<tr>
					<td>Code:</td>
					<td><input type='text' name='code'></td>
				</tr>
				<tr>
					<td>Semester:</td>
					<td><input type='text' name='semester' /></td>
				</tr>
				<tr>
					<td>time:</td>
					<td><input type='text' name='time' /></td>
				</tr>
				<tr>
					<td>room:</td>
					<td><input type='text' name='room' /></td>
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