<%@ page import="itstep.learning.entities.MainContent" %>
<%@ page import="java.util.List" %>
<h2>Вас также может заинтересовать:</h2>
<ul>
	<% List<MainContent> similarContent = (List<MainContent>) request.getAttribute("similarContent"); %>
	<% for (MainContent content : similarContent) { %>
	<li><a href="MainContentServlet?id=<%= content.getId() %>"><%= content.getTitle() %></a></li>
	<% } %>
</ul>