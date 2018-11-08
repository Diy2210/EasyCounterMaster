package com.example.diy2210.easycounter;

public class CounterAdapter {

    public String time;
    public String title;
    public String description;
    public Integer value;

    public CounterAdapter(String time, String title, String description, Integer  value) {
        this.time = time;
        this.title = title;
        this.title = description;
        this.value = value;
    }

    public String getTime() {return time; }
    public String getTitle() {return title; }
    public String getDescription() {return description; }
    public Integer getValue() {return value; }
}
