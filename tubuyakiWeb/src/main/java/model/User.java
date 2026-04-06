//ユーザー名とパスワードを保存するクラス
//ユーザー名とパスワードを自由に決められる機能を追加
package model; 

import java.io.Serializable; // Userオブジェクトをセッションに保存したり転送したりできるようにするため

public class User implements Serializable {
// User という「ユーザー情報を入れて運ぶ箱」を作る。
// implements Serializable により、この箱をセッションに入れられるようにする

  private String name; // ユーザー名を保存するためのフィールド

  private String pass; // パスワードを保存するためのフィールド
 

  public User(String name, String pass) {
  // Userオブジェクトを作るときに呼ばれるコンストラクタ
  

    this.name = name; // 引数で受け取った name を name フィールドに入れる。
    this.pass = pass; // 引数で受け取った pass を pass フィールドに入れる。
  }

  public String getName() { return name; }// name を外から取り出すためのゲッター
  //private なので直接触れないので取り出し専用メソッドを使う

  public String getPass() { return pass; }  // pass を外から取り出すためのゲッター
}
