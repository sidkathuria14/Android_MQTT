package com.app.androidkt.mqtt;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {
EditText user,pass,subscribe;
String username,password,topic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

       user =  (EditText)findViewById(R.id.user);
       pass = (EditText)findViewById(R.id.pass);
       subscribe = (EditText)findViewById(R.id.subscribe);

        SharedPreferences pref = this.getSharedPreferences(
                "pref", Context.MODE_PRIVATE);
       final SharedPreferences.Editor editor = pref.edit();

        ((Button)findViewById(R.id.subscribeBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topic = subscribe.getText().toString();
                editor.putString("topic",topic);
            }
        });

((Button)findViewById(R.id.passBtn)).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        password = pass.getText().toString();
        editor.putString("password",password);
    }
});
        ((Button)findViewById(R.id.userBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = user.getText().toString();
                editor.putString("username",username);
            }
        });
        editor.commit();



    }
}
