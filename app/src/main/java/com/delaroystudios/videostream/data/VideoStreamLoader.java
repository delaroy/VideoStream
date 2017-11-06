package com.delaroystudios.videostream.data;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.delaroystudios.videostream.QueryUtils;

import java.util.List;

/**
 * Created by delaroy on 11/2/17.
 */

public class VideoStreamLoader extends AsyncTaskLoader<List<VideoStream>> {

    /** Tag for log messages */
    private static final String LOG_TAG = VideoStreamLoader.class.getName();

    /** Query URL */
    private String mUrl;


    public VideoStreamLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<VideoStream> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        List<VideoStream> videoStreams = QueryUtils.fetchVideo(mUrl);
        return videoStreams;
    }
}
