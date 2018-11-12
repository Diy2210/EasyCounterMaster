package com.example.diy2210.easycounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MyCountersListActivity extends AppCompatActivity {

    private ListView listView;
    private Counter counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_counters_list);

        listView = findViewById(R.id.listView);
//        counter = new Counter(this, R.layout.my_counter_list_item)
    }
}
