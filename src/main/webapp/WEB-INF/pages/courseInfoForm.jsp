<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>Courses Page</title>
</head>
<body>

	<h1>Courses</h1>

	<div id="course-box">

		<h3>Modify Course</h3>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

		<form name='courseForm'
			action="<c:url value='/course/${course.id}' />" method='POST'>
			<input type="hidden" name="_method" value="put" />  
			<table>
				<tr>
					<td>Code:</td>
					<td><input type='text' name='code' value='${course.code}'></td>
				</tr>
				<tr>
					<td>Semester:</td>
					<td>
						<select name="semester">
							<option value="Fall2016" ${"Fall2016" == course.semester ? 'selected' : ''}>Fall2016</option>
							<option value="Winter2016" ${"Winter2016" == course.semester ? 'selected' : ''}>Winter2016</option>
							<option value="Spring2017" ${"Spring2017" == course.semester ? 'selected' : ''}>Spring2017</option>
							<option value="Summer2017" ${"Summer2017" == course.semester ? 'selected' : ''}>Summer2017</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>time:</td>
					<td><input type='text' name='time'  value='${course.time}'/></td>
				</tr>
				<tr>
					<td>room:</td>
					<td><input type='text' name='room' value='${course.room}'/></td>
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