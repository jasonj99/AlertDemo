package com.example.jasonj.alarmdemo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import static android.content.Context.ALARM_SERVICE;

public class MyBroadcastReceiver extends BroadcastReceiver {
    private static long count = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm + " + count++, Toast.LENGTH_LONG).show();

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        Intent intent0 = new Intent(context, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context.getApplicationContext(), 1, intent0, 0);

        long after5Seconds =  System.currentTimeMillis()+5*1000;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, after5Seconds, pendingIntent);
        }
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, after5Seconds, pendingIntent);
        }
        else {
            // do nothing, already using setRepeating() to do periodically alarm
        }
    }
}
