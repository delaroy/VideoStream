package com.delaroystudios.videostream;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.delaroystudios.videostream.data.VideoStream;

import java.util.List;

/**
 * Created by delaroy on 11/3/17.
 */

public class VideoStreamAdapter extends ArrayAdapter<VideoStream> {

    public VideoStreamAdapter(Context context, List<VideoStream> videoStreams) {
        super(context, 0, videoStreams);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.video_items, parent, false);
        }

        VideoStream currentStream = getItem(position);

        TextView title = (TextView) listItemView.findViewById(R.id.video_title);
        String textTitle = currentStream.getTitle();
        title.setText(textTitle);

        TextView textSubs = (TextView) listItemView.findViewById(R.id.text_subs);

        if (currentStream.getTitle() != null){
            String alphabet = currentStream.getTitle().substring(0, 1).toUpperCase();
            textSubs.setText(alphabet);
        }

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }

}
