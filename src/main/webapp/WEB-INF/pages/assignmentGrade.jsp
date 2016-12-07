<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@page session="true"%>
<html>
<head>
<title>Add Assignment</title>
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
						<h1 class="page-header">${assignment.name} Grade </h1>
					</div>
				</div>

				<!-- ... Your content goes here ... -->
				<div id="search panel">
					<form class="form-horizontal" action="<c:url value='/course/${course.id}/assignment/${assignmentId}/student/${studentid}' />"
						method="POST">
						<c:if test="${not empty file}">
						<div class="form-group">
							<label for="inputcode" class="col-sm-2 control-label ">Response
							</label>
							<div class="col-sm-3">
								<a  class="col-sm-2 control-label " href="">assignment1_student1.doc</a>
							</div>
						</div>
						</c:if>
						<div class="form-group">
							<label  class="col-sm-2 control-label ">Grade</label>
							<div class="input-group col-sm-2 pl20">
								<input type="number" class="form-control" id="inputcode" name="grade"
									placeholder="Grade" value="${response.grade}"  max="${assignment.totalScore}" required>
									<span class="input-group-addon">/${assignment.totalScore}</span>


							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-3">
								<button type="submit" class="btn btn-primary ml20">Submit</button>
								<button onclick="history.back()" class="btn btn-primary ml20">Cancel</button>
							</div>
						</div>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>
				</div>
			</div>


		</div>
	</div>
</body>
<%@ include file="common/footer.jsp"%>
</html>