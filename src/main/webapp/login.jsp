<!DOCTYPE html>
<html>
  <body>
    <h2>Вход</h2>
    <form method="post" action="/login">
      <label for="username">Логин:</label><br>
      <input type="text" id="username" name="username"><br>
      <label for="password">Пароль:</label><br>
      <input type="password" id="password" name="password"><br><br>
      <input type="submit" value="Войти">
      <p>Нет аккаунта? <a href="/register.jsp">Регистрация</a></p>
    </form>
  </body>
</html>