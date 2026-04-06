<!-- つぶやき投稿閲覧メイン画面出力ビュー -->
<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8" %>
<%@ page import="model.User,model.Mutter,java.util.List,java.util.Set,java.util.Map" %>
<%
User loginUser = (User) session.getAttribute("loginUser"); 
// セッションスコープに保存されたユーザー情報を取得

List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");
//アプリケーションスコープに保存されたつぶやきリストを取得

String errorMsg = (String) request.getAttribute("errorMsg");
//リクエストスコープに保存されたエラーメッセージを取得

// ★変更：お気に入りはログアウト後も残したいので「セッション」ではなく「アプリケーションスコープ」に保存したものを使う
Map<String, Set<Integer>> favoriteMap = (Map<String, Set<Integer>>) application.getAttribute("favoriteMap");
// アプリケーションスコープに保存された「全ユーザー分のお気に入り情報」

Set<Integer> favoriteIds = null;
if (favoriteMap != null && loginUser != null) {
  favoriteIds = favoriteMap.get(loginUser.getName());
}
// ログインユーザーの「お気に入りIDの集合」を取り出す（なければnull）
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>つぶやきWebアプリケーション</title>
</head>
<body>
<h1>つぶやきWebアプリケーションメイン</h1>

<p>
<%= loginUser.getName() %>さん、ログイン中
<a href="Logout">ログアウト</a>
</p>

<p>
  <a href="Main">更新</a> |
  <a href="FavoriteList">お気に入りだけ表示</a>
</p>

<form action="Main" method="post">
  <input type="text" name="text">
  <input type="submit" value="つぶやく">
</form>

<% if(errorMsg != null){ %>
  <p><%= errorMsg %></p>
<% } %>

<% for(Mutter mutter : mutterList){ %>
  <p>
    <%= mutter.getUserName() %>：<%= mutter.getText() %>

    <!-- ★追加：お気に入り登録/解除ボタン -->
    <form action="Favorite" method="post" style="display:inline;">
      <input type="hidden" name="mutterId" value="<%= mutter.getId() %>">
      <input type="hidden" name="returnTo" value="Main"><!-- 押した後Mainに戻す -->

      <%
        boolean fav = (favoriteIds != null && favoriteIds.contains(mutter.getId()));
      %>
      <button type="submit"><%= fav ? "★" : "☆" %></button>
    </form>
  </p>
<% } %>

</body>
</html>
