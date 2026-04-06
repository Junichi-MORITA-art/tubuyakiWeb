//つぶやき投稿の処理を行うモデル。つぶやきを「つぶやき一覧」に追加する
package model;

import java.util.List;

public class PostMutterLogic {

  // ★追加：つぶやきIDの連番（アプリが動いている間だけ有効）
  private static int nextId = 1;

  public void execute(Mutter mutter, List<Mutter> mutterList) { 
	  //追加したいつぶやき1件を、つぶやきが並んでいる一覧に追加

    // ★追加：受け取ったmutterをそのまま追加せず、ID付きに作り直す
    Mutter withId = new Mutter(nextId++, mutter.getUserName(), mutter.getText());

    mutterList.add(0, withId); // add(0, mutter)で先頭に追加
  }
}
