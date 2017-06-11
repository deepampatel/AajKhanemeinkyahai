package com.example.deepampatel.aajkhanemeinkyahai;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.io.IOException;
import java.net.URL;

public class seeMenu extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private TextView item1;
    private TextView item2;
    private TextView item3;

    private TextView updateTime;
    String selectedmess;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_menu);




        item1=(TextView)findViewById(R.id.item1);
        item2=(TextView)findViewById(R.id.item2);
        item3=(TextView)findViewById(R.id.item3);

        updateTime=(TextView)findViewById(R.id.timeOfUpdate);
        progressDialog=new ProgressDialog(this);
        selectedmess = getIntent().getStringExtra("MESS_NAME");
        url="https://aajkhanemeinkyahai.firebaseio.com/148vqOGRjOS5sscrTA0ZJTqdyhF3/"+selectedmess+".json";
        new menutask().execute();
    }
    public class menutask extends AsyncTask<URL,Void,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Getting the menu..");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(URL... params) {
            String searchresults=null;
            try{
                searchresults=NetworkUtils.getResponseFromHttpUrl(new URL(url));

            }catch (IOException e){
                e.printStackTrace();
            }
            return searchresults;
        }

        @Override
        protected void onPostExecute(String s) {
            if(s != null && !s.equals("")){
            Menu menu=NetworkUtils.readdatfromjson(s);
                progressDialog.dismiss();
                item1.setText(menu.item1);
                item2.setText(menu.item2);
                item3.setText(menu.item3);
                updateTime.setText(menu.timeStamp);
            }
            else{
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Please try again later!", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
