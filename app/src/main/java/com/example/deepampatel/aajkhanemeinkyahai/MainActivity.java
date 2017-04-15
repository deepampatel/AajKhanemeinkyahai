package com.example.deepampatel.aajkhanemeinkyahai;

import android.app.ProgressDialog;
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
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText edittextemail;
    private EditText edittextpassword;
    private TextView signin;
    private Button signupbutton;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private Button signinbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        edittextemail=(EditText)findViewById(R.id.editextEMAIL);
        edittextpassword=(EditText)findViewById(R.id.editextPASSWORD);
        signupbutton =(Button)findViewById(R.id.signupbutton);
        signinbutton =(Button)findViewById(R.id.signinbutton);
        signin=(TextView)findViewById(R.id.signinText);

        signupbutton.setOnClickListener(this);
        signinbutton.setOnClickListener(this);

    }
    private void registeruser(){
        String email=edittextemail.getText().toString().trim();
        String password=edittextpassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering user");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                           Toast.makeText(MainActivity.this,"User Registered",Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(MainActivity.this,"Could not register,please try again",Toast.LENGTH_LONG).show();
                    }
                });
        edittextemail.setText("");
        edittextpassword.setText("");

    }

    @Override
    public void onClick(View view) {
        if(view==signupbutton)
            registeruser();
        if(view==signinbutton){
            signinuser();

        }
            //Open login activity
    }

    private void signinuser() {
        String email=edittextemail.getText().toString().trim();
        String password=edittextpassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();

            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Logging in");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"User signed in",Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                        else
                            Toast.makeText(MainActivity.this,"Could not sign in,please try again",Toast.LENGTH_LONG).show();
                    }
                });
        edittextemail.setText("");
        edittextpassword.setText("");
    }
}
