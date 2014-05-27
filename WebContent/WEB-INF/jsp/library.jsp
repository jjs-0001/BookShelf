<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>図書検索</title>
</head>
<body>
    <h1>図書検索画面</h1>
    <p>検索したい本の名前を入力してください</p>
    <form method="post" action="Search">
    <input type="text" name="bookname">
    <input type="submit" value="検索">
    </form>
    <hr>
    <p>データベースに入れる本の情報を入力してください</p>
    <form method="post" action="Insert">
    本のタイトル<input type="text" name="title"><br>
    ISBN番号<input type="text" name="isbn"><br>
    著者名<input type="text" name="writer"><br>
    出版社<input type="text" name="publisher"><br>
    価格<input type="text" name="price"><br>
    <input type="submit" value="OK">
    </form>
</body>
</html>