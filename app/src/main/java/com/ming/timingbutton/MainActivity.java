package com.ming.timingbutton;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


import com.ming.timingbutton_lib.TimingButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TimingButton timingButton = findViewById(R.id.jump);
        timingButton.setText("跳过");
        timingButton.setTimerLisenter(TimingButton.BUTTON_TIMING, 6, new TimingButton.TimerLisenter() {
            @Override
            public void clocking(int time) {
                timingButton.setText(String.valueOf(time) + "跳过");
            }

            @Override
            public void timerOver() {
                startActivity(new Intent(MainActivity.this, TwoActivity.class));
                finish();
            }

            @Override
            public void onClick(boolean state) {

            }
        });
        timingButton.start();
    }
}
