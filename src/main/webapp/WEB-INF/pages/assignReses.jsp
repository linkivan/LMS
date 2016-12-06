<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>Grades Information - User: ${userId} Course: ${courseId}</title>
<%@ include file="common/header.jsp"%>
</head>
<body>
	<div id="wrapper">
		<%@ include file="common/nav.jsp"%>
		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="page-header"></div>
			<div class="error"></div>
			<div class="msg"></div>
			<div id="search panel">
				<form class="form-horizontal" action="<c:url value='/grades/1/1' />"
					method="get">
					<div class="form-group">
						<div class="row">
							<div class="col-lg-12 ">
								<div class="panel panel-default ">
									<div class="panel-heading ">Grades</div>
									<!-- /.panel-heading -->
									<div class="panel-body">
										<div class="table-responsive">
											<table class="table table-hover  ">
												<thead>
													<tr>
														<th class="mw30">Assignment id</th>
														<th class="mw30">Grade</th>
														<th class="mw30">Submit time</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="assignReses" items="${assignReses}">
														<tr>
															<td>${assignReses.assignmentId}</td>
															<td>${assignReses.grade}</td>
															<td>${assignReses.submitTime}</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
										<!-- /.table-responsive -->
									</div>
									<!-- /.panel-body -->
								</div>
								<!-- /.panel -->
							</div>


						</div>
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


