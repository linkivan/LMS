<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>Courses</title>
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
						 <div class="form-group">
		 <div class="row">
                    <div class="col-lg-12 ">
                        <div class="panel panel-default ">
                            <div class="panel-heading ">
                                Hover Rows
                            </div>
                            <!-- /.panel-heading -->
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-hover  ">
                                        <thead>
                                            <tr>
                                                <th >Student</th>
												<th class = "mw30">Assignment 1</th>
                                                <th class = "mw30">Assignment 2</th>
                                                <th class = "mw30">Assignment 3</th>
												<th class = "mw30">Total Score</th>
												<th class = "mw30">Total</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
												<td>Garvit Hasija</td>
                                                <td><a href="Instructor-Grade Response.html">10/10</a></td>
                                                <td ><a href="Instructor-Grade Response.html">20/20</a></td>
                                                <td><a href="Instructor-Grade Response.html">50/50</a></td>
                                                <td>80/80</td>
												<td>100%</td>
												
                                            </tr>
                                            <tr>
                                                <td>Garvit Hasija</td>
                                                <td><a href="Instructor-Grade Response.html">10/10</a></td>
                                                <td ><a href="Instructor-Grade Response.html">20/20</a></td>
                                                <td><a href="Instructor-Grade Response.html">50/50</a></td>
                                                <td>80/80</td>
												<td>100%</td>
												
                                            </tr>
                                            <tr>
                                                <td>Garvit Hasija</td>
                                                <td><a href="Instructor-Grade Response.html">10/10</a></td>
                                                <td ><a href="Instructor-Grade Response.html">20/20</a></td>
                                                <td><a href="Instructor-Grade Response.html">50/50</a></td>
                                                <td>80/80</td>
												<td>100%</td>
												
                                            </tr>
											<tr>
                                                <td>Garvit Hasija</td>
                                                <td><a href="Instructor-Grade Response.html">10/10</a></td>
                                                <td ><a href="Instructor-Grade Response.html">20/20</a></td>
                                                <td><a href="Instructor-Grade Response.html">50/50</a></td>
                                                <td>80/80</td>
												<td>100%</td>
												
                                            </tr>
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


