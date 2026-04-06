<!-- 新規登録完了画面出力ビュー -->
<!-- 登録完了ページを自分で作成 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Boolean registered = (Boolean) request.getAttribute("registered");
// Registerサーブレットで request.setAttribute("registered", true/false) と入れた値を受け取る

String errorMsg = (String) request.getAttribute("errorMsg");
// Registerサーブレットで request.setAttribute("errorMsg", "...") と入れた値を受け取る
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録結果</title>
</head>
<body>
<h1>ユーザー登録</h1>

<% if (registered != null && registered) { %>
  <p>登録が完了しました。</p>
<% } else { %>
  <p>登録に失敗しました。</p>

  <% if (errorMsg != null) { %>
    <p><%= errorMsg %></p>
  <% } %>
<% } %>

<p><a href="<%= request.getContextPath() %>/">トップへ戻る</a></p>
</body>
</html>