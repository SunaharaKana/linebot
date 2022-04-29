package com.example.linebot.replier;

import java.util.EnumSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Intent {

    //メッセージの正規表現パターンに対応するやり取り状態の定義
    REMINDER("^(\\d{1,2}):(\\d{1,2})に(.{1,32})$"),
    UNKNOUWN(".+");

    private final String regexp;

    private Intent(String regexp) {
        this.regexp = regexp;
    }

    //　メッセージからやり取り状態を判断
    public static Intent whichIntent(String text) {
        //すべてのIntent(REMINDER,UNKOWN)を取得
        EnumSet<Intent> set = EnumSet.allOf(Intent.class);
        //引数 textが、REMINDER,UNKNOWNのパターンに当てはまるかチェック
        // 当てはまったほうを戻り値とする
        for (Intent intent : set) {
            if (Pattern.matches(intent.regexp, text)) {
                return intent;
            }
        }
        return UNKNOUWN;
    }

    public String getRegexp() {
        return regexp;
    }

}
