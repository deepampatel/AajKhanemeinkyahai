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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edittextEmail;
    private EditText edittextPassword;
    private TextView signupText;
    private CircularProgressButton signinButton;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null)
        {
            finish();
            startActivity(new Intent(this,updateMenu.class));
        }
        edittextEmail=(EditText)findViewById(R.id.editextEMAIL);
        edittextPassword=(EditText)findViewById(R.id.editextPASSWORD);
        signupText=(TextView)findViewById(R.id.signupText);
        signinButton=(CircularProgressButton) findViewById(R.id.signinbutton);

        signinButton.setOnClickListener(this);
        signupText.setOnClickListener(this);

    }
    private void userSignin() {
        signinButton.startAnimation();
        String email=edittextEmail.getText().toString().trim();
        String password=edittextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)) {
            signinButton.revertAnimation();
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            signinButton.revertAnimation();
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            signinButton.revertAnimation();
                            startActivity(new Intent(LoginActivity.this,profileactivity.class));
                            edittextEmail.setText("");
                            edittextPassword.setText("");
                        }
                        else
                            signinButton.revertAnimation();
                            Toast.makeText(LoginActivity.this,"Could not signin,please try again",Toast.LENGTH_LONG).show();
                        edittextEmail.setText("");
                        edittextPassword.setText("");
                    }
                });

    }

    @Override
    public void onClick(View v) {
        if(v==signinButton)
        { userSignin();}
        if(v==signupText) {
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
        }

    }

