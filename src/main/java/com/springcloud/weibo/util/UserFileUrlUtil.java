package com.springcloud.weibo.util;

public class UserFileUrlUtil {

    public static final String PRE_PATH = "/r/";

    public static final String PICTURE_STUFFIX_PATH = "/picture/";

    public static final String MUSIC_STUFFIX_PATH = "/music/";

    public static final String VIDEO_STUFFIX_PATH = "/video/";

    public static final String getPictureUrl(final String pathUUID, final String pictureName) {
        return PRE_PATH + pathUUID + PICTURE_STUFFIX_PATH + pictureName;
    }

    public static final String getMusicUrl(final String pathUUID, final String musicName) {
        return PRE_PATH + pathUUID + MUSIC_STUFFIX_PATH + musicName;
    }

    public static final String getVideoUrl(final String pathUUID, final String videoName) {
        return PRE_PATH + pathUUID + VIDEO_STUFFIX_PATH + videoName;
    }

}
