<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>People</title>
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
						<h1 class="page-header">People</h1>
					</div>
				</div>

				<!-- ... Your content goes here ... -->
				<div>
					<c:if test="${not empty error}">
						<div class="error">${error}</div>
					</c:if>

					<div id="search panel">
						<form class="form-horizontal"
							action="<c:url value='/course/${id}/people/new' />" method="get">

							<div class="form-group">
								<div class="col-sm-10 form-inline mb25">
									<input type="text" class="form-control w200 ml25"
										id="inputsearch" name="username" placeholder="Search Here">
									<button type="submit" class="btn btn-danger ml20">Search</button>
								</div>
							</div>
						</form>

						<c:if test="${not empty user}">
							<c:if test="${not empty msg}">
								<div class="alert alert-danger">${msg}</div>
							</c:if>
							<form action="<c:url value='/course/${id}/people' />"
								method="post">
								<div class="form-group">
									<table
										class="table-hover table-striped w450 ml40 table-border table">
										<tr>
											<th>Name</th>
											<th>Role</th>
										</tr>
										<tr>
											<td>${user.username}</td>
											<td>${user.role}</td>
										</tr>
									</table>

								</div>
								<input type="hidden" name="userId" value="${user.id}" /> <input
									type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />

								<div class="form-group">
									<div class="col-sm-6">
										<a class="btn btn-danger ml20"
											href="<c:url value='/course/${id}/people' />">Cancel</a>

										<c:if test="${empty msg}">
											<button type="submit" class="btn btn-danger ml30">Add
												to Course</button>
										</c:if>

									</div>
								</div>
							</form>
						</c:if>
						<c:if test="${empty user}">
							<div class="alert alert-danger">No result found!</div>
							<div class="form-group">
								<div class="col-sm-6">
									<a class="btn btn-danger ml20"
										href="<c:url value='/course/${id}/people' />">Cancel</a>
								</div>
							</div>
						</c:if>

					</div>

				</div>
			</div>

		</div>
	</div>



</body>
<%@ include file="common/footer.jsp"%>
</html>


