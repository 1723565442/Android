package com.qutingxin.my_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class DetailNewsActivity extends AppCompatActivity {

    private TextView news_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);

        Bundle bundle = this.getIntent().getExtras();
        news_title = findViewById(R.id.news_title);
        news_title.setText(bundle.getString("title"));

        findViewById(R.id.back_button1).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(DetailNewsActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("flag", 1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


}