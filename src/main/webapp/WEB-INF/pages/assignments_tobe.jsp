<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>Assignments Page</title>
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
				
				<c:if test="${not empty error}">
					<div class="error">${error}</div>
				</c:if>
				<c:if test="${not empty msg}">
					<div class="msg">${msg}</div>
				</c:if>
				<div id="search panel">
					<form action="<c:url value='/assignments' />" method="get" class = "form-inline">
						<div  class = "form-group">
						<label for="code">Code: </label>
						<input type="text" id="code" name="code" class="form-control">
					</div>
					<div  class = "form-group ml20">
						<label for="code">Semester: </label>
						<select name="semester" id="semester" class="form-control" >
							<option value="Select">Select</option>
							<option value="Fall2016">Fall2016</option>
							<option value="Winter2016">Winter2016</option>
							<option value="Spring2017">Spring2017</option>
							<option value="Summer2017">Summer2017</option>
						</select>
					</div>
					<input class="btn btn-primary ml20" type="submit" value="Search Course">
					<a href="<c:url value='/assignments/new' />" class="btn btn-primary ml20">Add assignment</a>
					</form>
				</div>
				<c:if test="${ not empty courses}">
					<div id="assignments">
						<table class="table table-striped table-bordered table-hover">
							<tr>
								<th>Id</th>
								<th>Name</th>
								<th>Due data</th>
								<th>Total</th>
								<th>Weight</th>
							</tr>
							<c:forEach var="assignments" items="${assignments}">
							<tr>
								<td>${assignments.id}</td>
								<td>${assignments.name}</td>
								<td>${assignments.dueDate}</td>
								<td>${assignments.weight}</td>
								<td>
									<a href="<c:url value='/assignment/${assignments.id}' />">View</a>
								</td>
							</tr>
							<form action="<c:url value='/assignment/${assignments.id}'/>" method="post" id="deleteForm">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
								<input type="hidden" name="_method" value="delete" />  
							</form>
							
							<script>
								function formSubmit() {
									document.getElementById("deleteForm").submit();
								}
							</script>
							</c:forEach>
						</table>
					</div>
				</c:if>
				<c:if test="${empty courses}">
			        <div class="alert alert-danger">
	                   No assignments found!
	                </div>
	    		</c:if>
			</div>
		</div>

	</div>


</body>
<%@ include file="common/footer.jsp"%>
</html>