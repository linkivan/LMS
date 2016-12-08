<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>Grades Information</title>
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
						<h1 class="page-header">Grades</h1>
					</div>
				</div>
				<div class="error"></div>
				<div class="msg"></div>
				<div id="search panel">
					<form class="form-horizontal"
						action="<c:url value='/grades/1/1' />" method="get">
						<div class="form-group">
							<div class="row">
								<div class="col-lg-12 ">
									<div class="panel panel-default ">
										<!-- /.panel-heading -->
										<div class="panel-body">
											<div class="table-responsive">
											<c:if test="${not empty assignReses}">
												<table class="table table-hover table-striped ">
													<thead>
														<tr>
															<th class="mw30">Assignment</th>
															<th class="mw30">Submit time</th>
															<th class="mw30">Grade</th>
															
														</tr>
													</thead>
													<tbody>
														<c:forEach var="assignReses" items="${assignReses}">
															<tr>
																<td>${assignMap[assignReses.assignmentId]}</td>
																<td>${assignReses.submitTime}</td>
																<td>${assignReses.grade}</td>
																
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</c:if>
											<c:if test="${empty assignReses}">
												<div class="alert alert-danger">No Result found!</div>
											</c:if>
											</div>
											<!-- /.table-responsive -->
										</div>
										<!-- /.panel-body -->
									</div>
									<!-- /.panel -->
								</div>


							</div>
						</div>
					</form>
				</div>
			</div>
		</div>

		<!-- jQuery -->
		<script src="js/jquery.min.js"></script>

		<!-- Bootstrap Core JavaScript -->
		<script src="js/bootstrap.min.js"></script>

		<!-- Metis Menu Plugin JavaScript -->
		<script src="js/metisMenu.min.js"></script>

		<!-- Custom Theme JavaScript -->
		<script src="js/startmin.js"></script>

	</div>


</body>
<%@ include file="common/footer.jsp"%>
</html>


