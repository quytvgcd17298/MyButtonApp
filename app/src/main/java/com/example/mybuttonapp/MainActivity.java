package com.example.mybuttonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    Button btnBell, btnVibrate;


    public void OpenDialog(View view){
        final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.layout_dialog, null);

        final MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.beep);
        final Vibrator vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);

        btnBell = (Button) mView.findViewById(R.id.btnBell);
        btnVibrate = (Button) mView.findViewById(R.id.btnVibrate);

        alert.setView(mView);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        btnBell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayer.start();
            }
        });
        btnVibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                {
                    vibrator.vibrate(VibrationEffect.createOneShot(200,VibrationEffect.DEFAULT_AMPLITUDE));
                }
                else
                {
                    vibrator.vibrate(200);
                }
            }
        });
        alertDialog.show();
    }

}