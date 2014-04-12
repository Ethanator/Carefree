package com.carefree.carefree;

import android.app.LauncherActivity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;

import java.util.ArrayList;

/**
 * Created by Normn on 4/12/2014.
 */
public class ListProvider implements RemoteViewsFactory {
    private ArrayList listItemList = new ArrayList();
    private Context context = null;
    private int appWidgetId;

    public ListProvider(Context context, Intent intent) {
        this.context = context;
        appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

        populateListItem();
    }

    private void populateListItem() {
        int i = 0;
       for (i = 0; i < 10; i++) {
            listItemList.add(i
                    + " This is the content of the app widget listview.Nice content though");
        }

    }

    @Override
    public int getCount() {
        return listItemList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onCreate() {

    }
    public RemoteViews getLoadingView() {
        // You can create a custom loading view (for instance when getViewAt()
        // is slow.) If you
        // return null here, you will get the default loading view.
        return null;
    }
    public boolean hasStableIds() {
        return true;
    }
    public void onDataSetChanged() {
        // This is triggered when you call AppWidgetManager
        // notifyAppWidgetViewDataChanged
        // on the collection view corresponding to this factory. You can do
        // heaving lifting in
        // here, synchronously. For example, if you need to process an image,
        // fetch something
        // from the network, etc., it is ok to do it here, synchronously. The
        // widget will remain
        // in its current state while work is being done here, so you don't need
        // to worry about
        // locking up the widget.
    }

    public int getViewTypeCount() {
        return 1;
    }

    public void onDestroy() {
        // In onDestroy() you should tear down anything that was setup for your
        // data source,
        // eg. cursors, connections, etc.
        //mWidgetItems.clear();
    }
    /*
    *Similar to getView of Adapter where instead of View
    *we return RemoteViews
    *
    */
    @Override
    public RemoteViews getViewAt(int position) {
        final RemoteViews remoteView = new RemoteViews(
                context.getPackageName(), R.layout.list_row);
        Object listItem = listItemList.get(position);
         //listItem = listItemList.get(position);
        remoteView.setTextViewText( R.id.listViewWidget ,listItem.toString());
        //remoteView.setTextViewText(R.id., listItem.heading);
       //remoteView.setTextViewText(R.id.content, listItem.content);

        return remoteView;
    }
}