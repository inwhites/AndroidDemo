package com.example.inwhites.imgdisplay;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by inwhites on 2016/3/18.
 */
public class FilesList {


    private ArrayList<File> imageFiles;


    private int cnt;

    public FilesList(){

            this.imageFiles = findFile();
            this.cnt = this.imageFiles.size();
    }



    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }



    public ArrayList<File> getImageFiles() {
        return imageFiles;
    }

    public void setImageFiles(ArrayList<File> imageFiles) {
        this.imageFiles = imageFiles;
    }

    private ArrayList<File> findFile(){//查找目录，创建文件夹
        ArrayList<File>   fl= new ArrayList<File>();
        File dir = Environment.getExternalStorageDirectory();
        File fileDir = new File(dir+File.separator+"IMAGE");
        boolean sdCardExit = Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED);
        if(sdCardExit){

            if(!fileDir.exists())
                  fileDir.mkdir();
        }

        File[]  listFile = fileDir.listFiles();
        for(int i=0;i<listFile.length;i++){
            if(listFile[i].toString().endsWith(".jpg")||listFile[i].toString().endsWith(".png")){
                fl.add(listFile[i]);
            }
        }

       return fl;
    }








}
