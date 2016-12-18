package com.example.clarinetmaster.finalexamcpsu2016.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.clarinetmaster.finalexamcpsu2016.Models.Users;
import com.example.clarinetmaster.finalexamcpsu2016.R;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Users>{

    private Context context;
    private ArrayList<Users> usersArrayList;

    public ListAdapter(Context context, int resource, ArrayList<Users> usersArrayList) {
        super(context, resource, usersArrayList);

        this.context = context;
        this.usersArrayList = usersArrayList;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemLayout = View.inflate(context, R.layout.list_users, null);
        TextView textView = (TextView) itemLayout.findViewById(R.id.list_users_name_text_view);
        Users curUser = usersArrayList.get(position);
        textView.setText(curUser.getName());

        return itemLayout;

    }

}
