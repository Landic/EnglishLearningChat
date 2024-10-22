<%@ page import="itstep.learning.entities.MainContent" %>
<%@ page import="java.util.List" %>
<form method="post" action="/api/order">
	<% MainContent content = (MainContent) request.getAttribute("content"); %>
	<input type="hidden" name="contentIds" value="<%= content.getId() %>">
	<label for="username">Ваш логин:</label>
	<input type="text" id="username" name="username" required>
	<input type="submit" value="Заказать">
</form>
<h2>Вас также может заинтересовать:</h2>
<ul>
	<% List<MainContent> similarContent = (List<MainContent>) request.getAttribute("similarContent"); %>
	<% for (MainContent similar : similarContent) { %>
	<li><a href="MainContentServlet?id=<%= similar.getId() %>"><%= similar.getTitle() %></a></li>
	<% } %>
</ul>