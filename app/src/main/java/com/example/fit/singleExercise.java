package com.example.fit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class singleExercise extends AppCompatActivity {


    TextView Title;
    TextView Desc;

        // Initializers


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_exercise);
        final Intent intent = getIntent();

        Title = findViewById(R.id.Ex_Title);
        Desc = findViewById(R.id.Ex_Desc);
        //Sets toolbar as action bar
        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar); // sets toolbar as Actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // setting the toolbar title
        setTitle(intent.getStringExtra("Title"));
        // Title and description are set from the data being received
        Title.setText(intent.getStringExtra("Title"));
        Desc.setText(intent.getStringExtra("Description"));
        Desc.setMovementMethod(new ScrollingMovementMethod()); // Allows the description to be scrollable - this was used as many descriptions tend to be long
        // Sets text to srollable should it overflow
        YouTubePlayerView youTubePlayerView =(YouTubePlayerView) findViewById(R.id.youtube_player_view);        // Fetches Youtube player view

        // Youtube Player by  Pierfrancescosoffritti this is used to create a youtube player view  and display the video according to the vidId string being received
        getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = intent.getStringExtra("vidId");
                youTubePlayer.cueVideo(videoId, 0);
            }
        });


    }

}
