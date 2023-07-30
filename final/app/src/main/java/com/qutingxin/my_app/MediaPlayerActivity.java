package com.qutingxin.my_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class MediaPlayerActivity extends AppCompatActivity {

    private VideoView video;
    private MediaController mMediaController;
    public int sig;
    private Bundle bundle;
    private TextView video_title;

    private ImageView thumb;
    private ImageView share;
    private ImageView star;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);

        //接收数据
        bundle = this.getIntent().getExtras();
        video_title = findViewById(R.id.video_title);
        video_title.setText(bundle.getString("video_title"));

        video = findViewById(R.id.video_player);
        video.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.my_video));

        mMediaController = new MediaController(this);
        mMediaController.setMediaPlayer(video);
        video.setMediaController(mMediaController);

        video.start();
        sig = 1;


        //返回
        findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MediaPlayerActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("flag", 2);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }

        );
        thumb = findViewById(R.id.thumb);
        thumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thumb.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
            }
        });

        share = findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
            }
        });

        star = findViewById(R.id.star_whiet);
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
            }
        });
    }


}