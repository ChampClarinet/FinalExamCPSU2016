package com.example.clarinetmaster.finalexamcpsu2016;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clarinetmaster.finalexamcpsu2016.Models.Users;
import com.example.clarinetmaster.finalexamcpsu2016.Models.UsersMenu;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button createAccountButton;
    private Button loginButton;
    private UsersMenu menu;

    private Users curUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindWidget();

        loginButton.setOnClickListener(this);
        createAccountButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_create_account){
            startActivity(new Intent(this, RegisterActivity.class));
        }else {
            menu = UsersMenu.getInstance(this);
            if(checkValid()){
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("user", curUser);
                startActivity(intent);
            }
        }

    }

    private boolean checkValid() {
        String name = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        curUser = menu.findUsersByName(name);
        if(curUser != null){
            if(curUser.getPassword().equals(password)) return true;
        }
        Toast.makeText(this, getString(R.string.invalid_login), Toast.LENGTH_SHORT).show();
        return false;
    }

    private void bindWidget() {
        usernameEditText = (EditText) findViewById(R.id.editText_userName);
        passwordEditText = (EditText) findViewById(R.id.editText_password);
        createAccountButton = (Button) findViewById(R.id.btn_create_account);
        loginButton = (Button) findViewById(R.id.btn_login_submit);

        passwordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    if(keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER){
                        onClick(v);
                        return true;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.login_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_user_list){
            startActivity(new Intent(this, UserListActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

}
