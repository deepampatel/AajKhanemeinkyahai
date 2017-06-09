package com.example.deepampatel.aajkhanemeinkyahai;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText edittextemail;
    private EditText edittextpassword;
    private TextView signinTextview;
    private CircularProgressButton signupbutton;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null)
        {
            finish();
            startActivity(new Intent(this,profileactivity.class));
        }

        edittextemail=(EditText)findViewById(R.id.editextEMAIL);
        edittextpassword=(EditText)findViewById(R.id.editextPASSWORD);
        signupbutton =(CircularProgressButton) findViewById(R.id.signupbutton);

        signinTextview=(TextView)findViewById(R.id.signinText);

        signupbutton.setOnClickListener(this);
        signinTextview.setOnClickListener(this);

    }
    private void registeruser(){
        signupbutton.startAnimation();
        String email=edittextemail.getText().toString().trim();
        String password=edittextpassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)) {
            signupbutton.revertAnimation();
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            signupbutton.revertAnimation();
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            signupbutton.revertAnimation();
                            finish();
                            startActivity(new Intent(MainActivity.this,profileactivity.class));
                        }
                        else {
                            signupbutton.revertAnimation();
                            Toast.makeText(MainActivity.this, "Could not register,please try again", Toast.LENGTH_LONG).show();
                        }
                    }
                });
        edittextemail.setText("");
        edittextpassword.setText("");

    }


    @Override
    public void onClick(View view) {
        if(view==signupbutton)
            registeruser();
        if(view==signinTextview){
            //start login activity
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
            //Open login activity
    }


}
