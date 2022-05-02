package com.example.linebot.service;


import com.example.linebot.replier.RemindOn;
import com.example.linebot.repository.ReminderRepository;
import com.example.linebot.value.ReminderSlot;
import com.example.linebot.value.Reminderltem;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.linebot.repository.ReminderRepository;

@Service
public class ReminderService {

    private final ReminderRepository repository;

    @Autowired
    public ReminderService(ReminderRepository reminderRepository){
        this.repository = reminderRepository;
    }

    public RemindOn doReplyOfNewItem(MessageEvent<TextMessageContent> event){
        String userId = event.getSource().getUserId();
        TextMessageContent tmc = event.getMessage();
        String text = tmc.getText();
        ReminderSlot slot = new ReminderSlot(text);
        Reminderltem item = new Reminderltem(userId,slot);

        repository.insert(item);

        return new RemindOn(text);
    }
}
