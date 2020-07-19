


package com.example.newversion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class Login extends AppCompatActivity {
    EditText ed1,ed2;
    Button b1;
    static public String uname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ed1=findViewById(R.id.ed1);
        ed2=findViewById(R.id.ed2);
        b1=findViewById(R.id.b1);

      //  Toast.makeText(getApplicationContext(),uname,Toast.LENGTH_LONG).show();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"LOGIN SUCCESFULLY",Toast.LENGTH_LONG).show();
                uname=(ed1.getText()).toString().trim();
                Intent i=new Intent(Login.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
