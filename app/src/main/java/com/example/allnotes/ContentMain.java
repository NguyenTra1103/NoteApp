package com.example.allnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ContentMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_main);
    }

    public void ClickDraw(View view) {
        Intent intent = new Intent(this,NoteDrawActivity.class);
        startActivity(intent);
    }
}