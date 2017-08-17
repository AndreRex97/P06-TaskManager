package sg.edu.rp.c346.p06_taskmanager;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.NotificationCompat;

public class MyReceiverTask extends BroadcastReceiver {

    int reqCode = 12345;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Intent i = new Intent(context, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, reqCode, i, PendingIntent.FLAG_CANCEL_CURRENT);
        String name = intent.getStringExtra("name");

        NotificationCompat.Action action = new
                NotificationCompat.Action.Builder(
                R.mipmap.ic_launcher,
                name,
                pIntent).build();

        Intent intentreply = new Intent(context,
                MainActivity.class);
        PendingIntent pendingIntentReply = PendingIntent.getActivity
                (context, 0, intentreply,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteInput ri = new RemoteInput.Builder("status")
                .setLabel("Status report")
                .setChoices(new String [] {"Completed", "Not yet"})
                .build();

        NotificationCompat.Action action2 = new
                NotificationCompat.Action.Builder(
                R.mipmap.ic_launcher,
                "Reply",
                pendingIntentReply)
                .addRemoteInput(ri)
                .build();


        NotificationCompat.WearableExtender extender = new
                NotificationCompat.WearableExtender();
        extender.addAction(action);
        extender.addAction(action2);

        Notification notification = new
                NotificationCompat.Builder(context)
                .setContentText(name)
                .setContentTitle("Task")
                .setSmallIcon(R.mipmap.ic_launcher)
                .extend(extender)
                .build();

        NotificationManagerCompat notificationManagerCompat =
                NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(reqCode, notification);


        //build notification
//        Notification.Builder builder = new Notification.Builder(context);
//        builder.setContentTitle(name);
//        builder.setContentText("Task");
//        builder.setSmallIcon(android.R.drawable.ic_dialog_info);
//        builder.setContentIntent(pIntent);
//        builder.setAutoCancel(true);
//        Notification n = builder.build();
//        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(123, n);
    }
}
