package com.ming.timingbutton_lib;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Author MingRuQi
 * E-mail mingruqi@sina.cn,
 * <p>
 * DateTime 2019/2/15 16:18
 */
public class TimingButton extends AppCompatButton {
    public static final int BUTTON_TIMING = 0x00001;
    public static final int BUTTON_TIMER = 0x00002;
    private TimerLisenter timerLisenter = null;
    private boolean isTimer = false;//是否正在倒计时
    private int timerTime;//倒计时时间
    private int cashTimerTime;//倒计时时间缓存
    private Timer timer;
    private TimerTask timerTask;
    private Handler handler;
    private int buttonType;

    public TimingButton(Context context) {
        this(context, null);
    }

    public TimingButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimingButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (timerLisenter != null) {
                    timerLisenter.clocking(msg.arg1);
                    if (msg.arg1 == 0) {
                        overTimer();
                    }
                }
            }
        };
    }

    /**
     * 结束倒计时
     */
    private void overTimer() {
        isTimer = false;
        timerTask.cancel();
        timer.cancel();
        timerTime = cashTimerTime;
        if (timerLisenter != null) {
            timerLisenter.timerOver();
        }
    }

    /**
     * 开始倒计时
     */
    private void startTimer() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if (timerTime >= 0) {
                    if (timerLisenter != null) {
                        Message message = handler.obtainMessage();
                        message.arg1 = timerTime;
                        handler.sendMessage(message);
                    }
                    timerTime--;
                }
            }
        };
        isTimer = true;//开始倒计时
        timer.schedule(timerTask, 0, 1000);
    }


    /*---------------------------对外方法-----------------------------------*/

    /**
     * 获取是否正在倒计时
     *
     * @return false:未开始倒计时  true:正在倒计时
     */
    public boolean isTimer() {
        return isTimer;
    }

    /**
     * 设置倒计时监听
     *
     * @param buttonType    按钮类型
     * @param timerTime     倒计时时间
     * @param timerLisenter 监听器
     */
    public void setTimerLisenter(final int buttonType, int timerTime, final TimerLisenter timerLisenter) {
        this.buttonType = buttonType;
        this.timerTime = cashTimerTime = timerTime;
        this.timerLisenter = timerLisenter;
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerLisenter != null)
                    timerLisenter.onClick(isTimer);
                switch (buttonType) {
                    case BUTTON_TIMER:
                        if (!isTimer) {
                            startTimer();
                        }
                        break;
                    case BUTTON_TIMING:
                        if (isTimer) {
                            overTimer();
                        }
                        break;
                }
            }
        });
    }


    /**
     * 开始倒计时
     */
    public void start() {
        if (buttonType == BUTTON_TIMING){
            startTimer();
        }
    }

    /**
     * 结束倒计时
     */
    public void stop() {
        overTimer();
    }


    /*------------------------接口----------------------------*/

    /**
     * 倒计时监听
     */
    public interface TimerLisenter {
        /**
         * 正在倒计时
         *
         * @param time 剩余时间（秒）
         */
        void clocking(int time);

        /**
         * 倒计时结束
         */
        void timerOver();

        /**
         * 点击按钮
         *
         * @param timering false:未开始倒计时  true：正在倒计时
         */
        void onClick(boolean timering);
    }
}
