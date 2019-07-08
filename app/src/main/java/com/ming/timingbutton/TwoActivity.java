package com.ming.timingbutton;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ming.timingbutton_lib.TimingButton;

/**
 * Author MingRuQi
 * E-mail mingruqi@sina.cn
 * DateTime 2019/2/15 16:50
 */
public class TwoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        final TimingButton var = findViewById(R.id.var);
        Button ss = findViewById(R.id.ss);
        ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TwoActivity.this, String.valueOf(var.isTimer()), Toast.LENGTH_SHORT).show();
            }
        });
        var.setTimerLisenter(TimingButton.BUTTON_TIMER, 6, new TimingButton.TimerLisenter() {
            @Override
            public void clocking(int time) {
                var.setText(String.valueOf(time) + "秒");
            }

            @Override
            public void timerOver() {
                var.setText("重新获取验证码");
            }

            @Override
            public void onClick(boolean state) {
                if (state) {
                    Toast.makeText(TwoActivity.this, "正在获取验证码", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TwoActivity.this, "开始获取验证码", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
