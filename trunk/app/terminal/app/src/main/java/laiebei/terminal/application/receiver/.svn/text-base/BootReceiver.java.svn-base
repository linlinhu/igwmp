package laiebei.terminal.application.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2016/8/24.
 */
public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intentApp = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        intentApp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intentApp);
    }
}
