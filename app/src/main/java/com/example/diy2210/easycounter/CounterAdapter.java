package com.example.diy2210.easycounter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CounterAdapter extends ArrayAdapter<Counter> {

    private Context mContext;
    private List<Counter> countersList = new ArrayList<>();

    public CounterAdapter(Context context, ArrayList<Counter> list) {
        super(context, 0 , list);
        mContext = context;
        countersList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.my_counter_list_item,parent,false);

        Counter currentCounter = countersList.get(position);

        TextView timeTV = listItem.findViewById(R.id.timeTV);
        timeTV.setText(currentCounter.getTime());

        TextView titleTV = listItem.findViewById(R.id.titleTV);
        titleTV.setText(currentCounter.getTitle());

        TextView descriptionTV = listItem.findViewById(R.id.descriptionTV);
        descriptionTV.setText(currentCounter.getDescription());

        TextView valueTV = listItem.findViewById(R.id.valueTV);
        valueTV.setText(currentCounter.getValue());

        return listItem;
    }
}
