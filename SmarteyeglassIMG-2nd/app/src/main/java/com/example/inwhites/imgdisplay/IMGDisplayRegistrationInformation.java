package com.example.inwhites.imgdisplay;

import android.content.ContentValues;
import android.content.Context;

import com.sonyericsson.extras.liveware.aef.registration.Registration.ExtensionColumns;
import com.sonyericsson.extras.liveware.extension.util.ExtensionUtils;
import com.sonyericsson.extras.liveware.extension.util.registration.RegistrationInformation;

/**
 * Provides information needed during extension registration.
 */
public final class IMGDisplayRegistrationInformation
        extends RegistrationInformation {

    /** The application context. */
    private final Context context;

    /** Uses control API version*/
    private static final int CONTROL_API_VERSION = 4;

    /**
     * Creates registration object.
     *
     * @param context
     *            The context.
     */
    public IMGDisplayRegistrationInformation(final Context context) {
        this.context = context;
    }

    @Override
    public int getRequiredWidgetApiVersion() {
        return RegistrationInformation.API_NOT_REQUIRED;
    }

    @Override
    public int getRequiredNotificationApiVersion() {
        return RegistrationInformation.API_NOT_REQUIRED;
    }

    @Override
    public int getRequiredControlApiVersion() {
        return CONTROL_API_VERSION;
    }

    @Override
    public int getRequiredSensorApiVersion() {
        return RegistrationInformation.API_NOT_REQUIRED;
    }

    @Override
    public int getTargetControlApiVersion() {
        return CONTROL_API_VERSION;
    }


    @Override
    public ContentValues getExtensionRegistrationConfiguration() {
        String iconHostapp = ExtensionUtils.getUriString(context,
                R.drawable.icon);
        String iconExtension = ExtensionUtils.getUriString(context,
                R.drawable.icon_extension);

        ContentValues values = new ContentValues();
        values.put(ExtensionColumns.CONFIGURATION_ACTIVITY,
                MainActivity.class.getName());
        values.put(ExtensionColumns.CONFIGURATION_TEXT,
                context.getString(R.string.configuration_text));
        values.put(ExtensionColumns.NAME,
                context.getString(R.string.extension_name));
        values.put(ExtensionColumns.EXTENSION_KEY, Constants.EXTENSION_KEY);
        values.put(ExtensionColumns.HOST_APP_ICON_URI, iconHostapp);
        values.put(ExtensionColumns.EXTENSION_ICON_URI, iconExtension);
        values.put(ExtensionColumns.NOTIFICATION_API_VERSION,
                getRequiredNotificationApiVersion());
        values.put(ExtensionColumns.PACKAGE_NAME, context.getPackageName());
        return values;
    }

    @Override
    public boolean isDisplaySizeSupported(final int width, final int height) {
        ScreenSize size = new ScreenSize(context);
        return size.equals(width, height);
    }


    private String getUriString(final int id) {
        return ExtensionUtils.getUriString(context, id);
    }

}


