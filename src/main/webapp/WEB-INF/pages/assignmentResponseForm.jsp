<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>Submit Assignment</title>
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
						<h1 class="page-header">Submit ${assignment.name }</h1>
					</div>
				</div>

				<!-- ... Your content goes here ... -->
				<div id="search panel">
					<form class="form-horizontal"
						action="<c:url value='/course/${course.id}/assignment/${assignment.id}/response?${_csrf.parameterName}=${_csrf.token}' />"
						method="POST" enctype="multipart/form-data">
						<c:if test="${not empty file}">
							<div class="form-group">
								<label  class="col-sm-2 control-label ">
									Current File </label>
								<div class="col-sm-10">
									<a class="control-label " href="<c:url value='/course/${course.id}/files/${file.fileName}?fileLocation=${file.filePath}' />">${file.fileName}</a><br />
								</div>
							</div>
						</c:if>
						<div class="form-group">
							<label for="inputcode" class="col-sm-2 control-label ">File</label>
							<div class="col-sm-10">
								<input type="file" class="form-control-file" name="fileUpload" required>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-3">
								<button onclick="history.back()" class="btn btn-primary ml20">Cancel</button>
								<button type="submit" class="btn btn-primary ml20">Submit</button>


							</div>
						</div>
					</form>
				</div>
			</div>
		</div>

	</div>


</body>
<%@ include file="common/footer.jsp"%>
</html>


