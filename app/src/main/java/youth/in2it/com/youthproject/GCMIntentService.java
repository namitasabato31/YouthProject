package youth.in2it.com.youthproject;

/**
 * Created by IN2IT on 11/19/2015.
 */

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

public class GCMIntentService extends IntentService {

        // Sets an ID for the notification, so it can be updated
        public static final int notifyID = 9001;
        NotificationCompat.Builder builder;

        public GCMIntentService() {
            super("GcmIntentService");
        }

        @Override
        protected void onHandleIntent(Intent intent) {
            Bundle extras = intent.getExtras();
            Log.v("extras=",""+extras);
            GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);

            String messageType = gcm.getMessageType(intent);
            Log.v("messageType=",""+messageType);
            if (!extras.isEmpty()) {
               /* if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR
                        .equals(messageType)) {
                    sendNotification("Send error: " + extras.toString());
                } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED
                        .equals(messageType)) {
                    sendNotification("Deleted messages on server: "
                            + extras.toString());
                } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE
                        .equals(messageType)) {
                    sendNotification("Message Received from Google GCM Server:nn"
                            + extras.get("message"));
                }*/
                sendNotification(extras.get("message").toString());
            }
            GcmBroadcastReceiver.completeWakefulIntent(intent);
        }

    private void sendNotification(String msg) {
        Intent resultIntent = new Intent(this, HomeActivity.class);
        resultIntent.putExtra("msg", msg);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0,
                resultIntent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder mNotifyBuilder;
        NotificationManager mNotificationManager;

        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotifyBuilder = new NotificationCompat.Builder(this)
                .setContentTitle("Alert")
                .setContentText("You've received new message."+msg)
                .setSmallIcon(R.drawable.ic_launcher);
        // Set pending intent
        mNotifyBuilder.setContentIntent(resultPendingIntent);

        // Set Vibrate, Sound and Light
        int defaults = 0;
        defaults = defaults | Notification.DEFAULT_LIGHTS;
        defaults = defaults | Notification.DEFAULT_VIBRATE;
        defaults = defaults | Notification.DEFAULT_SOUND;

        mNotifyBuilder.setDefaults(defaults);
        // Set the content for Notification
        mNotifyBuilder.setContentText("New message from Server"+msg);
        // Set autocancel
        mNotifyBuilder.setAutoCancel(true);
        // Post a notification
        mNotificationManager.notify(notifyID, mNotifyBuilder.build());
    }
}