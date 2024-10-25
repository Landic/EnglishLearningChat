<jsp:useBean id="order" scope="request" type="javafx.application.Preloader.ErrorNotification"/>
<%@ page import="itstep.learning.entities.MainContent" %>
<%@ page import="java.util.List" %>
<form method="post" action="${pageContext.request.contextPath}/api/order">
	<% MainContent content = (MainContent) request.getAttribute("content"); %>
	<input type="hidden" name="contentIds" value="<%= content.getId() %>">
	<label for="username">Ваш логин:</label>
	<input type="text" id="username" name="username" required>
	<input type="submit" value="Заказать">
</form>
<form action="order/update" method="post">
	<%--@declare id="newdetails"--%><input type="hidden" name="orderId" value="${order.id}">
	<label for="newDetails">Редактировать детали заказа:</label>
		<label><input type="text" name="newDetails" value="${order.details}"></label>
		<button type="submit">Обновить</button>
</form>
<form action="order/delete" method="post">
	<input type="hidden" name="orderId" value="${order.id}">
	<button type="submit" onclick="return confirm('Вы уверены, что хотите удалить заказ?')">Удалить</button>
</form>
<h2>Вас также может заинтересовать:</h2>
<ul>
	<% List<MainContent> similarContent = (List<MainContent>) request.getAttribute("similarContent"); %>
	<% for (MainContent similar : similarContent) { %>
	<li><a href="MainContentServlet?id=<%= similar.getId() %>"><%= similar.getTitle() %></a></li>
	<% } %>
</ul>