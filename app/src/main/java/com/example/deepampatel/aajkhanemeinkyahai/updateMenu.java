package com.example.deepampatel.aajkhanemeinkyahai;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class updateMenu extends AppCompatActivity implements View.OnClickListener{
   private Spinner spinner;
    private TextView userEmailText;
    private Button logoutButton;
    private FirebaseAuth firebaseAuth;
    private EditText editTextitem1;
    private EditText editTextitem2;
    private EditText editTextitem3;
    private DatabaseReference databaseReference;
    private Button saveitem;
   String seletedMessl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatemenu);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
        databaseReference = FirebaseDatabase.getInstance().getReference();
        editTextitem1=(EditText) findViewById (R.id.item1);
        editTextitem2 =(EditText) findViewById( R.id.item2);
        editTextitem3 =(EditText) findViewById( R.id.item3);
        saveitem=(Button) findViewById(R.id.saveitem);
        FirebaseUser user=firebaseAuth.getCurrentUser();
        userEmailText=(TextView)findViewById(R.id.userEmailView);
        logoutButton=(Button)findViewById(R.id.logoutButton);

        logoutButton.setOnClickListener(this);
        saveitem.setOnClickListener(this);
        userEmailText.setText("Welcome  "+user.getEmail());
        spinner=(Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item;
                switch (position){
                    case 0:
                        Toast.makeText(getApplicationContext(), "Please select a mess!", Toast.LENGTH_SHORT).show();

                        break;
                    case 1:
                        item=spinner.getSelectedItem().toString();
                        setMessName(item);
                        break;
                    case 2:
                         item=spinner.getSelectedItem().toString();
                        setMessName(item);
                        break;

                    case 3:
                         item=spinner.getSelectedItem().toString();
                        setMessName(item);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Please Select the Mess!", Toast.LENGTH_SHORT).show();
            }
        });



    }
    private void setMessName(String s){
        seletedMessl=s;
    }
    private void saveMenuToFirebase(){
        String item1;
        String item2;
        String item3;
        item1=editTextitem1.getText().toString();
        item2=editTextitem2.getText().toString();
        item3=editTextitem3.getText().toString();
        if (item1==null && item2==null &&item3==null) {
            Toast.makeText(getApplicationContext(), "Cannot update an empty menu", Toast.LENGTH_SHORT).show();
        }
        else{
            Menu messmenu = new Menu(item1, item2, item3);

            databaseReference.child("148vqOGRjOS5sscrTA0ZJTqdyhF3").child(seletedMessl).setValue(messmenu);
            Toast.makeText(this, "Added Menu!!", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onClick(View v) {
        if(v==logoutButton)
        {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
        if(v==saveitem)
        {

        saveMenuToFirebase();
        }
    }
}
