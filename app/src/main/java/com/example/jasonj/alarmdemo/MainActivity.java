package com.example.jasonj.alarmdemo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startAlert();
    }

    public void startAlert() {
        long interval = 5*1000;
        long after5Seconds =  System.currentTimeMillis()+5*1000;

        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 1, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, after5Seconds, pendingIntent);
        //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, after5Seconds, interval, pendingIntent);
        //alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, after5Seconds, pendingIntent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, after5Seconds, pendingIntent);
        }
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, after5Seconds, pendingIntent);
        }
        else
        {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, after5Seconds, interval, pendingIntent);
        }
    }
}
