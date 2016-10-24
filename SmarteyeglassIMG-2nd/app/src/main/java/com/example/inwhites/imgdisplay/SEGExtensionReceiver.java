package com.example.inwhites.imgdisplay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Inwhites on 2015/6/27.
 */
public class SEGExtensionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        intent.setClass(context,SEGExtensionService.class);
        context.startService(intent);
    }
}
