package com.example.fitapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    int Status = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final TextView tvHelp = (TextView)findViewById(R.id.tvHelp);
        final Button btnLogin = (Button)findViewById(R.id.btnLogin);
        final TextView RegisterLink = (TextView)findViewById(R.id.RegisterLink);


        RegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent RegisterIntent = new Intent(MainActivity.this,Registerpage.class);
                MainActivity.this.startActivity(RegisterIntent);
            }
        });



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(etName.getText().toString(), etPassword.getText().toString());
                if(Status==0){
                    tvHelp.setText("Wrong username/password");
                }
            }
        });


    }

    private void validate(String userName, String userPassword){
        if((userName.equals("Admin"))&& (userPassword.equals("1234"))){
            Intent MainpageA = new Intent(MainActivity.this,Home.class);
            startActivity(MainpageA);
            Status = 1;
        }
    }
}
