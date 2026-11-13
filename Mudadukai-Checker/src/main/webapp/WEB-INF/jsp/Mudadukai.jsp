<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.MudadukaiManager" %>
<%@ page import="model.Mudadukai" %>
<%@ page import="java.util.List" %>
<%
    // セッションからマネージャーを取得
    MudadukaiManager manager = (MudadukaiManager) session.getAttribute("mudadukaiManager");
    
    // 初回アクセスなどでnullだった場合の安全対策
    if (manager == null) {
        manager = new MudadukaiManager();
    }
    
    List<Mudadukai> items = manager.getItems();
    int totalPrice = manager.getTotalPrice();
%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>今日の無駄遣い（脳汁出費）トラッカー</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<div class="container">
    <h1>💸 今日も脳汁出費しちゃいましたか？ 💸</h1>
    
    <!-- 入力フォーム（送信先をMudadukaiServletに設定） -->
    <form action="MudadukaiServlet" method="post">
        <div class="form-group">
            <label for="name">無駄遣いの内容（例: ガチャ、コンビニアイス）:</label>
            <input type="text" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="price">金額（円）:</label>
            <input type="number" id="price" name="price" required min="1">
        </div>
        <button type="submit">罪悪感を記録する</button>
    </form>

    <!-- 履歴一覧 -->
    <div class="history">
        <label>本日の無駄遣い履歴:</label>
        <ul>
            <% if (items == null || items.isEmpty()) { %>
                <li class="empty-message">まだ記録はありません（素晴らしい！）</li>
            <% } else { %>
                <% for (Mudadukai item : items) { %>
                    <li><%= item.getName() %> : <strong><%= item.getPrice() %>円</strong></li>
                <% } %>
            <% } %>
        </ul>
    </div>

    <!-- 罪悪感変換バナー -->
    <div class="footer-banner">
        <div class="total-price">
            総額: <%= totalPrice %> 円
        </div>
        <div class="conversion">
            牛丼 <b><%= manager.getGyudonCount() %></b>杯分 / 
            うまい棒 <b><%= manager.getUmaiboCount() %></b>本分 / 
            漫画 <b><%= manager.getMangaCount() %></b>冊分<br>
            <span class="guilt-message"><%= manager.getGuiltMessage() %></span>
        </div>
    </div>
</div>
</body>
</html>