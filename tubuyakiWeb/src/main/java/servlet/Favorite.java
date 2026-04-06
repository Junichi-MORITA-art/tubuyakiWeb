// お気に入り登録/解除を行うコントローラ
package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.User;

@WebServlet("/Favorite")
public class Favorite extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");

    int mutterId = Integer.parseInt(request.getParameter("mutterId"));

    // どこへ戻るか（指定がなければMain）
    String returnTo = request.getParameter("returnTo");
    if (returnTo == null || returnTo.length() == 0) {
      returnTo = "Main";
    }

    // ログインユーザー取得　誰のお気に入りか
    HttpSession session = request.getSession();
    User loginUser = (User) session.getAttribute("loginUser");
    if (loginUser == null) {
      response.sendRedirect("index.jsp");
      return;
    }
    String userName = loginUser.getName();

    // ★お気に入りをアプリケーションに保存
    ServletContext application = getServletContext();

    @SuppressWarnings("unchecked")
    Map<String, Set<Integer>> favoriteMap =
        (Map<String, Set<Integer>>) application.getAttribute("favoriteMap");

    if (favoriteMap == null) {
      favoriteMap = new HashMap<>();
      application.setAttribute("favoriteMap", favoriteMap);
    }

    // ログインユーザーお気に入り集合を取得
    Set<Integer> favoriteIds = favoriteMap.get(userName);
    if (favoriteIds == null) {
      favoriteIds = new HashSet<>();
      favoriteMap.put(userName, favoriteIds);
    }

    // トグル（登録/解除）
    if (favoriteIds.contains(mutterId)) {
      favoriteIds.remove(mutterId);
    } else {
      favoriteIds.add(mutterId);
    }

    // 戻り先へ
    response.sendRedirect(returnTo);
  }
}
