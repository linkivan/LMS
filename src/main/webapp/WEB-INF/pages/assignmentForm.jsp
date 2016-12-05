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
						<h1 class="page-header">Add Assignment</h1>
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

					<form name='assignmentForm' class="form-horizontal"
						action="<c:url value='/course/${course.id}/assignments' />"
						method='POST'>
						<div class="form-group row">
							<label  class="col-sm-2 control-label ">Name:</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="inputcode"
									placeholder="Name" name='name'>
							</div>
						</div>
						<div class="form-group row">
							<label for="inputcode" class="col-sm-2 control-label ">Due
								Date:</label>
							<div class="col-sm-3" data-provide="datepicker">
								<input type="date" class="form-control" placeholder="mm/dd/yyyy" name='dueDate'>

							</div>
						</div>
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-2 control-label ">Status:
							</label>
							<div class="col-sm-3">
								<select name="status" id="semester" class="form-control">
									<option value="Published">Published</option>
									<option value="Unpublished">Unpublished</option>

								</select>
							</div>
						</div>
						<div class="form-group row">
							<label for="totalScore" class="col-sm-2 control-label ">Total
								Score:</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="totalScore" name="totalScore"
									placeholder="Score">
							</div>
						</div>
						<div class="form-group row">
							<label for="weight" class="col-sm-2 control-label ">Weight:</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="weight" name="weight"
									placeholder="Percentage">
							</div>
						</div>
						<div class="form-group row" >
							<label class="col-sm-2 control-label">Description:</label>
							<div class="col-sm-3">
								<textarea class="form-control" rows="3" id="description" name="description"></textarea>
							</div>
							
						</div>
						<div class="form-group row">
							<label for="inputcode" class="col-sm-2 control-label ">File:</label>
							<div class="col-sm-3">
								<input type="file" class="form-control-file">
							</div>
						</div>
						<div class="form-group row">
							<label for="submitBy" class="col-sm-2 control-label ">Submit
								By:</label>
							<div class="col-sm-3">
								<select name="submitBy" id="submitBy" class="form-control">
									<option value="File">File</option>
									<option value="Email">Email</option>

								</select>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-sm-offset-2 col-sm-3">
								<button type="submit" class="btn btn-danger ml20">Submit</button>
								<button type="button" class="btn btn-danger ml20" name="Cancel"
									onclick="history.back()">Cancel</button>
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