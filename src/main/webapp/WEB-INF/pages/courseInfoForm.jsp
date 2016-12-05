<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@page session="true"%>
<html>
<head>
<title>Modify Course</title>
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
						<h1 class="page-header">Modify Course</h1>
					</div>
				</div>

				<div id="course-box">
					<c:if test="${not empty error}">
						<div class="error">${error}</div>
					</c:if>
					<c:if test="${not empty msg}">
						<div class="msg">${msg}</div>
					</c:if>

					<form name='courseForm' class="form-horizontal"
						action="<c:url value='/course/${course.id}' />" method='POST'>
						<input type="hidden" name="_method" value="put" />
						<div class="form-group">
							<label for="code" class="col-sm-2 control-label ">Code:</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" name='code' id="code"
									placeholder="Code" value='${course.code}'>
							</div>
						</div>
						<div class="form-group">
							<label for="semester" class="col-sm-2 control-label ">Semester</label>
							<div class="col-sm-3">
								<select name="semester" id="semester" class="form-control">
									<option value="Select">Select</option>
									<option value="Fall2016" ${"Fall2016" == course.semester ? 'selected' : ''}>Fall2016</option>
									<option value="Winter2016" ${"Winter2016" == course.semester ? 'selected' : ''}>Winter2016</option>
									<option value="Spring2017" ${"Spring2017" == course.semester ? 'selected' : ''}>Spring2017</option>
									<option value="Summer2017"${"Summer2017" == course.semester ? 'selected' : ''} >Summer2017</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="time" class="col-sm-2 control-label ">Time:</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="inputtime"
									name='time' placeholder="Time" value='${course.time}'>
							</div>
						</div>
						<div class="form-group">
							<label for="room" class="col-sm-2 control-label ">Room:</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="room" name='room'
									placeholder="Room" value='${course.room}'>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-3">
								<button type="submit" name="submit" class="btn btn-danger ml20">Submit</button>
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