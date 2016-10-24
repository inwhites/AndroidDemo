package com.example.inwhites.imgdisplay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener,ViewSwitcher.ViewFactory,AdapterView.OnItemSelectedListener {


    private ImageView imageView;
    private ImageView imageView2;
    private FilesList filesList = new FilesList();
    int cnt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView= (ImageView)findViewById(R.id.imageView);
        imageView2= (ImageView)findViewById(R.id.imageView2);
        if(new FilesList().getImageFiles().size()==0){
            imageView.setImageResource(R.drawable.warnning);
            imageView2.setImageResource(R.drawable.warnning);
        }
        else {
            imageView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageView2.setImageURI(Uri.fromFile(filesList.getImageFiles().get(State.getState().getCount())));
                }
            });
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(cnt<filesList.getImageFiles().size()){
                    imageView.setImageURI(Uri.fromFile(filesList.getImageFiles().get(cnt)));
                    cnt++;}
                    else
                        cnt=0;
                }
            });
        }



    }









    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public View makeView() {
        return null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
