<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<h1>Title : ${title}</h1>
	<h1>Message : ${message}</h1>
	
	<sec:authorize access="hasRole('ROLE_INSTR') or hasRole('ROLE_STU') or hasRole('ROLE_ADMIN')">
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

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h2>
				User : ${pageContext.request.userPrincipal.name} | <a
					href="javascript:formSubmit()"> Logout</a>
			</h2>
		</c:if>
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_STU')">
		<h2>
			Student View		
		</h2>
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<h2>
			Admin View		
		</h2>
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_INSTR')">
		<h2>
			Instructor View		
		</h2>
	</sec:authorize>
</body>
</html>