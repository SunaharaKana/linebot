package com.example.linebot.replier;

import com.example.linebot.value.CovidItem;
import com.example.linebot.value.CovidItemElement;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

import java.util.List;

//追加
//増加率に関してreplier

public class CovidRate implements Replier{

    private static final String MESSAGE_FORMAT =
            "%s の 新規感染者増加率は %f です";
    
    private final CovidItem item;
    
    public CovidRate(CovidItem item){
        this.item = item;
    }
    
    @Override
    public Message reply(){
        String body = "データがありません";
        List<CovidItemElement> list = item.getItemList(); //(1)
        if(list.size() > 0) {
            body = getMessageOfLast();
        }
        return new TextMessage(body);
    }

    private String getMessageOfLast() {
        List<CovidItemElement> list = item.getItemList();

        String region = list.get(0).getNameJp();

        RateOfIncrease rateOfIncrease = new RateOfIncrease(list);
        float rate = rateOfIncrease.rate();

        return String.format(
                MESSAGE_FORMAT,region,rate);
    }
}
