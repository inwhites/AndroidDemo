package com.example.inwhites.imgdisplay;

import android.content.Intent;

import com.sonyericsson.extras.liveware.aef.control.Control;
import com.sonyericsson.extras.liveware.extension.util.ExtensionService;
import com.sonyericsson.extras.liveware.extension.util.ExtensionUtils;
import com.sonyericsson.extras.liveware.extension.util.control.ControlExtension;
import com.sonyericsson.extras.liveware.extension.util.registration.DeviceInfoHelper;
import com.sonyericsson.extras.liveware.extension.util.registration.RegistrationInformation;

/**
 * Created by Inwhites on 2015/6/27.
 */
public final class SEGExtensionService extends ExtensionService {

    public static IMGDisplayControlSEG mIMGDisplayControlSEG;
    public static    SEGExtensionService Object;

    public SEGExtensionService(){super(Constants.EXTENSION_KEY);}


    public void onCreate(){
        super.onCreate();




    }


    @Override
    protected RegistrationInformation getRegistrationInformation() {
        return new IMGDisplayRegistrationInformation(this);
    }

    @Override
    protected boolean keepRunningWhenConnected() {
        return false;
    }


    @Override
    public ControlExtension createControlExtension(
            final String hostAppPackageName) {
        boolean isApiSupported = DeviceInfoHelper
                .isSmartEyeglassScreenSupported(this, hostAppPackageName);
        if (isApiSupported) {
            return new IMGDisplayControlSEG(this, hostAppPackageName);


        } else {
            throw new IllegalArgumentException(
                    "No control for: " + hostAppPackageName);
        }
    }


    public void startSEGcontrol()
    {
        Intent intent = new
                Intent(Control.Intents.CONTROL_START_REQUEST_INTENT);
        ExtensionUtils.sendToHostApp(
                SEGExtensionService.Object.getApplicationContext(),"com.sony.smarteyeglass",intent);

    }
}
