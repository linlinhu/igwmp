package laiebei.terminal.application.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.WindowManager;

import com.laiebei.terminal.R;

import laiebei.terminal.application.views.ColorDialog;
import laiebei.terminal.common.utilcode.AppUtils;
import timber.log.Timber;

/**
 * Created by Portgas.D.Ace on 2016/4/6.
 */
public class EngModeSwitchBroadcasetReceiver extends BroadcastReceiver {

    private void showDialog(final Context context){
        ColorDialog dialog = new ColorDialog(context);
        dialog.setTitle(context.getString(R.string.str_cozy_prompt));
        dialog.setContentText(context.getString(R.string.app_name) + "\n" + context.getString(R.string.str_exit_software));
        dialog.setAnimationEnable(true);
        //dialog.setColor(context.getResources().getColor(R.color.color_dialog_corlor));
        dialog.setPositiveListener(context.getString(R.string.str_confirm), new ColorDialog.OnPositiveListener() {
            @Override
            public void onClick(ColorDialog dialog) {
                AppUtils.killForcible(context);
            }
        });
        dialog.setNegativeListener(context.getString(R.string.str_cancel), new ColorDialog.OnNegativeListener() {
            @Override
            public void onClick(ColorDialog dialog) {
                dialog.cancel();
            }
        });
        dialog.getWindow()
                .setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.show();

    }

    @Override
    public void onReceive(Context context, Intent intent) {
//        boolean mIsEngMode = intent.getIntExtra("state", 1) == 1 ? true : false;
//        AppUtils.killForcible(context);
        Timber.w("接收到调试退出广播");
        showDialog(context);
    }
}
