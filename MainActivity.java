package com.example.speech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playTTSENG(View view) {
        // Untuk menuju ke activity Text To Speech Versi English
        Intent intent = new Intent(this, TextToSpeechEnglish.class);
        startActivity(intent);
    }

    public void playTTSID(View view) {
        // Untuk menuju ke activity Text To Speech Versi Indonesia
        Intent intent = new Intent(this, TextToSpeechIndonesia.class);
        startActivity(intent);
    }

    public void playSTT(View view) {
        // Untuk menuju ke activity Speech To Text
        Intent intent = new Intent(this, SpeechToText.class);
        startActivity(intent);
    }
}