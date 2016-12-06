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
						<h1 class="page-header">Assignment Grade</h1>
					</div>
				</div>

				<!-- ... Your content goes here ... -->
				<div id="search panel">
					<form class="form-horizontal" action="<c:url value='/courses' />"
						method="POST">

						<div class="form-group">
							<label for="inputcode" class="col-sm-2 control-label ">Response
							</label>
							<div class="col-sm-3">
								<a for="inputcode" class="col-sm-2 control-label " href="">assignment1_student1.doc</a>
							</div>
						</div>
						<div class="form-group">
							<label for="inputcode" class="col-sm-2 control-label ">Grade</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="inputcode" name="grade"
									placeholder="Grade">


							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-3">
								<button type="submit" class="btn btn-danger ml20">Submit</button>
								<button type="cancel" class="btn btn-danger ml20">Cancel</button>
							</div>
						</div>
					</form>
				</div>
			</div>


		</div>
	</div>
	</div>
</body>
<%@ include file="common/footer.jsp"%>
</html>