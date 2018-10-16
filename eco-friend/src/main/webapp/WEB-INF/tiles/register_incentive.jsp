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
					<strong>Incentive Detail</strong>s
				</div>
			</div>

			<div class="panel-body">
				<form:form method="post" modelAttribute="order" class="login-form">

					<div class="input-group register-input">

						<span class="input-group-btn"></span>

					</div>

					<div>
						${order.sender.firstName}<form:input path="totalContainerNo" type="number" class="form-control" placeholder="Total Container Number" required="required" />
					</div>

					<div>
						<form:input path="validContainerNo" type="number" class="form-control" placeholder="Valid Container Number" required="required" />
					</div>

					<div>
						<form:input path="totalIncentive" type="number" step="0.01" id="repeatPassword" class="form-control" placeholder="Total Incentive" required="required" />
					</div>

					<div>
						<button class="btn btn-lrg btn-primary btn-block" type="submit">Register Incentive</button>
					</div>


				</form:form>
			</div>

		</div>
	</div>
</div>

