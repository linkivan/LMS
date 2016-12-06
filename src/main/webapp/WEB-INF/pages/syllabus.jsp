<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>Syllabus</title>
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
						<h1 class="page-header">Syllabus</h1>
					</div>
				</div>

				<!-- ... Your content goes here ... -->
				<sec:authorize access="hasRole('ROLE_INSTR')">
					<div id="search panel">
						<form class="form-horizontal"
							action="<c:url value='/course/${course.id}/syllabus?${_csrf.parameterName}=${_csrf.token}' />"
							method="POST" enctype="multipart/form-data">
							<c:if test="${not empty syllabus}">
							<div class="form-group">
								<label for="inputcode" class="col-sm-2 control-label ">
									Current Syllabus </label>
								<div class="col-sm-3">
									<a class="col-sm-2 control-label " href="<c:url value='/course/${course.id}/files/${syllabus.fileName}?fileLocation=${syllabus.filePath}' />">${syllabus.fileName}</a><br />
								</div>
							</div>
							</c:if>
							<div class="form-group">
								<label for="inputcode" class="col-sm-2 control-label ">New Syllabus</label>
								<div class="col-sm-3">
									<input type="file" name="fileUpload">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-3">
									<button type="submit" class="btn btn-danger ml20">upload</button>
								</div>
							</div>
						</form>
					</div>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_STU')">
					<div id="search panel">
						<form class="form-horizontal" action="<c:url value='/courses' />"
							method="get">
							<c:if test="${not empty syllabus}">
							<div class="form-group">
								<div class="col-sm-3">
									<a class="col-sm-2 control-label " href="<c:url value='/course/${course.id}/files/${syllabus.fileName}?fileLocation=${syllabus.filePath}' />">${syllabus.fileName}</a>

								</div>
							</div>
							</c:if>
							<c:if test="${ empty syllabus}">
							No syllabus for this course yet!
							</c:if>
						</form>

					</div>
				</sec:authorize>
			</div>
		</div>

	</div>


</body>
<%@ include file="common/footer.jsp"%>
</html>


