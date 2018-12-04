package com.example.diy2210.easycounter;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MyCountersListActivity extends AppCompatActivity {

    private SQLHelper sqlHelper;
    private ListView listView;
    private ListAdapter adapter;
    private ConstraintLayout emptyCL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_counters_list);

        listView = findViewById(R.id.listView);
        emptyCL = findViewById(R.id.emptyCL);
        listView.setEmptyView(emptyCL);

        sqlHelper = new SQLHelper(this);
        ArrayList<HashMap<String, String>> counterList = sqlHelper.GetCounters();
        adapter = new SimpleAdapter(MyCountersListActivity.this, counterList, R.layout.my_counter_list_item,
                new String[]{"time", "title", "description", "value"},
                new int[]{R.id.time, R.id.title, R.id.description, R.id.value});
        listView.setAdapter(adapter);

    }
}
