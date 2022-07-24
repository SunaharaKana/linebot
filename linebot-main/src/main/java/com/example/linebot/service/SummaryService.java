package com.example.linebot.service;

import com.example.linebot.replier.Intent;
import com.example.linebot.replier.RemindSummary;
import com.example.linebot.repository.SummaryRepository;
import com.example.linebot.value.CovidSlot;
import com.example.linebot.value.SummaryItem;
import com.example.linebot.value.SummarySlot;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SummaryService {

    private final SummaryRepository summaryRepository;

    @Autowired
    public SummaryService(SummaryRepository summaryRepository){
        this.summaryRepository = summaryRepository;

    }

    public RemindSummary doReplyOfSummary(MessageEvent<TextMessageContent> event) throws IOException, InterruptedException {

        TextMessageContent tmc = event.getMessage();
        String text = tmc.getText();
        System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
        System.out.println(text);
        String regexp = Intent.SUMMARY.getRegexp();
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(text);
        System.out.println(matcher);
        if(matcher.matches()){
            text = matcher.group(1);
        }else {
            throw new IllegalArgumentException("text をスロットに分けられません");
        }
        String text2 = SummaryRepository.findSummaryAPI(text);
        return new RemindSummary(text,text2);
    }
}
