<!-- お気に入り一覧出力ビュー -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="model.User,model.Mutter,java.util.List" %>
<%
User loginUser = (User) session.getAttribute("loginUser");
List<Mutter> favoriteMutterList =
  (List<Mutter>) request.getAttribute("favoriteMutterList");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>お気に入り</title>
</head>
<body>
<h1>お気に入り一覧</h1>

<%-- ログインしてないならトップへ --%>
<% if (loginUser == null) { %>
  <p>ログインしてください。<a href="index.jsp">戻る</a></p>
<% } else { %>
  <p><%= loginUser.getName() %>さん、ログイン中 <a href="Logout">ログアウト</a></p>
  <p><a href="Main">メインに戻る</a> | <a href="FavoriteList">更新</a></p>

  <% if (favoriteMutterList == null || favoriteMutterList.size() == 0) { %>
    <p>お気に入りはまだありません。</p>
  <% } else { %>
    <% for (Mutter mutter : favoriteMutterList) { %>
      <p>
        <%= mutter.getUserName() %>：<%= mutter.getText() %>
        <form action="Favorite" method="post" style="display:inline;">
          <input type="hidden" name="mutterId" value="<%= mutter.getId() %>">
          <input type="hidden" name="returnTo" value="FavoriteList">
          <button type="submit">★</button>
        </form>
      </p>
    <% } %>
  <% } %>
<% } %>

</body>
</html>
