<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>Courses</title>
<%@ include file="common/header.jsp"%>
</head>
<body>
	<div id="wrapper">
		<%@ include file="common/nav.jsp"%>
		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="container-fluid">

				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">Courses</h1>
					</div>
				</div>

				<!-- ... Your content goes here ... -->
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<c:if test="${not empty error}">
						<div class="error">${error}</div>
					</c:if>
					<c:if test="${not empty msg}">
						<div class="msg">${msg}</div>
					</c:if>
					<div id="search panel">
						<form action="<c:url value='/courses' />" method="get"
							class="form-inline">
							<div class="form-group">
								<label for="code">Code: </label> <input type="text" id="code"
									name="code" class="form-control" required>
							</div>
							<div class="form-group ml20">
								<label for="code">Semester: </label> <select name="semester"
									id="semester" class="form-control" required>
									<option value="">Select</option>
									<option value="Fall2016">Fall2016</option>
									<option value="Winter2016">Winter2016</option>
									<option value="Spring2017">Spring2017</option>
									<option value="Summer2017">Summer2017</option>
								</select>
							</div>
							<input class="btn btn-primary ml20" type="submit"
								value="Search Course"> <a
								href="<c:url value='/courses/new' />"
								class="btn btn-primary ml20">Add Course</a>
						</form>
					</div>
					<c:if test="${ not empty courses}">
						<div id="courses">
							<table class="table table-striped table-bordered table-hover">
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
										<td><a href="<c:url value='/course/${courses.id}/information' />">View</a>
											<a id="delete-course" href="javascript:formSubmit()">Delete</a>
										</td>
									</tr>
									<form action="<c:url value='/course/${courses.id}'/>"
										method="post" id="deleteForm">
										<input type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" /> <input type="hidden"
											name="_method" value="delete" />
									</form>

									<script>
										function formSubmit() {
											document.getElementById(
													"deleteForm").submit();
										}
									</script>
								</c:forEach>
							</table>
						</div>
					</c:if>
					<c:if test="${empty code and  empty semester}">
						<div class="alert alert-info">You can search or add a course. </div>
					</c:if>
					<c:if test="${empty courses and (not empty code or  not empty semester)}">
						<div class="alert alert-danger">No course found! Please change your parameters or add a course!</div>
					</c:if>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_INSTR') or hasRole('ROLE_STU')">
					<c:if test="${ not empty courses}">
						<c:forEach var="course" items="${courses}">
							<ul class="list-group">
							  <li class="list-group-item"><a href="<c:url value='/course/${course.id}/information' />">${course.code} - ${course.semester}</a></li>
							</ul>
						</c:forEach>
					</c:if>
					<c:if test="${ empty courses}">
						<div class="alert alert-danger">You have no courses now!</div>
					</c:if>
				</sec:authorize>
			</div>
		</div>

	</div>


</body>
<%@ include file="common/footer.jsp"%>
</html>


