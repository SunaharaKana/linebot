package com.example.linebot.repository;

import com.example.linebot.value.Reminderltem;
import com.example.linebot.value.ReminderSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.linebot.repository.ReminderRepository;

@Repository
public class ReminderRepository {
    private final JdbcTemplate jdbc;

    @Autowired
    public ReminderRepository(JdbcTemplate jdbcTemplate){
        this.jdbc = jdbcTemplate;
    }

    public void insert(Reminderltem item){
        // language=sql
        String sql = "insert into reminder_item "
                + "(user_id, push_at, push_text) "
                + "values (?, ?, ?)";

        String userId = item.getUserId();
        ReminderSlot slot = item.getSlot();
        jdbc.update(sql,userId,slot.getPushAt(),slot.getPushText());
    }

}
