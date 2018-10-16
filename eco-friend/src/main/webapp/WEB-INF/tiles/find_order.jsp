<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<c:url var="url" value="/viewstatus"></c:url>

<div class="row">
	<div class="col-md-8 col-md-offset-2">

		<c:forEach var="order" items="${page.content}">

			<c:url var="pickLink" value="/pick_order?id=${order.orderId}" />
			<c:url var="mapLink" value="https://www.google.com.au/maps/place/${order.provider.address}" />

			<div class="panel panel-default">

				<div class="panel-heading">
					<div class="panel-title">
						<fmt:formatDate pattern=" H :mm:ss   dd / MM / y" value="${order.added}" />
					</div>
				</div>

				<div class="panel-body">
					<div>The weight of this order is about ${order.weight} kg</div>

					<div>
						The order location is <a href="${mapLink}" target="_blank" class="btn btn-primary btn-sm">${order.provider.address} </a>
					</div>


					<div class="edit-link pull-right">

						<c:if test="${ order.sender == NULL && order.confirm == false}">
							<a class="btn btn-primary btn-sm" onclick="return confirm('Really want to pick the order?')" href="${pickLink}">Pick Order</a>
						</c:if>

					</div>


				</div>
			</div>

		</c:forEach>
	</div>
</div>
