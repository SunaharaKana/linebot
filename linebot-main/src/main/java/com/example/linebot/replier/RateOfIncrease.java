package com.example.linebot.replier;

import com.example.linebot.value.CovidItemElement;

import java.util.List;

//追加
//増加率を計算

public class RateOfIncrease {

    private List<CovidItemElement> list;

    public RateOfIncrease(List<CovidItemElement> list){
        this.list = list;
    }

    public float rate(){
        DifferenceNumber differenceNumber = new DifferenceNumber(list);
        int after = differenceNumber.calcAfter();
        int before = differenceNumber.calcBefore();

        return ((float)after/(float) before) - 1;
    }


}
