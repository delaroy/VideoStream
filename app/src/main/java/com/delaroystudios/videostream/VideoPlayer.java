package com.delaroystudios.videostream;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import static com.delaroystudios.videostream.MainActivity.API_KEY;

/**
 * Created by delaroy on 11/23/17.
 */

public class VideoPlayer extends YouTubeFailureRecoveryActivity {

    public static final String EXTRA_TITLE = "videotitle";
    public static final String EXTRA_VIDEOID = "videoid";
    String title,videoId;
    TextView videoTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_player);

         title = getIntent().getExtras().getString(EXTRA_TITLE);
        videoId = getIntent().getExtras().getString(EXTRA_VIDEOID);

        videoTitle = (TextView) findViewById(R.id.videoName);
        videoTitle.setText(title);

        YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(API_KEY, this);
    }
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                        boolean wasRestored) {
        if (!wasRestored) {
            player.cueVideo(videoId);
        }
    }

    @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }
}
