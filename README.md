# linebot
大学3年の時に作成した。

「○○について知りたい」と送るとwikipediaの文を要約したものが返ってくる。

授業の最終課題。授業の一環で使用したlinebotをそのまま使っているので、要約機能以外も持つ。

# 仕組み
・○○をキーワードとし、wikipediaから該当部分をスクレイピングする。BeautifulSoup
を用いている。そして文章部分だけを抽出する

・文章をpysummarizationを使用し、自動要約する

・要約文をJavaで返信させる

<img src="https://github.com/SunaharaKana/linebot/blob/master/IMG_3840.png" width="30%" />

結果
