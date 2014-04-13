package com.carefree.carefree;

/**
 * Created by Normn on 4/12/2014.
 */
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.appwidget.AppWidgetProvider;
import android.net.Uri;
import android.view.View;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.util.List;


public class ExampleAppWidgetProvider extends AppWidgetProvider {

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;

        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];

            // Create an Intent to launch ExampleActivity
            Intent intent = new Intent(context, LocationActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);


           // RemoteViews remoteViews = updateWidgetListView(context,appWidgetIds[i]);
            //appWidgetManager.updateAppWidget(appWidgetIds[i],remoteViews);

            // Get the layout for the App Widget and attach an on-click listener
            // to the button
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_design);
           views.setOnClickPendingIntent(R.id.button, pendingIntent);
            views.setTextViewText(R.id.textView, "Test message PLus more data but it does remove the default");
            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

}