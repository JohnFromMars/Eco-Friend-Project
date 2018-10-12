<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<c:url var="url" value="/viewstatus"></c:url>

<div class="row">
	<div class="col-md-8 col-md-offset-2">

		<c:forEach var="order" items="${page.content}">
	
			<c:url var="dropLink" value="/drop_order?id=${order.orderId}" />

			<div class="panel panel-default">

				<div class="panel-heading">
					<div class="panel-title">
						<fmt:formatDate pattern=" H :mm:ss   dd / MM / y" value="${order.added}" />
					</div>
				</div>

				<div class="panel-body">
					<div>The weight of this order is about ${order.weight} kg</div>

					<div>
						The order location is <a class="btn btn-primary btn-sm">${order.provider.address} </a>
					</div>


					<div class="edit-link pull-right">

						<c:if test="${ order.sender != NULL && order.confirm == false}">
							<a class="btn btn-primary btn-sm" onclick="return confirm('Really want to drop the order?')" href="${dropLink}">Drop Order</a>
						</c:if>

						<c:if test="${ order.sender != NULL && order.confirm == true}">
							<a class="btn btn-primary btn-sm">Confirmed</a>
						</c:if>

					</div>


				</div>
			</div>

		</c:forEach>
	</div>
</div>
