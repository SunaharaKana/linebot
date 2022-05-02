package com.example.linebot.service;


import com.example.linebot.replier.RemindOn;
import com.example.linebot.value.ReminderSlot;
import com.example.linebot.value.Reminderltem;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import org.springframework.stereotype.Service;


@Service
public class ReminderService {

    public RemindOn doReplyOfNewItem(MessageEvent<TextMessageContent> event){
        String userId = event.getSource().getUserId();
        TextMessageContent tmc = event.getMessage();
        String text = tmc.getText();
        ReminderSlot slot = new ReminderSlot(text);
        Reminderltem item = new Reminderltem(userId,slot);


        return new RemindOn(text);
    }
}
