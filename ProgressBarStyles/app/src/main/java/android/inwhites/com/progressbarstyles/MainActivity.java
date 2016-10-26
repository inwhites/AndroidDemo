package android.inwhites.com.progressbarstyles;
/**
 *
 * @author inwhites
 * @time 2016/10/12 10:53
 */


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private RoundProgressBar mRoundProgressBar;
    private HorizontalProgressBar mHorizontalProgressBar;
    Button mButton = null;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mRoundProgressBar.setProgress( msg.arg1);
            mHorizontalProgressBar.setProgress(msg.arg1);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRoundProgressBar = (RoundProgressBar) findViewById(R.id.roundBar);
        mHorizontalProgressBar = (HorizontalProgressBar) findViewById(R.id.horizonBar);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: " +"****");
                setRoundProgressBar();
            }
        });



    }

    public void setRoundProgressBar(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                int progress = 0;
                while (true){
                    if(progress == 100)
                        break;
                    Message message = mHandler.obtainMessage();

                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    progress+=1;
                    Log.d("TAG", "run: " + progress);
                    message.arg1 = progress;
                    message.sendToTarget();
                }
            }
        }).start();

    }

}
