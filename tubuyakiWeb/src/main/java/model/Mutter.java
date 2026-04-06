//つぶやいたユーザー名と内容を保持するクラス
package model;
import java.io.Serializable;

public class Mutter implements Serializable {
  private int id; // ★追加：つぶやきID（どのつぶやきか区別する番号）
  private String userName; // ユーザー名
  private String text; // つぶやき内容
  public Mutter() { }

  // ★変更：idも受け取るようにする
  public Mutter(int id, String userName, String text) { //名前とつぶやきを表示
    this.id = id; // ★追加：受け取ったIDをthis.idに保存
    this.userName = userName; //受け取ったユーザー名をthis.userName に保存
    this.text = text; //受け取ったテキストを保存
  }

  public int getId() { return id; } // ★追加：privateのidを見るためのゲッター
  public String getUserName() { return userName; } //privateの userNameとtextを見るためのゲッター
  public String getText() { return text; }
}
