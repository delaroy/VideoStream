package com.delaroystudios.videostream;


import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.widget.ListPopupWindow.MATCH_PARENT;
import static com.delaroystudios.videostream.MainActivity.API_KEY;

/**
 * Created by delaroy on 11/23/17.
 */

public class VideoPlayer extends YouTubeFailureRecoveryActivity implements
        YouTubePlayer.OnFullscreenListener {


    private LinearLayout baseLayout;
    private YouTubePlayerView playerView;
    private YouTubePlayer player;

    private View otherViews;

    private boolean fullscreen;

    public static final String EXTRA_TITLE = "videotitle";
    public static final String EXTRA_VIDEOID = "videoid";
    String title,videoId;
    TextView videoTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.video_play);
        baseLayout = (LinearLayout) findViewById(R.id.layout);
        playerView = (YouTubePlayerView) findViewById(R.id.player);
        otherViews = findViewById(R.id.other_views);

        title = getIntent().getExtras().getString(EXTRA_TITLE);
        videoId = getIntent().getExtras().getString(EXTRA_VIDEOID);

        videoTitle = (TextView) findViewById(R.id.videoName);
        videoTitle.setText(title);

        playerView.initialize(API_KEY, this);

        doLayout();
    }

    @Override
    public void onResume(){
        super.onResume();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            otherViews.setVisibility(View.GONE);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            LinearLayout.LayoutParams playerParams =
                    (LinearLayout.LayoutParams) playerView.getLayoutParams();
            playerParams.width = LayoutParams.MATCH_PARENT;
            playerParams.height = LayoutParams.MATCH_PARENT;

        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                        boolean wasRestored) {
        this.player = player;
        // Specify that we want to handle fullscreen behavior ourselves.
        player.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
        player.setOnFullscreenListener(this);
        if (!wasRestored) {
            player.cueVideo(videoId);
        }
    }

    @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return playerView;
    }


    private void doLayout() {
        LinearLayout.LayoutParams playerParams =
                (LinearLayout.LayoutParams) playerView.getLayoutParams();
        if (fullscreen) {
            // When in fullscreen, the visibility of all other views than the player should be set to
            // GONE and the player should be laid out across the whole screen.
            playerParams.width = LayoutParams.MATCH_PARENT;
            playerParams.height = LayoutParams.MATCH_PARENT;

            otherViews.setVisibility(View.GONE);
        } else {
            // This layout is up to you - this is just a simple example (vertically stacked boxes in
            // portrait, horizontally stacked in landscape).
            otherViews.setVisibility(View.VISIBLE);
            ViewGroup.LayoutParams otherViewsParams = otherViews.getLayoutParams();
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                playerParams.width = otherViewsParams.width = 0;
                playerParams.height = WRAP_CONTENT;
                otherViewsParams.height = MATCH_PARENT;
                playerParams.weight = 1;
                baseLayout.setOrientation(LinearLayout.HORIZONTAL);
            } else {
                playerParams.width = otherViewsParams.width = MATCH_PARENT;
                playerParams.height = WRAP_CONTENT;
                playerParams.weight = 0;
                otherViewsParams.height = 0;
                baseLayout.setOrientation(LinearLayout.VERTICAL);
            }
        }
    }



    @Override
    public void onFullscreen(boolean isFullscreen) {
        fullscreen = isFullscreen;
        doLayout();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        doLayout();
    }

}