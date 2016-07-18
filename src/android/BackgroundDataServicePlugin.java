package com.albaresapps.plugins;

import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;


/**
 * This class will start/stop a service, update a database, and perform geolocation
 */
public class BackgroundDataServicePlugin extends CordovaPlugin {
    private static final String keywordStartService = "STARTDATASERVICE";

    //main execute function
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        Log.i("DataService", "Action received: " + action);
        //check for the action and perform a match
        if(action.equalsIgnoreCase(keywordStartService)) {
            Log.i("DataService", "Actions matched");
            Intent serviceIntent = new Intent(this.cordova.getActivity().getApplicationContext(), DataService.class);
            this.cordova.getActivity().startService(serviceIntent);
        }

        return false;
    }

    public static class DataService extends Service {

        //private variables
        private NotificationManager nM;
        private static final int NOTIFICATION = 12345;


        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            Log.i("DataService", "Service has started");
            //showNotification();
            return START_NOT_STICKY;
        }

        @Override
        public void onCreate() {
            Log.i("DataService", "Service Created");
            //init local notification service
            nM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        }

        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        private void showNotification() {
            Log.i("DataService", "Notification started");
            //init notification
            Notification notification = new Notification.Builder(this)
                    .setTicker("Service has started")
                    .setWhen(System.currentTimeMillis())
                    .setContentTitle("New Message Received")
                    .setContentText("Service Started")
                    .build();
            nM.notify(NOTIFICATION, notification);

        }
    }
}
