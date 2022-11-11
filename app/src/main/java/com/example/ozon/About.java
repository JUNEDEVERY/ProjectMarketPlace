package com.example.ozon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void onclickMain(View v){
        {
            startActivity(new Intent(this, MainActivity.class));
        }

    }
    public void onClickADD(View v){
        {
            startActivity(new Intent(this, Addendum.class));
        }

    }
    public void onClickVK(View v){
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/digitalfenom"));
        startActivity(browser);

    }
    public void onClicktTG(View v){
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/nikitadevery"));
        startActivity(browser);
    }
    public void onClickInsta(View v){
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/n.forc3/"));
        startActivity(browser);
    }
}