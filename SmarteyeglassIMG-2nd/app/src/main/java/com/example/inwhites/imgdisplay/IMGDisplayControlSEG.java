package com.example.inwhites.imgdisplay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sony.smarteyeglass.extension.util.SmartEyeglassControlUtils;
import com.sonyericsson.extras.liveware.aef.control.Control;
import com.sonyericsson.extras.liveware.extension.util.ExtensionUtils;
import com.sonyericsson.extras.liveware.extension.util.control.ControlExtension;
import com.sonyericsson.extras.liveware.extension.util.control.ControlTouchEvent;

import java.io.ByteArrayOutputStream;

/**
 * Created by Inwhites on 2015/6/27.
 */
public final class IMGDisplayControlSEG extends ControlExtension {

    /** Quality parameter for encoding PNG data. */
    private static final int PNG_QUALITY = 100;

    /** Size of initial buffer for encoding PNG data. */
    private static final int PNG_DEFAULT_CAPACITY =250;


    /** Renders display data to the SmartEyeglass screen using
     *  the bitmap method.
     */
    private final Renderer bitmapRenderer = new Renderer() {
        // Render layout when an update is needed
        @Override
        public void init() {
            updateBitmap();
        }
        // Render display data using bitmap method
        @Override
        public void update() {
            updateBitmap();
        }
        // Retrieve the name of the Renderer
        @Override
        public String toString() {
            return "BITMAP";
        }
    };

    /** Map swipe direction to rendering method. */
    private final SparseArray<Renderer> rendererMap =
            new SparseArray<Renderer>();

    /** Instance of the Control Utility class. */
    private final SmartEyeglassControlUtils utils;

    /** Uses SmartEyeglass API version*/
    private static final int SMARTEYEGLASS_API_VERSION = 1;

    /** The application context. */
    private final Context context;

    /** Manages the counter and the icon image */
    public final State state;

    /** The chosen renderer object, for either layout or bitmap method. */
    private Renderer renderer;

    /**
     * Instantiates a control object, initializing the rendering-method map
     * so that a swipe-left action renders a bitmap, and the swipe-right
     * action renders a layout.
     *
     * @param context            The context.
     * @param hostAppPackageName Package name of SmartEyeglass host application.
     */
    public IMGDisplayControlSEG(final Context context,
                               final String hostAppPackageName) {
        super(context, hostAppPackageName);
        this.context = context;
        utils = new SmartEyeglassControlUtils(hostAppPackageName, null);
        utils.setRequiredApiVersion(SMARTEYEGLASS_API_VERSION);
        utils.activate(context);
        state = State.getState();
        rendererMap.put(Control.Intents.SWIPE_DIRECTION_LEFT, bitmapRenderer);
       // rendererMap.put(Control.Intents.SWIPE_DIRECTION_RIGHT, layoutRenderer);
    }

    // Reset state object and assign initial renderer object
    @Override
    public void onStart() {

       // renderer = layoutRenderer;
        renderer = bitmapRenderer;
    }

    // Update the display when app becomes visible, using the
    // current render method.
    @Override
    public void onResume() {
        // Send a UI when the extension becomes visible.
        renderer.init();
        super.onResume();
    }

    // Clean up data structures on termination.
    @Override
    public void onDestroy() {
        Log.d(Constants.LOG_TAG, "onDestroy: HelloLayoutsControl");
        utils.deactivate();
    };

    // Respond to touch on controller by updating the display
    // using the current rendering method.
    @Override
    public void onTouch(final ControlTouchEvent event) {
        super.onTouch(event);

        Log.d(Constants.LOG_TAG,
                "onTouch: HelloLayoutsControl " + renderer
                        + " - " + event.getX()
                        + ", " + event.getY());

        if (event.getAction() != Control.Intents.TOUCH_ACTION_RELEASE) {
            return;
        }

        // Switch the state of icon and update the display using current
        // render method.
        state.update();
        renderer.update();
    }

    // Respond to swipe on controller by setting
    // the rendering method.
    @Override
    public void onSwipe(final int direction) {
        Renderer next = rendererMap.get(direction);
        if (next == null) {
            return;
        }
        renderer = next;

        // Reset state object after change of rendering method
      //  state.reset();
        // Initialize and update display using the render method
        //renderer.init();
    }

    /**
     * Renders a bitmap to the display.
     * Populates a layout to compose the image and text,
     * then converts it to a bitmap for display with showBitmap().
     */
    private void updateBitmap() {
        // Initialize layout display parameters
        RelativeLayout root = new RelativeLayout(context);
        root.setLayoutParams(new RelativeLayout.LayoutParams(
                R.dimen.smarteyeglass_control_width,
                R.dimen.smarteyeglass_control_height));

        // Set dimensions and properties of the bitmap to fit the screen.
        final ScreenSize size = new ScreenSize(context);
        final int width = size.getWidth();
        final int height = size.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        bitmap.compress(Bitmap.CompressFormat.PNG, PNG_QUALITY,
                new ByteArrayOutputStream(PNG_DEFAULT_CAPACITY));
        bitmap.setDensity(DisplayMetrics.DENSITY_LOW);

        // Use the bitmap.xml layout resource as a base.
        RelativeLayout layout = (RelativeLayout) RelativeLayout.inflate(context,
                R.layout.layout, root);
        // Sets dimensions of the layout to those of the bitmap.
        layout.measure(height, width);
        layout.layout(0, 0, layout.getMeasuredWidth(),
                layout.getMeasuredHeight());

        // Adds 1 to counter value and retrieves it
        int count = state.getCount();
        // Set the caption text for this view in the layout's TextView element
      /*  if (count > 0) {
            TextView textView =
                    (TextView) layout.findViewById(R.id.btn_update_this);
            textView.setText(getCaption(count));
        }*/

        // Set the icon to add to the layout's ImageView element.
        ImageView imageView = (ImageView) layout.findViewById(R.id.image);
       // imageView.setImageResource(state.getImage());

        if (new FilesList().getImageFiles().size()==0) {
            imageView.setImageResource(R.drawable.warnning);
        }
        else
        imageView.setImageURI(Uri.fromFile(state.getImage().get(count)));
        // Convert the entire layout to a bitmap using a canvas.
        Canvas canvas = new Canvas(bitmap);
        layout.draw(canvas);

        // Update the screen to display the bitmap
        utils.showBitmap(bitmap);
    }




    /**
     * Extracts a display string for the current screen from resources.
     *
     * @param count The current screen.
     * @return      The display string.
     */
    private String getCaption(final int count) {
        return null ;
    }

    /**
     * Retrieves the URI string corresponding to a resource ID.
     *
     * @param id The resource ID.
     * @return   The URI string.
     */
    private String getUriString(final int id) {
        return ExtensionUtils.getUriString(context, id);
    }




}
