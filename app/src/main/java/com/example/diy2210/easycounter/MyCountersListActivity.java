package com.example.diy2210.easycounter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MyCountersListActivity.this);
                builder.setCancelable(false);
                builder.setMessage(R.string.message_delete_item_counter);
                builder.setPositiveButton(R.string.ok_button,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                sqlHelper.DeleteCounter(position);
                                recreate();
                            }
                        })
                        .setNegativeButton(R.string.cancel_button,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        dialog.dismiss();
                                    }
                                }
                        ).show();
            }
        });
    }
}
