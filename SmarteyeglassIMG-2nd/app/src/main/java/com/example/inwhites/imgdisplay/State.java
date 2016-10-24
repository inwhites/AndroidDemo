package com.example.inwhites.imgdisplay;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Inwhites on 2015/7/3.
 */
public final class State {
    private static State state = null;
    private FilesList filesList = new FilesList();
    /** Contains the counter value shown in the UI. */
    private int count=0;


    /**
     * Creates a new instance.
     */
    private State() {

    }

    public static State getState(){
        if (state == null){
            state = new State();
        }
        return state;
    }


    /**
     * Resets the state.
     */


    /**
     * Updates the state. This method increments the counter and toggles the
     * icon.
     */
    public void update() {

        if (count>=0 && count < (filesList.getCnt()-1))
            ++count;
        else
            count=0;

    }

    /**
     * Returns the value of the counter.
     *
     * @return The counter.
     */
    public int getCount() {
        return count;
    }

    /**
     * Returns the resource ID of the icon.
     *
     * @return The resource ID of the icon.
     */
    public ArrayList<File> getImage() {
        //if(filesList==null)

        return filesList.getImageFiles();


    }
    public File getFile(int cnt) {

        return filesList.getImageFiles().get(cnt);


    }
}
