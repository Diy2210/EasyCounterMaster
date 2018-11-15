package com.example.diy2210.easycounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MyCountersListActivity extends AppCompatActivity {

    private ListView listView;
    private CounterAdapter counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_counters_list);

        listView = findViewById(R.id.listView);
        ArrayList<Counter> counterArrayList = new ArrayList<>();
        counterArrayList.add(new Counter(ECApp.valueInt.toString()));
        counter = new CounterAdapter(this, counterArrayList);
        listView.setAdapter(counter);
    }
}
