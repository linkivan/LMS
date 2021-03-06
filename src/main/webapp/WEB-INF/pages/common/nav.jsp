
<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="navbar-header">
		<a class="navbar-brand" href="<c:url value='/courses' />">LMS
			TEAM2</a>
	</div>

	<button type="button" class="navbar-toggle" data-toggle="collapse"
		data-target=".navbar-collapse">
		<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
		<span class="icon-bar"></span> <span class="icon-bar"></span>
	</button>



	<!-- Top Navigation: Right Menu -->
	<ul class="nav navbar-right navbar-top-links">
		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
				${pageContext.request.userPrincipal.name} <b class="caret"></b>
		</a>
			<ul class="dropdown-menu dropdown-user">
				<li><a href="javascript:formSubmit()"><i
						class="fa fa-sign-out fa-fw"></i> Logout</a></li>
				<!-- For login user -->
				<c:url value="/j_spring_security_logout" var="logoutUrl" />
				<form action="${logoutUrl}" method="post" id="logoutForm">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
				<script>
					function formSubmit() {
						document.getElementById("logoutForm").submit();
					}
				</script>

			</ul></li>
	</ul>

	<!-- Sidebar -->
	<div class="navbar-default sidebar" role="navigation">
		<div class="sidebar-nav navbar-collapse">

			<ul class="nav" id="side-menu">
				<!-- <li class="sidebar-search">
					<div class="input-group custom-search-form">
						<input type="text" class="form-control" placeholder="Search...">
						<span class="input-group-btn">
							<button class="btn btn-primary" type="button">
								<i class="fa fa-search"></i>
							</button>
						</span>
					</div>
				</li> -->
				<li><a href="<c:url value='/courses' />" ><i
						class="fa fa-dashboard fa-fw"></i> Dashboard</a></li>
				<c:if test="${uiMenu.showCourse}">
					<li>
						<a><i class="fa fa-sitemap fa-fw"></i>
							${uiMenu.courseCode} <span class="fa arrow"></span>
						</a>
						<ul class="nav nav-second-level">
							<li>
								<a href="<c:url value='/course/${course.id}/information' />">INFORMATION</a>
							</li>
							<li>
								<a href="<c:url value='/course/${course.id}/people' />">PEOPLE</a>
							</li>
							<sec:authorize
								access="hasRole('ROLE_STU') or hasRole('ROLE_INSTR')">
								<li><a href="<c:url value='/course/${course.id}/assignments' />">ASSIGNMENT</a></li>
								<li><a href="<c:url value='/course/${course.id}/grades' />">GRADE</a></li>
								<li><a href="<c:url value='/course/${course.id}/syllabus' />">SYLLABUS</a></li>
							</sec:authorize>
						</ul>
					</li>
				</c:if>

			</ul>

		</div>
	</div>
</nav>