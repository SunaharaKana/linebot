package com.example.linebot.value;

public class SummaryItem {
    private final SummarySlot slot;

    public SummaryItem(SummarySlot slot){
        this.slot = slot;
    }

    public SummarySlot getSlot(){
        return slot;
    }
}
