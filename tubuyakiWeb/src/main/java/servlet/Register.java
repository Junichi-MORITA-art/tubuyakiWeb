//新規作成した部品。ユーザー登録機能
//ID/パスの受け取り → Userオブジェクト化 → アプリケーションスコープ保存 → 結果JSPへ転送
package servlet;

import java.io.IOException;
import java.util.HashMap; // ★追加：ユーザー一覧を作るため
import java.util.Map;     // ★追加：複数ユーザーを「名前 → User」で管理するため

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.User;

@WebServlet("/Register")
public class Register extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    String name = request.getParameter("name"); //フォームの入力値を取り出し
    String pass = request.getParameter("pass");

    // ★追加：前後の空白を除去して、「空白だけ」の入力も未入力として扱いやすくする
    if (name != null) {
      name = name.trim();
    }
    if (pass != null) {
      pass = pass.trim();
    }

    // ★追加：ユーザー名またはパスワードが未入力なら登録失敗にする
    if (name == null || name.length() == 0 || pass == null || pass.length() == 0) {
      request.setAttribute("registered", false); // 登録失敗
      request.setAttribute("errorMsg", "ユーザー名とパスワードを入力してください");

      RequestDispatcher dispatcher =
          request.getRequestDispatcher("WEB-INF/jsp/registerResult.jsp");
      dispatcher.forward(request, response); //このサーブレットからregisterResult.jspへ処理を引き継ぐ
      return; // ここで処理終了
    }

    User registered = new User(name, pass); //User オブジェクトをつくる

    // ★追加：アプリケーションスコープから登録済みユーザー一覧を取得
    Map<String, User> registeredUsers =
        (Map<String, User>) getServletContext().getAttribute("registeredUsers");

    // ★追加：まだ一覧が存在しないなら新しく作る
    if (registeredUsers == null) {
      registeredUsers = new HashMap<>();
    }

    // ★追加：同じ名前のユーザーがすでにいるか確認
    if (registeredUsers.containsKey(name)) {
      request.setAttribute("registered", false); // 登録失敗
      request.setAttribute("errorMsg", "そのユーザー名はすでに使われています");

      RequestDispatcher dispatcher =
          request.getRequestDispatcher("WEB-INF/jsp/registerResult.jsp");
      dispatcher.forward(request, response); //このサーブレットからregisterResult.jspへ処理を引き継ぐ
      return; // ここで処理終了
    }

    // ★変更：1件だけ保存するのではなく、一覧に追加する
    registeredUsers.put(name, registered);

    // ★変更：アプリケーションスコープに一覧ごと保存
    getServletContext().setAttribute("registeredUsers", registeredUsers);

    request.setAttribute("registered", true); //リクエストスコープに保存

    RequestDispatcher dispatcher =
        request.getRequestDispatcher("WEB-INF/jsp/registerResult.jsp");
    dispatcher.forward(request, response); //このサーブレットからregisterResult.jspへ処理を引き継ぐ
  }
}