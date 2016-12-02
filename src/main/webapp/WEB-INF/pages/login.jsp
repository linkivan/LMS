<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>LMS - Login</title>
        <%@ include file="common/header.jsp" %>
    </head>
<body onload='document.loginForm.username.focus();'>

	<div id="login-box">
		<div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div class="login-panel panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Please Sign In</h3>
                        </div>
                        
                        <div class="panel-body">
	                        <c:if test="${not empty error}">
							<div class="error text-danger">${error}</div>
								</c:if>
							<c:if test="${not empty msg}">
								<div class="msg text-info">${msg}</div>
							</c:if>
                            <form role="form" name='loginForm'
							action="<c:url value='/j_spring_security_check' />" method='POST'>
                                <fieldset>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="Username" name="username" type="text" autofocus>
                                    </div>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="Password" name="password" type="password" value="">
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input name="remember" type="checkbox" value="Remember Me">Remember Me
                                        </label>
                                    </div>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                    <!-- Change this to a button or input when using this as a form -->
                                    <input name="submit" type="submit" value="Login" class="btn btn-lg btn-success btn-block">
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
		<%-- <form name='loginForm'
			action="<c:url value='/j_spring_security_check' />" method='POST'>

			<table>
				<tr>
					<td>User:</td>
					<td><input type='text' name='username'></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='password' /></td>
				</tr>
				<tr>
					<td colspan='2'><input name="submit" type="submit"
						value="submit" /></td>
				</tr>
			</table>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

		</form> --%>
	</div>
 <%@ include file="common/footer.jsp" %>
</body>
</html>