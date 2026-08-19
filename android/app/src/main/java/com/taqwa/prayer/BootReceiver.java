package com.taqwa.prayer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * BootCompletedReceiver
 *
 * Reschedules prayer time notifications after the device is rebooted.
 * Per F-Droid / FOSS compliance: no Google Play Services dependencies.
 * Uses standard Android APIs only.
 *
 * Notifications are managed by the Capacitor Local Notifications plugin.
 * This receiver simply triggers the web app to re-schedule them.
 */
public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null) return;

        String action = intent.getAction();
        if (Intent.ACTION_BOOT_COMPLETED.equals(action)
                || Intent.ACTION_MY_PACKAGE_REPLACED.equals(action)
                || "android.intent.action.QUICKBOOT_POWERON".equals(action)) {

            // Mark that notifications need to be rescheduled.
            // The main Activity will pick this up on next launch.
            SharedPreferences prefs = context.getSharedPreferences("taqwa_prefs", Context.MODE_PRIVATE);
            prefs.edit().putBoolean("reschedule_notifications", true).apply();
        }
    }
}
