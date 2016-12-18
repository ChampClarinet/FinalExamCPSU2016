package com.example.clarinetmaster.finalexamcpsu2016;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.clarinetmaster.finalexamcpsu2016.Adapters.ListAdapter;
import com.example.clarinetmaster.finalexamcpsu2016.Models.UsersMenu;

public class UserListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        ListView listView = (ListView) findViewById(R.id.user_list_view);
        UsersMenu menu = UsersMenu.getInstance(this);
        ListAdapter adapter = new ListAdapter(this, R.layout.list_users, menu.getUsersList());
        listView.setAdapter(adapter);

    }

}
