package net.decenternet.technicalexam.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import net.decenternet.technicalexam.R;
import net.decenternet.technicalexam.ui.tasks.TasksActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ImageView ivBrandingLogo;
    public static Boolean theCatalyst = true;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivBrandingLogo = (ImageView) findViewById(R.id.ivBrandingLogo);

        //todo: add timer to redirect to TaskActivity

        /**
         * Tasks
         * 1. Change the text "Put your favorite quote here" with your own quote.
         * 2. Set any image that best illustrate the quote to ivBrandingLogo.
         * 3. Display this screen for 3 seconds, then redirect to TasksActivity and close this MainActivity.
         */


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                cancel();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), TasksActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                });

            }
        }, 3* 1000);

    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}