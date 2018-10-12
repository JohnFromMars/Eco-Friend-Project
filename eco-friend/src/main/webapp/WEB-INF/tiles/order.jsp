<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:url var="loginUrl" value="/login" />

<div class="row">

	<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">

		<div class="login-error">
			<form:errors path="order.*" />
		</div>

		<div class="panel panel-default">

			<div class="panel-heading">
				<div class="panel-title">
					<strong>Make an Order</strong>s
				</div>
			</div>

			<div class="panel-body">
				<form:form method="post" modelAttribute="order" class="login-form">

					<div class="input-group register-input">

						<span class="input-group-btn"></span>

					</div>

					<div>
						<form:input path="weight" type="text" class="form-control" placeholder="Weight" required="required" />
					</div>


					<div>
						<button class="btn btn-lrg btn-primary btn-block" type="submit">Order</button>
					</div>


				</form:form>
			</div>

		</div>
	</div>
</div>

