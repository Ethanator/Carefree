package com.carefree.carefree;

/**
 * Created by Normn on 4/12/2014.
 */
import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.appwidget.AppWidgetProvider;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.util.Log;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

<<<<<<< HEAD
import com.firebase.client.Firebase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.provider.Settings.Global.getString;
=======
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
>>>>>>> 1f6e5c1e8ef81397ceb05da5aa7d4567cf799bd6


public class ExampleAppWidgetProvider extends AppWidgetProvider {
    public static String SCREEN_ON = "TurnScreenOn";

            ;
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;

        int layoutID = 0;


           layoutID = R.layout.widget_design;
        Intent gps=new Intent(context, useGPS.class);
        Intent active = new Intent(context, ExampleAppWidgetProvider.class);
        active.setAction(SCREEN_ON);
        PendingIntent actionPendingIntent = PendingIntent.getBroadcast(context, 0, active, PendingIntent.FLAG_UPDATE_CURRENT);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), layoutID);
        remoteViews.setOnClickPendingIntent(R.id.button, actionPendingIntent);
        remoteViews.setTextViewText(R.id.textView, "In case of Emergency");
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
        remoteViews.setOnClickPendingIntent(R.id.button, actionPendingIntent);
        context.startService(active);
        /*for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];

            // Create an Intent to launch ExampleActivity
            Intent intent = new Intent(context, LocationActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

           // RemoteViews remoteViews = updateWidgetListView(context,appWidgetIds[i]);
            //appWidgetManager.updateAppWidget(appWidgetIds[i],remoteViews);

            // Get the layout for the App Widget and attach an on-click listener
            // to the button
            //RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_design);
           //views.setOnClickPendingIntent(R.id.button, pendingIntent);
            remoteViews.setTextViewText(R.id.textView, "Test message PLus more data but it does remove the default");
            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }*/
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        RemoteViews updateViews;
        int j = 0;
        if (intent.getAction().equals(SCREEN_ON)) {
            String msg = "";
            try {
                msg = intent.getStringExtra("Message");
            } catch (NullPointerException e) {
                Log.e("Error", "No Message");
            }
            PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, 0);

            Log.d("Debg1", "WidgetAlar");

            RemoteViews control = new RemoteViews(context.getPackageName(),
                    R.layout.widget_design_son);

            String csvString = "";
            String outString = "";
            String filename = context.getString(R.string.local_file_name);
            FileInputStream fis;

            try {
                fis = context.openFileInput(filename);
                byte[] input = new byte[fis.available()];
                while (fis.read(input) != -1) {
                    csvString += new String(input);
                }
                String[] keys = csvString.split("\n")[0].split("\t");
                String[] vals = csvString.split("\n")[1].split("\t");

                for(j = 0;j < 6; j++){

                    outString += new String(keys[j]+": "+ vals[j]+"\n");

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            control.setTextViewText(R.id.textView, outString);

            ComponentName cn = new ComponentName(context,
                    ExampleAppWidgetProvider.class);
            AppWidgetManager.getInstance(context).updateAppWidget(cn,
                    control);

        }
        super.onReceive(context, intent);
    }

    public void uploadAlarm() {

    }
}