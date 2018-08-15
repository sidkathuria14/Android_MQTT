package com.app.androidkt.mqtt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    private MqttAndroidClient client;
    private String TAG = "MainActivity";
    private PahoMqttClient pahoMqttClient;
//String username = "iytoiwss";
    boolean led1 = false,led2= false,led3= false,led4= false,led5= false,led6= false,led7= false,led8= false,led9= false;
    String username,password,topic;
//String user1 = "sidd";
//String pass = "123456";
//String password = "3nCzHJC94OLH";
    private EditText textMessage, subscribeTopic, unSubscribeTopic;
    private Button publishMessage, subscribe, unSubscribe;
    long led,msg_on,msg_off;
TextView tv;
Spinner spinner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pahoMqttClient = new PahoMqttClient();

        tv = (TextView) findViewById(R.id.tv);
//        tv.setVisibility(View.INVISIBLE);

        SharedPreferences pref = this.getPreferences(Context.MODE_PRIVATE);
        username = pref.getString("username", "avrutti03");
        password = pref.getString("password", "123456789");
        topic = pref.getString("topic", "my_iot");


//        textMessage = (EditText) findViewById(R.id.textMessage);
//        publishMessage = (Button) findViewById(R.id.publishMessage);
//
//        subscribe = (Button) findViewById(R.id.subscribe);
//        unSubscribe = (Button) findViewById(R.id.unSubscribe);
//
//        subscribeTopic = (EditText) findViewById(R.id.subscribeTopic);
//        unSubscribeTopic = (EditText) findViewById(R.id.unSubscribeTopic);
        MqttConnectOptions mqtt = new MqttConnectOptions();
        mqtt.setPassword(password.toCharArray());
        mqtt.setUserName(username);
//        mqtt.setServerURIs(new String[] {"mqtt://m10.cloudmqtt.com:" + Constants.PORT});
        client = pahoMqttClient.getMqttClient(getApplicationContext(), Constants.MQTT_BROKER_URL, Constants.CLIENT_ID);
//        publishMessage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick: publish");
//                String msg = textMessage.getText().toString().trim();
//                Log.d(TAG, "onClick: " + msg);
//                if (!msg.isEmpty()) {
//                    try {
//                        pahoMqttClient.publishMessage(client, msg, 1, Constants.PUBLISH_TOPIC);
////                        pahoMqttClient.publishMessage();
//                        Log.d(TAG, "onClick: " + "try");
//                    } catch (MqttException e) {
//                        Log.d(TAG, "onClick: mqtte");
//                        e.printStackTrace();
//                    } catch (UnsupportedEncodingException e) {
//                        Log.d(TAG, "onClick: usee");
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//try{
//        pahoMqttClient.subscribe(client,"blah", 1);
//                    } catch (MqttException e) {
//                        e.printStackTrace();
//                    }

//        subscribe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                String topic = subscribeTopic.getText().toString().trim();
////                if (!topic.isEmpty()) {
//                    try {
//
//                        pahoMqttClient.subscribe(client, topic, 1);
//Toast.makeText(MainActivity.this,"connected successfully",Toast.LENGTH_LONG).show();
//                    } catch (MqttException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//        });
//        unSubscribe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String topic = unSubscribeTopic.getText().toString().trim();
//                if (!topic.isEmpty()) {
//                    try {
//                        pahoMqttClient.unSubscribe(client, topic);
//                    } catch (MqttException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });

        spinner = (Spinner) findViewById(R.id.spinner);
spinner2 = (Spinner)findViewById(R.id.spinner2);
        List<String> list = new ArrayList<String>();
        list.add("LED 1");
        list.add("LED 2");
        list.add("LED 3");
        list.add("LED 4");
        list.add("LED 5");
        list.add("LED 6");
        list.add("LED 7");
        list.add("LED 8");
        list.add("LED 9");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
//        spinner.setOnItemClickListener();
//        int led_number = Integer.parseInt(String.valueOf(spinner.getSelectedItem().toString().charAt(4)));


        List<String> list2 = new ArrayList<String>();
        list2.add("LED 10");
        list2.add("LED 11");
        list2.add("LED 12");
        list2.add("LED 13");
        list2.add("LED 14");
        list2.add("LED 15");
        list2.add("LED 16");
        list2.add("LED 17");
        list2.add("LED 18");
//        tv.setText("LED " + led_number + " is switched" + );
ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list2);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter2);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                led = adapterView.getItemIdAtPosition(i) + 1;
                if (led == 1) {
                    msg_on = 0;
                    msg_off = 2;
                    tv.setTextColor(led1?Color.GREEN:Color.RED);
                    tv.setText("LED 1 is switched " + (led1 ? "on" : "off"));
//                    tv.setVisibility(View.VISIBLE);
                } else if (led == 2) {
                    msg_on = 3;
                    msg_off = 4;
                    tv.setTextColor(led2?Color.GREEN:Color.RED);
                    tv.setText("LED 2 is switched " + (led2 ? "on" : "off"));
                } else if (led == 3) {
                    msg_on = 5;
                    msg_off = 6;
                    tv.setTextColor(led3?Color.GREEN:Color.RED);
                    tv.setText("LED 3 is switched " + (led3 ? "on" : "off"));
                } else if (led == 4) {
                    msg_on = 7;
                    msg_off = 8;
                    tv.setTextColor(led4?Color.GREEN:Color.RED);
                    tv.setText("LED 4 is switched " + (led4 ? "on" : "off"));
                } else if (led == 5) {
                    msg_on = 9;
                    msg_off = 10;
                    tv.setTextColor(led5?Color.GREEN:Color.RED);
                    tv.setText("LED 5 is switched " + (led5 ? "on" : "off"));
                } else if (led == 6) {
                    msg_on = 11;
                    msg_off = 12;
                    tv.setTextColor(led6?Color.GREEN:Color.RED);
                    tv.setText("LED 6 is switched " + (led6 ? "on" : "off"));
                } else if (led == 7) {
                    msg_on = 13;
                    msg_off = 14;
                    tv.setTextColor(led7?Color.GREEN:Color.RED);
                    tv.setText("LED 7 is switched " + (led7 ? "on" : "off"));
                } else if (led == 8) {
                    msg_on = 15;
                    msg_off = 16;
                    tv.setTextColor(led8?Color.GREEN:Color.RED);
                    tv.setText("LED 8 is switched " + (led8 ? "on" : "off"));
                } else if (led == 9) {
                    msg_on = 17;
                    msg_off = 18;
                    tv.setTextColor(led9?Color.GREEN:Color.RED);
                    tv.setText("LED 9 is switched " + (led9 ? "on" : "off"));
                }

                Log.d(TAG, "onItemSelected: " + adapterView.getItemIdAtPosition(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//        AdapterView.OnItemSelectedListener

        ((Button)findViewById(R.id.btnOn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: btnOn");
                Log.d(TAG, "onClick: " + spinner.getSelectedItem());
                int a = Integer.parseInt(String.valueOf(spinner.getSelectedItem().toString().charAt(4)));
                Log.d(TAG, "onClick: a = " + a);
                tv.setTextColor(Color.GREEN);
                tv.setText(tv.getText().toString().substring(0,17) + " on");
                Log.d(TAG, "onClick: " + tv.getText().toString().substring(18).equals("on"));

                switch(a){
                    case 1:
                        led1 = true;
                    case 2:
                        led2 = true;
                    case 3:
                        led3 = true;
                    case 4:
                        led4 = true;
                    case 5:
                        led5 = true;
                    case 6:
                        led6 = true;
                    case 7:
                        led7 = true;
                    case 8:
                        led8 = true;
                    case 9:
                        led9 = true;
                }

                try {
                    pahoMqttClient.publishMessage(client, String.valueOf(msg_on), 1, Constants.PUBLISH_TOPIC);
//                        pahoMqttClient.publishMessage();
                    Log.d(TAG, "onClick: " + "try");
                    Toast.makeText(MainActivity.this, String.valueOf(msg_on), Toast.LENGTH_SHORT).show();
                } catch (MqttException e) {
                    Log.d(TAG, "onClick: mqtte");
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    Log.d(TAG, "onClick: usee");
                    e.printStackTrace();
                }
            }
        });

        ((Button)findViewById(R.id.btnOff)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: btnOff");
                int b = Integer.parseInt(String.valueOf(spinner.getSelectedItem().toString().charAt(4)));
                Log.d(TAG, "onClick: b = " + b);
                tv.setTextColor(Color.RED);
                tv.setText(tv.getText().toString().substring(0,17) + " off");

                Log.d(TAG, "onClick: " + tv.getText().toString().substring(18).equals("off"));

                switch(b){
                    case 1:
                        led1 = false;
                    case 2:
                        led2 = false;
                    case 3:
                        led3 = false;
                    case 4:
                        led4 = false;
                    case 5:
                        led5 = false;
                    case 6:
                        led6 = false;
                    case 7:
                        led7 = false;
                    case 8:
                        led8 = false;
                    case 9:
                        led9 = false;
                }

                Log.d(TAG, "onClick: selected led" + spinner.getSelectedItem());
                try {
                    pahoMqttClient.publishMessage(client, String.valueOf(msg_off), 1, Constants.PUBLISH_TOPIC);
//                        pahoMqttClient.publishMessage();
                    Toast.makeText(MainActivity.this, String.valueOf(msg_off), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onClick: " + "try");
                } catch (MqttException e) {
                    Log.d(TAG, "onClick: mqtte");
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    Log.d(TAG, "onClick: usee");
                    e.printStackTrace();
                }
            }
        });

        Intent intent = new Intent(MainActivity.this, MqttMessageService.class);
        startService(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.settings:
                startActivity(new Intent(MainActivity.this,SettingsActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
//<EditText
//        android:id="@+id/subscribeTopic"
//                android:layout_width="178dp"
//                android:layout_height="wrap_content"
//                android:layout_marginLeft="16dp"
//                android:layout_marginTop="24dp"
//                android:ems="10"
//                android:hint="Subscribe topic"
//                android:inputType="textPersonName"
//                app:layout_constraintLeft_toLeftOf="parent"
//                app:layout_constraintTop_toBottomOf="@+id/textMessage" />
//
//<Button
//        android:id="@+id/subscribe"
//                android:layout_width="124dp"
//                android:layout_height="48dp"
//                android:layout_marginLeft="0dp"
//                android:layout_marginRight="8dp"
//                android:layout_marginTop="24dp"
//                android:text="Subscribe"
//                app:layout_constraintHorizontal_bias="0.0"
//                app:layout_constraintLeft_toLeftOf="@+id/publishMessage"
//                app:layout_constraintRight_toRightOf="@+id/publishMessage"
//                app:layout_constraintTop_toBottomOf="@+id/publishMessage" />
//
//<EditText
//        android:id="@+id/unSubscribeTopic"
//                android:layout_width="180dp"
//                android:layout_height="wrap_content"
//                android:layout_marginRight="0dp"
//                android:layout_marginTop="24dp"
//                android:ems="10"
//                android:hint="Unsubscribe topic"
//                android:inputType="textPersonName"
//                app:layout_constraintHorizontal_bias="0.15"
//                app:layout_constraintLeft_toLeftOf="@+id/subscribeTopic"
//                app:layout_constraintRight_toRightOf="@+id/subscribeTopic"
//                app:layout_constraintTop_toBottomOf="@+id/subscribeTopic" />
//
//<Button
//        android:id="@+id/unSubscribe"
//                android:layout_width="124dp"
//                android:layout_height="48dp"
//                android:layout_marginLeft="8dp"
//                android:layout_marginRight="8dp"
//                android:layout_marginTop="18dp"
//                android:text="UnSubscribe"
//                app:layout_constraintHorizontal_bias="0.416"
//                app:layout_constraintLeft_toRightOf="@+id/unSubscribeTopic"
//                app:layout_constraintRight_toRightOf="parent"
//                app:layout_constraintTop_toBottomOf="@+id/subscribe" />


//<EditText
//        android:id="@+id/textMessage"
//                android:layout_width="180dp"
//                android:layout_height="wrap_content"
//                android:layout_marginLeft="16dp"
//                android:layout_marginTop="16dp"
//                android:ems="10"
//                android:hint="Enter message"
//                android:inputType="textPersonName"
//                app:layout_constraintLeft_toLeftOf="parent"
//                app:layout_constraintTop_toTopOf="parent" />
//
//<Button
//        android:id="@+id/publishMessage"
//                android:layout_width="124dp"
//                android:layout_height="48dp"
//                android:layout_marginLeft="8dp"
//                android:layout_marginRight="8dp"
//                android:layout_marginTop="11dp"
//                android:text="Publish"
//                app:layout_constraintHorizontal_bias="0.416"
//                app:layout_constraintLeft_toRightOf="@+id/textMessage"
//                app:layout_constraintRight_toRightOf="parent"
//                app:layout_constraintTop_toTopOf="parent" />

