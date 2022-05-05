package com.example.linebot;

import com.linecorp.bot.model.response.BotApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;

import com.example.linebot.service.ReminderService;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;


@RestController // <1>
public class Push {

    private static final Logger log = LoggerFactory.getLogger(Push.class);

    // push先のユーザID
    private String userId = "U0346ece35886a173a9fececb51a9208c";

    private final LineMessagingClient messagingClient;

    private final ReminderService reminderService; // <1>

    @Autowired // <2>
    public Push(LineMessagingClient lineMessagingClient,
                ReminderService reminderService){
        this.messagingClient = lineMessagingClient;
        this.reminderService = reminderService;
    }

    /*
    @Autowired
    public Push(LineMessagingClient lineMessagingClient){
        this.messagingClient = lineMessagingClient;
    }
     */

    // テスト
    @GetMapping("test")
    public String hello(HttpServletRequest request) {
        return "Get from " + request.getRequestURL();
    }

    // 時報をpushする
    @GetMapping("timetone") //<2>
    //@Scheduled(cron = "0 */1 * * * *",zone = "Asia/Tokyo")
    public String pushTimeTone(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("a K:mm"); // <3>
        String text = dtf.format(LocalDateTime.now());
        try {
            PushMessage pMsg =
                    new PushMessage(userId,new TextMessage(text)); // <4>
            BotApiResponse resp = messagingClient.pushMessage(pMsg).get();
            log.info("Sent messages: {}",resp);
        }catch (InterruptedException | ExecutionException e){
            throw new RuntimeException(e);
        }
        return text; // <5>
    }

    @Scheduled(cron = "0 */1 * * * *",zone = "Asia/Tokyo")
    public void pushReminder(){
        try {
            List<PushMessage> messages =
                    reminderService.doPushReminderItems();
            for (PushMessage message : messages){
                BotApiResponse resp =
                       messagingClient.pushMessage(message).get(); // <2>
                log.info("Sent messages: {}", resp);
            }
        } catch (InterruptedException | ExecutionException e){
            throw new RuntimeException(e);
        }
    }

}