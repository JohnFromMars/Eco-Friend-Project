<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Eco Friend</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- boostrap css -->
<link href="/css/bootstrap.css" rel="stylesheet">
<!-- echpo.css -->
<link href="/css/eco-friend.css" rel="stylesheet">
<!-- jquery include -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="/js/jquery-ui.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-default navbar-static-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/">Eco Friend</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="/">Home</a></li>
				<li><a href="/about">About Eco Friend</a></li>

				<sec:authorize access="hasRole('ROLE_SENDER')">
					<li><a href="/update_sender">Update</a></li>
					<li><a href="/find_order">Find</a></li>
					<li><a href="/sender_order">My Orders</a></li>
				</sec:authorize>

				<sec:authorize access="hasRole('ROLE_PROVIDER')">
					<li><a href="/update_provider">Update</a></li>
					<li><a href="/order">Order</a></li>
					<li><a href="/provider_order">My Orders</a></li>
				</sec:authorize>

				<sec:authorize access="hasRole('ROLE_DEPOT')">
					<li><a href="/update_depot">Update</a></li>
					<li><a href="/search_order">Search Order</a></li>
				</sec:authorize>


			</ul>
			<ul class="nav navbar-nav navbar-right">

				<sec:authorize access="!isAuthenticated()">
					<li><a href="/login">Login</a></li>
					<li><a href="/register">Register</a></li>
				</sec:authorize>

				<sec:authorize access="isAuthenticated()">
					<li><a href="javascript:$('#logoutForm').submit();">Logout</a></li>
				</sec:authorize>

			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
	</nav>


	<c:url var="logoutLink" value="/logout"></c:url>
	<form id="logoutForm" action="${logoutLink}" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>

	<tiles:insertAttribute name="content" />

	<!-- boostrap js lib-->
	<script src="/js/bootstrap.js"></script>
</body>
</html>