package com.example.diy2210.easycounter;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MyCountersListActivity extends AppCompatActivity {

    private ListView listView;
    private CounterAdapter counterAdapter;
    private ArrayList<Counter> counters;
    private ArrayAdapter<String> adapter;
    private ConstraintLayout emptyCL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_counters_list);

        listView = findViewById(R.id.listView);
        emptyCL = findViewById(R.id.emptyCL);
        listView.setEmptyView(emptyCL);

//        ArrayList<Counter> counterArrayList = new ArrayList<>();
//        counterArrayList.add(new Counter(ECApp.valueInt.toString()));
//        counterAdapter = new CounterAdapter(this, counterArrayList);
//        listView.setAdapter(counterAdapter);

//        counterAdapter = new CounterAdapter(this, counters);
//        listView.setAdapter(counterAdapter);

        counters = new ArrayList<>();
        adapter = new ArrayAdapter<>(MyCountersListActivity.this, R.layout.my_counter_list_item);
    }
}
