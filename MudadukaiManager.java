package model;

import java.util.ArrayList;
import java.util.List;

public class MudadukaiManager {
  private List<Mudadukai> items = new ArrayList<>();
  private int totalPrice = 0;

  // 無駄遣いの追加
  public void addExpense(String name, int price) {
    if (name != null && !name.trim().isEmpty() && price > 0) {
      items.add(new Mudadukai(name, price));
      totalPrice += price;
    }
  }

  public List<Mudadukai> getItems() {
    return items;
  }

  public int getTotalPrice() {
    return totalPrice;
  }

  // --- 脳汁出費の換算・判定ロジック ---
  public int getCount1() {
    return totalPrice / 500; // 牛丼1杯500円想定
  }

  public int getCount2() {
    return totalPrice / 15; // うまい棒1本15円想定
  }

  public int getCount3() {
    return totalPrice / 2000; // 洋服１着2000円想定
  }

  public String getGuiltMessage() {
    if (totalPrice == 0) {
      return "今日も賢者モード維持！素晴らしい！";
    } else if (totalPrice < 1000) {
      return "まだ軽傷です。でもチリツモですよ…？";
    } else if (totalPrice < 5000) {
      return "やってしまいましたね。財布が泣いています。";
    } else if (totalPrice < 30000) {
      return "【警告】脳汁が出すぎて金銭感覚がバグっています！";
    } else {
      return "🚨【大破産】今月のお米代が消えました。明日から水生活です。🚨";
    }
  }
}
