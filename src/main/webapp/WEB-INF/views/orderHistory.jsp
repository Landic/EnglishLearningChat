<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<title>Order History</title>
	</head>
	<body>
		<h2>Your Order History</h2>
		<table>
			<tr>
				<th>Order ID</th>
				<th>Content ID</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="order" items="${completedOrders}">
				<tr>
					<td>${order.id}</td>
					<td>${order.contentId}</td>
					<td>
						<form action="${pageContext.request.contextPath}/order-history" method="post">
							<input type="hidden" name="orderId" value="${order.id}"/>
							<button type="submit">Repeat Order</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>