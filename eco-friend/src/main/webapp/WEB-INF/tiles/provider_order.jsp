<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<c:url var="url" value="/viewstatus"></c:url>

<div class="row">
	<div class="col-md-8 col-md-offset-2">



		<c:forEach var="order" items="${page.content}">

			<c:url var="editLink" value="/editstatus?id=${order.orderId}" />
			<c:url var="deleteLink" value="/deletestatus?id=${order.orderId}" />
			<c:url var="confirmLink" value="/confirm_order?id=${order.orderId}" />

			<div class="panel panel-default">

				<div class="panel-heading">
					<div class="panel-title">
						<fmt:formatDate pattern=" H :mm:ss   dd / MM / y" value="${order.added}" />
					</div>
				</div>

				<div class="panel-body">
					<div>The weight of this order is about ${order.weight} kg</div>
					<c:if test="${ order.sender != NULL}">
						<div>
							Your Sender is <a class="btn btn-primary btn-sm">${order.sender.firstName} ${order.sender.lastName}</a>
						</div>
					</c:if>

					<c:if test="${ order.sender != NULL && order.confirm == true && order.depot != NULL}">
						<div>
							Your Incentive is ${order.providerIncentive} dollar
						</div>
					</c:if>

					<div class="edit-link pull-right">

						<c:if test="${ order.sender == NULL && order.confirm == false}">
							<a class="btn btn-primary btn-sm" href="${editLink}">Edit</a>
							<a class="btn btn-primary btn-sm" onclick="return confirm('Really want to delete?')" href="${deleteLink}">Delete</a>
						</c:if>

						<c:if test="${ order.sender != NULL && order.confirm == false}">
							<a class="btn btn-primary btn-sm" onclick="return confirm('Really want to confirm?')" href="${confirmLink}">Confirm</a>
						</c:if>

						<c:if test="${ order.sender != NULL && order.confirm == true && order.depot == NULL}">
							<a class="btn btn-primary btn-sm">Confirmed</a>
						</c:if>

						<c:if test="${ order.sender != NULL && order.confirm == true && order.depot != NULL}">
							<a class="btn btn-primary btn-sm">Finish</a>
						</c:if>

					</div>


				</div>
			</div>

		</c:forEach>
	</div>
</div>
