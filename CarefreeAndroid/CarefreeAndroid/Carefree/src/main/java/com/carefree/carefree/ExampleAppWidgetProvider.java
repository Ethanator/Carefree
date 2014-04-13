package com.carefree.carefree;

/**
 * Created by Normn on 4/12/2014.
 */
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

import com.firebase.client.Firebase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.provider.Settings.Global.getString;


public class ExampleAppWidgetProvider extends AppWidgetProvider {
    public static String SCREEN_ON = "TurnScreenOn";

            ;
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;

        int layoutID = 0;


           layoutID = R.layout.widget_design;

        Intent active = new Intent(context, ExampleAppWidgetProvider.class);
        active.setAction(SCREEN_ON);
        PendingIntent actionPendingIntent = PendingIntent.getBroadcast(context, 0, active, PendingIntent.FLAG_UPDATE_CURRENT);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), layoutID);
        remoteViews.setOnClickPendingIntent(R.id.button, actionPendingIntent);
        remoteViews.setTextViewText(R.id.textView, "Test message PLus more data but it does remove the default");
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
            control.setTextViewText(R.id.textView, "Updated Screen On");

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