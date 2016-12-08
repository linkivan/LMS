<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@page session="true"%>
<html>
<head>
<title>Assignment Detail</title>
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
						<h1 class="page-header">Assignment Detail</h1>
					</div>
				</div>

				<!-- ... Your content goes here ... -->

				<div id="course-box">
					<c:if test="${not empty error}">
						<div class="error">${error}</div>
					</c:if>
					<c:if test="${not empty msg}">
						<div class="msg">${msg}</div>
					</c:if>
					<sec:authorize access="hasRole('ROLE_INSTR')">
						<form name='assignmentForm' class="form-horizontal"
							action="<c:url value='/course/${course.id}/assignment/${assignment.id}?${_csrf.parameterName}=${_csrf.token}' />"
							method='POST' enctype="multipart/form-data">
							<div class="form-group row">
								<label class="col-sm-2 control-label ">Name:</label>
								<div class="col-sm-3">
									<input type="text" class="form-control" id="name"
										value="${assignment.name }" placeholder="Name" name='name' required>
								</div>
							</div>
							<div class="form-group row">
								<label for="inputcode" class="col-sm-2 control-label ">Due
									Date:</label>
								<div class="col-sm-3" data-provide="datepicker">
									<input type="date" class="form-control"
										placeholder="mm/dd/yyyy" name='dueDate'
										value="${assignment.dueDate }" required>

								</div>
							</div>
							<div class="form-group row">
								<label for="inputPassword3" class="col-sm-2 control-label ">Status:
								</label>
								<div class="col-sm-3">
									<select name="status" id="status" class="form-control" required>
										<option value="Published"
											${"Published" == assignment.status ? 'selected' : ''}>Published</option>
										<option value="Unpublished"
											${"Unpublished" == assignment.status ? 'selected' : ''}>Unpublished</option>

									</select>
								</div>
							</div>
							<div class="form-group row">
								<label for="totalScore" class="col-sm-2 control-label ">Total
									Score:</label>
								<div class="col-sm-3">
									<input type="number" class="form-control" id="totalScore"
										name="totalScore" placeholder="Score"
										value="${assignment.totalScore }" required>
								</div>
							</div>
							<%-- <div class="form-group row">
								<label for="weight" class="col-sm-2 control-label ">Weight:</label>
								<div class="col-sm-3">
									<input type="text" class="form-control" id="weight"
										name="weight" placeholder="Percentage"
										value="${assignment.weight }">
								</div>
							</div> --%>
							<div class="form-group row">
								<label class="col-sm-2 control-label">Description:</label>
								<div class="col-sm-3">
									<textarea class="form-control" rows="3" id="description"
										name="description">${assignment.description}</textarea>
								</div>

							</div>
							<div class="form-group row">
								<label for="inputcode" class="col-sm-2 control-label ">File:</label>
								<div class="col-sm-5">
									<c:if test="${not empty fileModel}">
										<div class="col-sm-12">
											<a class="control-label"
												href="<c:url value='/course/${course.id}/files/${fileModel.fileName}?fileLocation=${fileModel.filePath}' />">${fileModel.fileName}</a>
										</div>
									</c:if>
									<div class="col-sm-12">
										<input type="file" class="form-control-file" name="fileUpload">
									</div>
								</div>
							</div>
							<div class="form-group row">
								<label for="submitBy" class="col-sm-2 control-label ">Submit
									By:</label>
								<div class="col-sm-3">
									<select name="submitBy" id="submitBy" class="form-control">
										<option value="File"
											${"File" == assignment.submitBy ? 'selected' : ''}>File</option>
										<option value="Email"
											${"Email" == assignment.submitBy ? 'selected' : ''}>Email</option>

									</select>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-sm-offset-2 col-sm-3">
									<button type="submit" class="btn btn-primary ml20">Submit</button>
									<button onclick="history.back()" class="btn btn-primary ml20">Cancel</button>
								</div>
							</div>
						</form>
					</sec:authorize>
					<sec:authorize access="hasRole('ROLE_STU')">
						<div id="search panel">
							<form class="form-horizontal">

								<div class="form-group">
									<label class="col-sm-2 control-label ">Name:</label>
									<div class="col-sm-3">
										<label class="control-label">${assignment.name}</label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label ">Due Date:</label>
									<div class="col-sm-3">
										<label class="control-label">${assignment.dueDate}</label>
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-2 control-label ">Total Score:</label>
									<div class="col-sm-3">
										<label class="control-label ">${assignment.totalScore}</label>
									</div>
								</div>
								<%-- <div class="form-group">
									<label class="col-sm-2 control-label ">Weight:</label>
									<div class="col-sm-3">
										<label class="control-label ">${assignment.weight}</label>
									</div>
								</div> --%>
								<div class="form-group">
									<label class="col-sm-2 control-label ">Description:</label>
									<div class="col-sm-3">
										<label class="control-label ">${assignment.description}</label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label ">File:</label>
									<c:if test="${not empty fileModel}">					
									<div class="col-sm-3">
										<a class="control-label" href="<c:url value='/course/${course.id}/files/${fileModel.fileName}?fileLocation=${fileModel.filePath}' />">${fileModel.fileName}</a>
									</div>
									</c:if>
									<c:if test="${ empty fileModel}">
									<div class="col-sm-3">
										<label class="control-label ">No File Attached</label>
									</div>					
										
									</c:if>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label ">Submit By:</label>
									<div class="col-sm-3">
										<label class="control-label ">${assignment.submitBy}</label>
									</div>
								</div>
								<div class="form-group">
									<div class="ml30 col-sm-1">
										<button onclick="history.back()" class="btn btn-primary ml20">Cancel</button>
									</div>
									<div class="col-sm-3">
										<a href="<c:url value='/course/${course.id}/assignment/${assignment.id}/response' />"" class="btn btn-primary ml20">Submit
											Assignment</a>
									</div>
								</div>
							</form>
						</div>

					</sec:authorize>
				</div>
			</div>
		</div>
	</div>
</body>
<%@ include file="common/footer.jsp"%>
</html>