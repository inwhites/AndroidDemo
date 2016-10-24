package com.example.inwhites.imgdisplay;

/**
 * Created by Inwhites on 2015/7/3. for test
 */
public interface  Renderer {


    /**
     * This method is called when the render is active.
     */
    void init();

    /**
     * This method is called when the state is changed and the render must
     * update the UI.
     */
    void update();
}
