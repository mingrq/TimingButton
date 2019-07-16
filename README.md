# 最新版本
 2.0.0
#

### 使用
#
```
在 build.gradle 中添加
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
```
dependencies {
	        implementation 'com.github.mingrq:TimingButton:Tag'
	}
```
### 方法
#
### boolean isTimer()

#### 获取是否正在倒计时
返回值
``
true：正在倒计时
``
``
false：倒计时未开始
``
#
### void setTimerLisenter(final int buttonType, int timerTime, final TimerLisenter timerLisenter)

#### 设置监听器
buttonType：按钮类型
``
BUTTON_TIMING:定时按钮 ``  `` BUTTON_TIMER：倒计时按钮    
``

timerTime：倒计时时间 ``单位:秒``

timerLisenter: 倒计时监听

```
timingButton.setTimerLisenter(TimingButton.BUTTON_TIMING, 6, new TimingButton.TimerLisenter() {
            /**
             * 
             * @param time 剩余时间（秒）
             */
            @Override
            public void clocking(int time) {
                //正在倒计时 
            }

            
            @Override
            public void timerOver() {
               //倒计时完成
            }

            @Override
            public void onClick(boolean state) {
                //倒计时按钮点击
            }
        });
```
#
### void start()
#### 开始倒计时
#
### void stop()  
#### 倒计时停止
