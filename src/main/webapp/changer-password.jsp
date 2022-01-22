<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<c:choose>
	<c:when
		test="${ !empty sessionScope.idClient && !empty sessionScope.code}">
		<html class="no-js" lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Réservations des salles</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">
<link href="https://fonts.googleapis.com/css?family=Play:400,700"
	rel="stylesheet">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/owl.carousel.css">
<link rel="stylesheet" href="css/owl.theme.css">
<link rel="stylesheet" href="css/owl.transitions.css">
<link rel="stylesheet" href="css/animate.css">
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/morrisjs/morris.css">
<link rel="stylesheet"
	href="css/scrollbar/jquery.mCustomScrollbar.min.css">
<link rel="stylesheet" href="css/metisMenu/metisMenu.min.css">
<link rel="stylesheet" href="css/metisMenu/metisMenu-vertical.css">
<link rel="stylesheet" href="css/calendar/fullcalendar.min.css">
<link rel="stylesheet" href="css/calendar/fullcalendar.print.min.css">
<link rel="stylesheet" href="css/form/all-type-forms.css">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="css/responsive.css">
<script src="js/vendor/modernizr-2.8.3.min.js"></script>
<style type="text/css">
* {
	font-family: 'Bahnschrift Light';
}
</style>
</head>

<body>
	<div class="error-pagewrap">
		<div class="error-page-int">
			<div class="text-center ps-recovered">
				<h3>RECUPERER MOT DE PASSE</h3>
				<p>Veuillez changer votre mot de passe.</p>
			</div>
			<div class="content-error">
				<div class="hpanel">
					<div class="panel-body poss-recover">
						<p>Entrez deux mot de passe identique.</p>
						<form action="login" method="post">
							<div class="form-group">
								<label class="control-label" for="password">Mot de passe</label>
								<input type="password" placeholder="*****" required value=""
									name="password" id="password" class="form-control">
							</div>
							<%
							if (request.getAttribute("mdpConfirmError") != null) {
							%>
							<div
								style="margin-top: -10px; margin-left: 3px; font-size: 13px; color: #ff0000c7; font-weight: 700;">Mot
								de passe incorrect</div>
							<%
							}
							%>
							<div class="form-group">
								<label class="control-label" for="password">Confirmation de mot de passe</label>
								<input type="password" placeholder="*****" required value=""
									name="passwordConfirm" id="passwordConfirm" class="form-control">
							</div>
							<%
							if (request.getAttribute("mdpConfirmError") != null) {
							%>
							<div
								style="margin-top: -10px; margin-left: 3px; font-size: 13px; color: #ff0000c7; font-weight: 700;">Mot
								de passe incorrect</div>
							<%
							}
							%>
							<button type="submit" class="btn btn-success btn-block"
								style="margin-top: 15px;">Enregistrer</button>
						</form>
					</div>
				</div>
			</div>
			<div class="text-center login-footer">
				<p>
					Copyright © 2018. All rights reserved. Template by <a
						href="https://colorlib.com/wp/templates/">Colorlib</a>
				</p>
			</div>
		</div>
	</div>
	<script src="js/vendor/jquery-1.12.4.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/wow.min.js"></script>
	<script src="js/jquery-price-slider.js"></script>
	<script src="js/jquery.meanmenu.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/jquery.sticky.js"></script>
	<script src="js/jquery.scrollUp.min.js"></script>
	<script src="js/scrollbar/jquery.mCustomScrollbar.concat.min.js"></script>
	<script src="js/scrollbar/mCustomScrollbar-active.js"></script>
	<script src="js/metisMenu/metisMenu.min.js"></script>
	<script src="js/metisMenu/metisMenu-active.js"></script>
	<script src="js/tab.js"></script>
	<script src="js/icheck/icheck.min.js"></script>
	<script src="js/icheck/icheck-active.js"></script>
	<script src="js/plugins.js"></script>
	<script src="js/main.js"></script>
	<script src="js/tawk-chat.js"></script>
</body>
		</html>
	</c:when>
	<c:otherwise>
		<%@include file="forms/error404.jsp"%>
	</c:otherwise>
</c:choose>