package com.example.clarinetmaster.finalexamcpsu2016;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.clarinetmaster.finalexamcpsu2016.Models.Users;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Users curUser = (Users) getIntent().getExtras().getSerializable("user");
        TextView nameTextView = (TextView) findViewById(R.id.main_name_text_view);
        nameTextView.setText(curUser.getName());

    }

}
