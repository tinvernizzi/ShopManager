package widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.widget.RemoteViews;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Random;

import tanguy.shopmanager.R;
import tanguy.shopmanager.weather.WeatherManager;

/**
 * Created by tanguy on 26/04/2017.
 */

public class WeatherWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final int count = appWidgetIds.length;

        for (int i = 0; i < count; i++) {
            int widgetId = appWidgetIds[i];

            try {
                WeatherManager weather = new WeatherManager("Biot", context);
                String report = null;
                report = weather.generateWeatherReport();

                RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.weather_widget);
                remoteViews.setTextViewText(R.id.textView, report);

            Intent intent = new Intent(context, WeatherWidgetProvider.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                    0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.wholeWidget, pendingIntent);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}