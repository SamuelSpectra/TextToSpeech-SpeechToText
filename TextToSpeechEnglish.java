package com.example.speech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import java.util.Locale;

public class TextToSpeechEnglish extends AppCompatActivity {

    EditText input;
    Button clear, speak;
    SeekBar pitch, speed;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech_english);

        start();
    }

    void start() {
        // Deklarasi layout untuk widget
        input = (EditText) findViewById(R.id.input);
        clear = (Button) findViewById(R.id.clear);
        speak = (Button) findViewById(R.id.speak);
        pitch = (SeekBar) findViewById(R.id.seekPitch);
        speed = (SeekBar) findViewById(R.id.seekSpeed);

        // Inisialisasi Fungsi Text To Speech menggunakan library dari Android Studio
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    // Inisialisasi bahasa yang digunakan disini menggunakan bahasa inggris
                    int result = tts.setLanguage(Locale.ENGLISH);
                    // Logika untuk mengecek bahasa data bahasa hilang atau tidak dan mengecek apakah bahasa didukung atau tidak
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "This language is not supported");
                    } else {
                        speak.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "initialization failed");
                }
            }
        });

        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                say();
            }
        });
    }

    void say() {
        String text = input.getText().toString();
        // Inisialisasi untuk mengatur setinggan pitch yang ingin digunakan
        float seekpitch = (float) pitch.getProgress() / 50;
        if (seekpitch < 0.1) {
            seekpitch = 0.1f;
        }

        // Inisialisasi untuk mengatur setingan speed yang ingin digunakan
        float seekspeed = (float) speed.getProgress() / 50;
        if (seekspeed < 0.1) {
            seekspeed = 0.1f;
        }

        tts.setPitch(seekpitch);
        tts.setSpeechRate(seekspeed);

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    public void btnClear(View view) {
        // inisialisasi untuk button clear yang digunakan untuk menghapus inputan
        switch (view.getId()) {
            case R.id.clear:
                input.setText("");
                break;
        }
    }
}