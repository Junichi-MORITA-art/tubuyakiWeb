//ログイン処理を行うモデル
//LoginLogic を「1234固定」から「登録済み照合」へ

package model;

import java.util.Map; // ★追加：複数ユーザーを名前付きで保存するため

import jakarta.servlet.ServletContext; // ★追加：アプリケーションスコープを使う

public class LoginLogic {
  public boolean execute(User inputUser, ServletContext application) {

    // アプリケーションスコープから「登録済みユーザー一覧」を取得
    Map<String, User> registeredUsers =
        (Map<String, User>) application.getAttribute("registeredUsers");
    // ★変更：registeredUser 1件ではなく、registeredUsers 一覧を取得する

    if (registeredUsers == null) return false;
    // まだユーザー一覧そのものが作られていないならログイン不可

    User registeredUser = registeredUsers.get(inputUser.getName());
    // 入力された名前をキーにして、その名前の登録済みユーザーを取り出す

    if (registeredUser == null) return false;
    // その名前のユーザーが存在しないならログイン不可

    return registeredUser.getPass().equals(inputUser.getPass());
    // パスワードが一致すればOK
  }
}