//お気に入り一覧を作るコントローラ
package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Mutter;
import model.User;

@WebServlet("/FavoriteList")
public class FavoriteList extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // ログイン確認
    HttpSession session = request.getSession();
    User loginUser = (User) session.getAttribute("loginUser");
    if (loginUser == null) {
      response.sendRedirect("index.jsp");
      return;
    }
    String userName = loginUser.getName();

    // つぶやき一覧取得
    ServletContext application = getServletContext();
    @SuppressWarnings("unchecked")
    List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");
    if (mutterList == null) {
      mutterList = new ArrayList<>();
      application.setAttribute("mutterList", mutterList);
    }

    // ★applicationから favoriteMap を取得し、このユーザーのお気に入りID集合を取る
    @SuppressWarnings("unchecked")
    Map<String, Set<Integer>> favoriteMap =
        (Map<String, Set<Integer>>) application.getAttribute("favoriteMap");

    Set<Integer> favoriteIds = null;
    if (favoriteMap != null) {
      favoriteIds = favoriteMap.get(userName);
    }

    // お気に入りだけ抽出
    List<Mutter> favoriteMutterList = new ArrayList<>();
    if (favoriteIds != null) {
      for (Mutter m : mutterList) {
        if (favoriteIds.contains(m.getId())) {
          favoriteMutterList.add(m);
        }
      }
    }
     //favorite.jsp に表示させるためのデータを渡して、画面に移動する
    request.setAttribute("favoriteMutterList", favoriteMutterList);

    RequestDispatcher dispatcher =
        request.getRequestDispatcher("WEB-INF/jsp/favorite.jsp");
    dispatcher.forward(request, response);
  }
}
