package com.delaroystudios.videostream.data;

/**
 * Created by delaroy on 11/2/17.
 */

/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class VideoStream {

    private String mtitle;

    private String mhostUrl;

    private String mUrl;

    public VideoStream(String title, String hostUrl) {
        mtitle = title;
        mhostUrl = hostUrl;
    }

    public String getTitle() {
        return mtitle;
    }

    public String getHostUrl() {
        return mhostUrl;
    }

}
