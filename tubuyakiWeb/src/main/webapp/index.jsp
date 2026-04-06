<!-- トップ画面出力のビュー -->
<!-- 新規登録ボタンを自分で追加 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>つぶやきWebアプリケーション</title>
</head>
<body>
<h1>つぶやきWebアプリケーションへようこそ</h1>

<form method="post">
<%-- ★変更：required を追加して、未入力のまま送信しにくくする --%>
ユーザー名：<input type="text" name="name" required><br>
パスワード：<input type="password" name="pass" required><br><br>

<!-- ログイン -->
<button type="submit" formaction="Login">ログイン</button>

<!-- 新規登録 -->
<button type="submit" formaction="Register">新規登録</button>
</form>

</body>
</html>