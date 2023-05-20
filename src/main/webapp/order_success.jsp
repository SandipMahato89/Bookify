<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Success</title>
<%@include file="all_component/allCss.jsp"%>
</head>
<body style="background-color: #f0f1f2">
	<%@include file="all_component/navbar.jsp"%>

	<div class="container text-center mt-3">
		<i class="fas fa-check-circle fa-5x  text-success"></i>
		<h1>Thank You</h1>
		<h2>Your Order SuccessFully</h2>
		<h5>Within 7 days Your order will be Delivered in Your address</h5>

		<a href="index.jsp" class="btn btn-primary mt-3">Home</a> 
		<a href="user_order.jsp" class="btn btn-danger mt-3">View Order</a>
	</div>

</body>
</html>