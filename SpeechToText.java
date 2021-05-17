package com.example.speech;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class SpeechToText extends AppCompatActivity {

    private TextView result;
    private Button mbtnSpeak;
    private final int REQ_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_to_text);

        start();
    }

    void start() {
        // Deklarasi Layout untuk Widget
        result = (TextView) findViewById(R.id.placeholder);
        mbtnSpeak = (Button) findViewById(R.id.btnSpeak);

        // Membuat sebuah click listener yang memanggil fungsi tanyaInputSuara
        mbtnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tanyaInputSuara();
            }
        });
    }

    public void tanyaInputSuara() {
        // memanggil library Speech To Text menggunakan RecognizerIntent
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        // Mengambil bahasa default dari Speech To Text yaitu Bahasa Inggris
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Hi, Say Something");

        try {
            // memulai activity untuk menjalankan Speech To Text
            startActivityForResult(intent, REQ_CODE);
        } catch (ActivityNotFoundException a) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Toast.makeText(this, "Hasil suara ditampilkan", Toast.LENGTH_SHORT).show();
        // mengambil request code dari startActivityForResult
        switch (requestCode) {
            case REQ_CODE : {
                // Mengeceek apakah ada resultCode yang dikirim atau tidak
                if (resultCode == RESULT_OK && null != data) {
                    // Jika ada resultCode yang dikirim maka data akan disimpan kedalam sebuah ArrayList
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    // Memanggil kata yang disimpan dalam ArrayList pada index ke-0
                    this.result.setText(result.get(0));
                }
            break;
            }
        }
    }
}