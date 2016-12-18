package com.example.clarinetmaster.finalexamcpsu2016;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.clarinetmaster.finalexamcpsu2016.Models.Users;
import com.example.clarinetmaster.finalexamcpsu2016.Models.UsersMenu;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nameEditText;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button registerButton;
    private UsersMenu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bindWidget();

        menu = UsersMenu.getInstance(this);

        registerButton.setOnClickListener(this);

    }

    private AlertDialog.Builder registerFailedDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.dialog_register_failed_title);
        dialog.setMessage(R.string.dialog_register_failed_message);
        dialog.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        return dialog;
    }

    private boolean checkValid(String username) {
        return menu.findUsersByName(username) == null;
    }

    private void bindWidget() {
        nameEditText = (EditText) findViewById(R.id.register_editText_name);
        usernameEditText = (EditText) findViewById(R.id.register_editText_userName);
        passwordEditText = (EditText) findViewById(R.id.register_editText_password);
        registerButton = (Button) findViewById(R.id.btn_register_submit);

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
    public void onClick(View v) {
        String name = nameEditText.getText().toString();
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if(checkValid(username)){
            menu.insertData(new Users(name, username, password));
            finish();
        }else{
            registerFailedDialog().show();
        }
    }
}
