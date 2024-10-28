<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
	<head><title>Личный кабинет</title></head>
	<body>
		<h1>Профиль пользователя</h1>
		<p>Имя пользователя: ${user.username}</p>
		<p>Email: ${user.email}</p>
		<p>Биография: ${user.bio}</p>
		<img src="${user.photoUrl}" alt="Фото профиля" />
	</body>
</html>