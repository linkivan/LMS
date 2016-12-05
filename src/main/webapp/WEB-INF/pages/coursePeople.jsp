<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>People</title>
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
						<h1 class="page-header">People</h1>
					</div>
				</div>

				<!-- ... Your content goes here ... -->
				<div>
					<div class="error"></div>
					<div class="msg"></div>
					<div id="people">
						<div class="form-group">
							<table class="table-hover table-striped table-border table w450">
								<tr>
									<th>User Name</th>
									<th>Role</th>
									<sec:authorize access="hasRole('ROLE_ADMIN')">
										<th>Action</th>
									</sec:authorize>
								</tr>
								<c:forEach var="instructor" items="${instructors}">
									<tr>
										<td>${instructor.username}</td>
										<td>${instructor.role}</td>
										<sec:authorize access="hasRole('ROLE_ADMIN')">
											<td><a
												href="javascript:formInstructorDelSubmit${instructor.id}()">Delete</a></td>
										</sec:authorize>
									</tr>
									<sec:authorize access="hasRole('ROLE_ADMIN')">
										<form
											action="<c:url value='/course/${id}/people/${instructor.id}'/>"
											method="post" id="deleteIntructorForm${instructor.id}">
											<input type="hidden" name="${_csrf.parameterName}"
												value="${_csrf.token}" /> <input type="hidden"
												name="_method" value="delete" />
										</form>

										<script>
										function formInstructorDelSubmit${instructor.id}() {
											document.getElementById(
													"deleteIntructorForm"+${instructor.id})
													.submit();
										}
									</script>
									</sec:authorize>
								</c:forEach>
								<c:forEach var="student" items="${students}">
									<tr>
										<td>${student.username}</td>
										<td>${student.role}</td>
										<sec:authorize access="hasRole('ROLE_ADMIN')">
											<td><a
												href="javascript:formStudentDelSubmit${student.id}()">Delete</a></td>
										</sec:authorize>
									</tr>
									<sec:authorize access="hasRole('ROLE_ADMIN')">
										<form
											action="<c:url value='/course/${id}/people/${student.id}'/>"
											method="post" id="deleteStudentForm${student.id}">
											<input type="hidden" name="${_csrf.parameterName}"
												value="${_csrf.token}" /> <input type="hidden"
												name="_method" value="delete" />
										</form>

										<script>
										function formStudentDelSubmit${student.id}() {
											document.getElementById(
													"deleteStudentForm"+${student.id})
													.submit();
										}
									</script>
									</sec:authorize>
								</c:forEach>
							</table>
						</div>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-2 mln">
									<a href="<c:url value='/course/${id}/people/new' />"
										type="submit" class="btn btn-danger">Add People</a>
								</div>
							</div>
						</sec:authorize>
					</div>
				</div>
			</div>

		</div>
	</div>
</body>
<%@ include file="common/footer.jsp"%>
</html>


