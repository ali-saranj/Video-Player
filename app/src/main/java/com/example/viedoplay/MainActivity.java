package com.example.viedoplay;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView   video;
    ImageButton bifore, next, start;
    SeekBar  seekBar;
    TextView tv;
    boolean  videoStop = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        video = findViewById(R.id.Video);
        bifore = findViewById(R.id.bifore);
        start = findViewById(R.id.start1);
        next = findViewById(R.id.next);
        seekBar = findViewById(R.id.seek);
        tv = findViewById(R.id.tv);

        video.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.aa));
        video.start();

        tv.setText(0 + " / " + 59);

        start.setOnClickListener(Start);
        next.setOnClickListener(Next);
        bifore.setOnClickListener(Bifore);
        seekBar.setOnSeekBarChangeListener(Seek);

        Handler h = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                tv.setText((video.getCurrentPosition() / 1000) + " / " + (seekBar.getMax() / 1000));
                h.postDelayed(this, 1000);
            }
        };
        h.postDelayed(runnable, 1000);
    }

    public final View.OnClickListener Start = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (videoStop) {
                video.start();
                start.setImageResource(android.R.drawable.ic_media_pause);
                videoStop = false;
            } else {
                video.pause();
                videoStop = true;
                start.setImageResource(android.R.drawable.ic_media_play);
            }
        }
    };

    public final View.OnClickListener Next = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            video.seekTo(video.getCurrentPosition() + 10000);
        }
    };

    public final View.OnClickListener Bifore = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            video.seekTo(video.getCurrentPosition() - 10000);
        }
    };

    public final SeekBar.OnSeekBarChangeListener Seek = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            tv.setText((progress / 1000) + " / " + (seekBar.getMax() / 1000));
            video.seekTo(progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}