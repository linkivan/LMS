<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>Course Information - ${course.code} ${course.semester}</title>
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
						<h1 class="page-header">${course.code}${course.semester}</h1>
					</div>
				</div>

				<!-- ... Your content goes here ... -->
				<div class="form-group">
					<table class="table-hover table-striped w350 table-border table">
						<tr>
							<th><label class="col-sm-2 control-label ">Code:</label></th>
							<td>${course.code}</td>
						</tr>
						<tr>
							<th><label class="col-sm-2 control-label ">Semester:</label></th>
							<td>${course.semester}</td>
						</tr>
						<%-- <tr>
							<th><label class="col-sm-2 control-label ">Instructor:</label></th>
							<td>${course.instructorId}</td>
						</tr> --%>
						<tr>
							<th><label class="col-sm-2 control-label ">Time:</label></th>
							<td>${course.time}</td>
						</tr>
						<tr>
							<th><label class="col-sm-2 control-label ">Room:</label></th>
							<td>${course.room}</td>
						</tr>
					</table>
					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-3">
								<a href="<c:url value='/course/${course.id}/info' />"
									class="btn btn-primary">Modify</a>
							</div>
						</div>
					</sec:authorize>
				</div>
			</div>
		</div>

	</div>


</body>
<%@ include file="common/footer.jsp"%>
</html>


