<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>Courses</title>
<%@ include file="common/header.jsp"%>
</head>
<body>
	<div id="wrapper">
		<div id="page-wrapper">
			<div class="container-fluid">

				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">Courses</h1>
					</div>
				</div>

				<!-- ... Your content goes here ... -->
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<h2>Hello ${pageContext.request.userPrincipal.name}!</h2>
				</c:if>
				<c:if test="${not empty error}">
					<div class="error">${error}</div>
				</c:if>
				<c:if test="${not empty msg}">
					<div class="msg">${msg}</div>
				</c:if>
				<div id="search panel">
					<form action="<c:url value='/courses' />" method="get">
						Code: <input type="text" name="code" class="form-group"><br>
						Semester: <select name="semester" class="form-group">
							<option value="Fall2016">Fall2016</option>
							<option value="Winter2016">Winter2016</option>
							<option value="Spring2017">Spring2017</option>
							<option value="Summer2017">Summer2017</option>
						</select><br> <input type="submit" value="Search Course">
					</form>
					<a href="<c:url value='/courses/new' />">add course</a>
				</div>
				<c:if test="${ not empty courses}">
					<div id="courses">
						<table>
							<tr>
								<th>Id</th>
								<th>Code</th>
								<th>Semester</th>
								<th>Action</th>
							</tr>
							<c:forEach var="courses" items="${courses}">
								<tr>
									<td>${courses.id}</td>
									<td>${courses.code}</td>
									<td>${courses.semester}</td>
									<td><a href="<c:url value='/course/${courses.id}' />">View</a>
										<a id="delete-course"
										href="<c:url value='/course/${courses.id}'/>">Delete</a></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</c:if>
				<c:if test="${empty courses}">
		        No course found!
	    	</c:if>
			</div>
		</div>

	</div>

	<%@ include file="common/nav.jsp"%>
</body>
<%@ include file="common/footer.jsp"%>
</html>


