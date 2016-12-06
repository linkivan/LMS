<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" 
           uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>Grades</title>
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

				<!-- ... Your content goes here ... -->
				<div class="form-group">
					<div class="row">
						<div class="col-lg-12 ">
							<div class="panel panel-default ">
								<!-- /.panel-heading -->
								<div class="panel-body">
									<div class="table-responsive">
										<table class="table table-hover  ">
											<thead>
												<tr>
													<th>Student</th>
													<c:forEach var="assignment" items="${assignments}">
														<th class="mw30">${assignment.name}</th>
													</c:forEach>
													<th class="mw30">Total Score</th>
													<th class="mw30">Total</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="gradeBookModel" items="${grades}">
													<tr>
														<td>${gradeBookModel.student.username }</td>
														<c:set var="total" value="${0}"/>
														<c:set var="score" value="${0}"/>
														<c:set var="final" value="${0}"/>
														<c:forEach var="assignment" items="${assignments}">
															<c:set var="showScore" value="${gradeBookModel.grades.containsKey(assignment.id)? gradeBookModel.grades[assignment.id].grade : 0 }" />
															<td><a href="Instructor-Grade Response.html">
															${showScore}/${assignment.totalScore}</a></td>
															<c:set var="total" value="${total + showScore }"/>
															<c:set var="score" value="${score + assignment.totalScore }"/>
														</c:forEach>
														<td>${total}/${score}</td>
														<td><fmt:formatNumber type="percent" maxIntegerDigits="3" value="${total/score}" /></td>
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

	</div>


</body>
<%@ include file="common/footer.jsp"%>
</html>


