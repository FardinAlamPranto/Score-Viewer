package com.example.scoreviewer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PlayerListAdapter extends ArrayAdapter<MinPlayers> {

    private Context cn;
    private int res;
    private int lp;

    private static class ViewHolder {
        TextView firstname;
        TextView lastname;
    }

    public PlayerListAdapter(Context context, int resource, ArrayList<MinPlayers> mplayers) {
        super(context, resource, mplayers);
        cn = context;
        res = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String firstname = getItem(position).getFirstname();
        String lastname = getItem(position).getSurname();

        final View result;

        ViewHolder holder;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(cn);
            convertView = inflater.inflate(res, parent, false);
            holder= new ViewHolder();
            holder.firstname = convertView.findViewById(R.id.firstname_item);
            holder.lastname =  convertView.findViewById(R.id.lastname_item);
            result = convertView;

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }


        Animation animation = AnimationUtils.loadAnimation(cn,
                (position > lp) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lp = position;

        holder.firstname.setText(firstname);
        holder.lastname.setText(lastname);

        return convertView;
    }
}
