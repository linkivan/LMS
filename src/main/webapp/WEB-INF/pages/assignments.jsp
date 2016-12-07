<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@page session="true"%>
<html>
<head>
<title>Assignments</title>
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
						<h1 class="page-header">Assignments</h1>
					</div>
				</div>

				<!-- ... Your content goes here ... -->
				<div>
					<c:if test="${not empty error}">
						<div class="error">${error}</div>
					</c:if>
					<c:if test="${not empty msg}">
						<div class="msg">${msg}</div>
					</c:if>

					<div>
						<div id="courses" class="panel-heading">
							<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th>Assignments</th>
									<th>Due Date</th>
									<sec:authorize access="hasRole('ROLE_INSTR')">
									<th>Status</th>
									</sec:authorize>
									<th>Action</th>
								</tr>
								</thead>
								<tbody>
								<c:forEach var="assignment" items="${assignments}">
								
									<tr>
										<td>${assignment.name}</td>
										<td>${assignment.dueDate}</td>
										<sec:authorize access="hasRole('ROLE_INSTR')">
										<td>${assignment.status}</td>
										</sec:authorize>
										<td><a class="mr20"
											href="<c:url value='/course/${course.id}/assignment/${assignment.id}' />">View</a>
											<sec:authorize access="hasRole('ROLE_INSTR')">
												<a href="javascript:formAssignDelSubmit${assignment.id}()">Delete</a>
											</sec:authorize></td>

									</tr>
									
									<sec:authorize access="hasRole('ROLE_INSTR')">
										<form
											action="<c:url value='/course/${course.id}/assignment/${assignment.id}'/>"
											method="post" id="deleteAssignmentForm${assignment.id}">
											<input type="hidden" name="${_csrf.parameterName}"
												value="${_csrf.token}" /> <input type="hidden"
												name="_method" value="delete" />
										</form>

										<script>
										function formAssignDelSubmit${assignment.id}() {
											document.getElementById(
													"deleteAssignmentForm"+${assignment.id})
													.submit();
										}
									</script>
									</sec:authorize>
								</c:forEach>
								</tbody>
							</table>
						</div>
						<sec:authorize access="hasRole('ROLE_INSTR')">
							<div id="search panel">
								<div class="form-group">
									<div class="col-sm-3">
										<a
											href="<c:url value='/course/${course.id}/assignments/new' />"
											class="btn btn-primary ml20">Add Assignment</a>
									</div>
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