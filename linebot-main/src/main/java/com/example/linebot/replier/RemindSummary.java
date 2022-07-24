package com.example.linebot.replier;

import com.example.linebot.value.SummaryItem;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

public class RemindSummary implements Replier{
    private final String msg;
    private final String msg2;

    public RemindSummary(String msg,String msg2){
        this.msg = msg;
        this.msg2 = msg2;
    }

    @Override
    public Message reply(){
        return new TextMessage(msg + "について説明します。\n" + msg2);
    }
}
