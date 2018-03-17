package com.uom.hashbeats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PendingAdapter extends ArrayAdapter<MedicalRecord> {
    private final Context context;
    private final ArrayList<MedicalRecord> values;

    public PendingAdapter(Context context, ArrayList<MedicalRecord> values) {
        super(context, R.layout.pending_row, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.pending_row, parent, false);
        TextView title = (TextView) rowView.findViewById(R.id.title);
        TextView desc = (TextView) rowView.findViewById(R.id.desc);
        TextView date = (TextView) rowView.findViewById(R.id.date);

        title.setText(values.get(position).getTitle());
        desc.setText(values.get(position).getDescr());
        date.setText(getDate(values.get(position).getDate()).toString());

        return rowView;
    }

    private Date getDate(int i) {
        String dateInString = "2011-1-1";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, i);
        return new Date(c.getTimeInMillis());
    }
}