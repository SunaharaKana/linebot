package com.example.linebot.value;

import com.example.linebot.replier.Intent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SummarySlot {

    private final String summary;

    public SummarySlot(String text){
        // Slotにあたる部分を取り出す正規表現の仕組み(Matcher)を作る
        String regexp = Intent.SUMMARY.getRegexp();
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(text);
        if (matcher.matches()){
            // テキストの中から都道府県名を抜き出す
            summary = matcher.group(1);
        }else {
            // 正規表現にマッチしない場合、実行時間例外をthrowする
            throw new IllegalArgumentException(
                    "text をスロットに分けらません");
        }
    }
}
