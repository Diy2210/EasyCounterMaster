//package com.example.diy2210.easycounter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CounterAdapter extends ArrayAdapter<Counter> {
//
//    private Context mContext;
//    private ArrayList<Counter> counters;
//
//    public CounterAdapter(Context context, ArrayList<Counter> counters) {
//        super(context, R.layout.my_counter_list_item, counters);
////        super(context, 0, counters);
//        mContext = context;
//        this.counters = counters;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View rowView = convertView;
//        if (rowView == null) {
//
//            rowView = LayoutInflater.from(mContext).inflate(R.layout.my_counter_list_item, parent, false);
//
//            TextView timeTV = rowView.findViewById(R.id.timeTV);
//            timeTV.setText(counters.get(position).getTime());
//
//            TextView titleTV = rowView.findViewById(R.id.titleTV);
//            titleTV.setText(counters.get(position).getTitle());
//
//            TextView descriptionTV = rowView.findViewById(R.id.descriptionTV);
//            descriptionTV.setText(counters.get(position).getDescription());
//
//            TextView valueTV = rowView.findViewById(R.id.valueTV);
//            valueTV.setText(counters.get(position).getValue());
//
//        }
//
//        return rowView;
//    }
//}
