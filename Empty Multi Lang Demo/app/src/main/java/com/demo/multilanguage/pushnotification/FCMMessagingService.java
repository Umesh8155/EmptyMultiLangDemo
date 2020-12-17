package com.demo.multilanguage.pushnotification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.demo.multilanguage.R;
import com.demo.multilanguage.activity.MainActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

//import com.windex.saraswatividhyasankul.activitys.HomeWorkActivity;


public class FCMMessagingService extends FirebaseMessagingService {
    SharedPreferences pref;
    String TAG = "RVG";
    Bitmap myBitmap;


    //    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.e(TAG, "RemoteMessage" + remoteMessage.getData().toString());
        if (remoteMessage.getData().size() > 0) {

            Log.d(TAG, "Body---" + remoteMessage.getData().get("body"));
            Log.d(TAG, "title---" + remoteMessage.getData().get("title"));
            Log.d(TAG, "ValueType---" + remoteMessage.getData().get("ValueType"));
            Log.d(TAG, "icon---" + remoteMessage.getData().get("icon"));
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String currentDateandTime = sdf.format(new Date());
//            MainStartActivity.UpdateDb();
            Log.d(TAG, "Data ..............................inserted");
            showNotification(remoteMessage.getData().get("body"),
                    remoteMessage.getData().get("title"),
                    remoteMessage.getData().get("ValueType"),remoteMessage.getData().get("icon"));


        } else {



            initChannels(this);
            Intent intent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"default")
                    .setAutoCancel(true)
                    .setContentTitle(getResources().getString(R.string.app_name))
                    .setContentText("")
                    .setSmallIcon(R.drawable.logo)
                    .setContentIntent(pendingIntent);


            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone ringtone = RingtoneManager.getRingtone(this,uri);
            ringtone.play();

            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.notify(0, builder.build());
        }
    }

    private void showNotification(String message, String title, String type,String icon) {
        Intent i = null;
        Log.d(TAG, "message__" + message);
        Log.d(TAG, "title__" + title);
        Log.d(TAG, "type__" + type);
        Log.d(TAG, "icon__" + icon);
        Log.e("types",type);
        Log.e("icon",icon);


            i = new Intent(this, MainActivity.class);








        initChannels(this);
        Log.e("title",title);
        Log.e("icon=",icon);
        getBitmapFromURL(icon);


        if(icon.equals("")||icon==null){
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"default")
                    .setAutoCancel(true)
                    .setContentTitle(title)
                    .setContentText(message)
                    //.setContentText(Html.fromHtml(message))
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                    .setSmallIcon(R.drawable.logo)
                    .setContentIntent(pendingIntent);
            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone ringtone = RingtoneManager.getRingtone(this,uri);
            ringtone.play();
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.notify(0, builder.build());

        }
        else {
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"default")
                    .setAutoCancel(true)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                    .setSmallIcon(R.drawable.logo)
                    .setStyle(new NotificationCompat.BigPictureStyle()
                            .bigPicture(myBitmap)
                            .bigLargeIcon(null))
                    .setContentIntent(pendingIntent);
            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone ringtone = RingtoneManager.getRingtone(this,uri);
            ringtone.play();
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.notify(0, builder.build());
        }










    }

    public void initChannels(Context context) {
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel("default",
                "Channel name",
                NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription("Channel description");
        notificationManager.createNotificationChannel(channel);
    }
    public Bitmap getBitmapFromURL(String strURL) {
        try {
            URL url = new URL(strURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }




}

