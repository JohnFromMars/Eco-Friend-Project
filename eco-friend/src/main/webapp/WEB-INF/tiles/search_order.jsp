<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="search" value="/search_result"></c:url>

<div class="row">
	<div class="col-md-8 col-md-offset-2">
	
		<form method="GET" action="${search}">
			<div class="input-group input-group-lg">
				 <input type="text" class="form-control" name="s" placeHolder="Enter Sender's License Number"> <span class="input-group-btn">
					<button id="search-button" class="btn btn-primary" type="submit">Search Orders</button>
				</span>
			</div>
		</form>
	</div>
</div>