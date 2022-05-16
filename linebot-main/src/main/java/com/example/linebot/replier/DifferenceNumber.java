package com.example.linebot.replier;

import com.example.linebot.value.CovidItem;
import com.example.linebot.value.CovidItemElement;

import java.util.List;

//追加
//新規感染者数を計算

public class DifferenceNumber {

    private List<CovidItemElement> list;

    public  DifferenceNumber(List<CovidItemElement> list){
        this.list = list;
    }

    public int calcAfter(){
        int afterNumber = list.get(0).getNpatients() - list.get(7).getNpatients();

        return afterNumber;
    }

    public int calcBefore(){
        int beforeNumber = list.get(7).getNpatients() - list.get(14).getNpatients();

        return beforeNumber;
    }


}
