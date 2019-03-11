package com.example.aomek.missingpersonsfinder.login;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.aomek.missingpersonsfinder.R;
import com.example.aomek.missingpersonsfinder.home.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class loginActivity extends AppCompatActivity {

    private EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //// when click register
        Button registerButton = findViewById(R.id.regist_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(loginActivity.this, registerActivity.class);
                startActivity(i);
            }
        });

        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(loginActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        email =  (EditText) findViewById(R.id.email_edit_text);
        password =  (EditText) findViewById(R.id.password_edit_text);


//        String json = toJSON();
//                if(json == ""){
//                    Toast t = Toast.makeText(this, "false", Toast.LENGTH_SHORT);
//                    t.show();
//                }else {
//                    Toast t = Toast.makeText(this, json, Toast.LENGTH_SHORT);
//                    t.show();
//                }
        //// when click login
//        Button loginButton = findViewById(R.id.login_button);
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String json = toJSON();
//                if(json == ""){
//                }else {
//                }
//            }
//        });

    }

    public String getEmail(){
        return this.email.getText().toString();
    }

    public String getPassword(){
        return this.password.getText().toString();
    }

    public String toJSON(){

        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("email", getEmail() );
            jsonObject.put("password", getPassword());

            return jsonObject.toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }
}
