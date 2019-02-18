package com.krishna.wrokmanagerdemo;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.provider.UserDictionary;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;

import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class WorkManagers extends Worker{
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_TEXT = "text";
    public WorkManagers(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

//        for (int i = 0; i <5 ; i++) {
//            try {
//                Thread.sleep(5);
//                System.out.println("Work"+ i);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        String title = getInputData().getString(EXTRA_TITLE);
        String message = getInputData().getString(EXTRA_TEXT);

        sendNotification(title , message);
        return Result.SUCCESS;
    }

    private void sendNotification(String title, String message) {
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        //If on Oreo then notification required a notification channel.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("1001", "WorkTest", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), "1001")
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.mipmap.ic_launcher);

        notificationManager.notify(1, notification.build());
    }

}
