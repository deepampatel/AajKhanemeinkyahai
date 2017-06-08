package com.example.deepampatel.aajkhanemeinkyahai;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class profileactivity extends AppCompatActivity implements View.OnClickListener{
    private TextView mess1;
    private TextView mess2;
    private Button updateButton;
    private TextView mess3;
    private FirebaseAuth firebaseAuth;
    String selectedmess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileactivity);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

        updateButton=(Button)findViewById(R.id.updateButton);

        mess1=(TextView)findViewById(R.id.mess1);
        mess2=(TextView)findViewById(R.id.mess2);
        mess3=(TextView)findViewById(R.id.mess3);

        mess1.setOnClickListener(this);
        mess2.setOnClickListener(this);
        mess3.setOnClickListener(this);
        updateButton.setOnClickListener(this);

    }

    public void getmess1Menu(){
        Intent intent = new Intent(profileactivity.this,seeMenu.class);
        intent.putExtra("MESS_NAME", "Mess1");
        startActivity(intent);

    }
    public void getmess2Menu(){
        Intent intent = new Intent(profileactivity.this,seeMenu.class);
        intent.putExtra("MESS_NAME", "Mess2");
        startActivity(intent);
    }
    public void getmess3Menu(){
        Intent intent = new Intent(profileactivity.this,seeMenu.class);
        intent.putExtra("MESS_NAME", "Mess3");
        startActivity(intent);
    }
    @Override
    public void onClick(View v) {
        if(v==mess1)
        {
         getmess1Menu();
        }
        else if(v==mess2)
        {
            getmess2Menu();
        }
        else if(v==mess3)
        {
            getmess3Menu();
        }
        else if(v==updateButton)
        {

            startActivity(new Intent(profileactivity.this,updateMenu.class));
        }
    }
}
