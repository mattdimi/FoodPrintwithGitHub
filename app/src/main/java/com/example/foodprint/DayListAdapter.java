package com.example.foodprint;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DayListAdapter extends ArrayAdapter<Day> {

    static class ViewHolder {
        TextView text_view0,text_view1,text_view2,text_view3;
    }

    Context context;
    int resource;
    private int last_position =-1;
    ViewHolder holder;

    public DayListAdapter(Context context, int resource, ArrayList<Day> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        Day day = getItem(position);

        final View result;
        if (convertView ==  null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource,parent,false);
            holder = new ViewHolder();
            holder.text_view0 = convertView.findViewById(R.id.text_view0);
            holder.text_view1 = convertView.findViewById(R.id.text_view1);
            holder.text_view2 = convertView.findViewById(R.id.text_view2);
            holder.text_view3 = convertView.findViewById(R.id.text_view3);
            convertView.setTag(holder);
            result = convertView;
        }
        else{
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(context,(position>last_position)? R.anim.load_down_anim:R.anim.load_up_anim);
        result.startAnimation(animation);
        last_position = position;

        holder.text_view0.setText(day.getDate());
        holder.text_view1.setText(String.valueOf(day.getSteps()));
        holder.text_view2.setText(String.valueOf(day.getCalories()));
        holder.text_view3.setText(String.valueOf(day.getDistance()));

        return convertView;

    }

}

