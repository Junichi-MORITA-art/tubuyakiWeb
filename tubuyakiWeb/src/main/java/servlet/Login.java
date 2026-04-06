//ログインリクエストを処理するコントローラ
//フォームから送られたID/パスを受け取り、アプリケーションスコープに保存された登録ユーザーと照合し、結果画面へ
package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.LoginLogic;
import model.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
	//Loginというクラスをつくる。サーブレットとして動くために HttpServlet を継承
  private static final long serialVersionUID = 1L; 
    //Javaがこのクラスを 保存したり読み戻したりするときに照合に使う番号

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException { //HttpServlet の doPost をオーバーライド

    request.setCharacterEncoding("UTF-8"); //受け取る文字コードをUTF-8に指定
    String name = request.getParameter("name");
    String pass = request.getParameter("pass"); //ログイン画面の名前とパスをとりだす

    User user = new User(name, pass);

    
    HttpSession session = request.getSession(); // セッション取得

    
    LoginLogic loginLogic = new LoginLogic(); // ログイン処理（登録済み情報と照合）

   
    // 登録済みユーザー（registeredUser）は「セッション」ではなく
    // 「アプリケーションスコープ（ServletContext）」に保存する
    // ログアウトでセッションが消えると登録情報まで消えて再ログインできなくなるため
    boolean isLogin = loginLogic.execute(user,this.getServletContext());

    if (isLogin) {
      session.setAttribute("loginUser", user); 
      //ログイン成功したらloginUser という名前で User をセッションに保存
    }

    RequestDispatcher dispatcher =
        request.getRequestDispatcher("WEB-INF/jsp/loginResult.jsp");
    dispatcher.forward(request, response); //サーブレット → JSP に処理を引き継いで結果表示
  }
}
