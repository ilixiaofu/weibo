package com.springcloud.weibo.util;

public class UserLocalFilePathUtil {

    public static final String PRE_PATH = "E:/resource/";

    public static final String PICTURE_STUFFIX_PATH = "/picture/";

    public static final String USER_DEFAULT_HEAD_PICTURE = PRE_PATH + "default" + PICTURE_STUFFIX_PATH + "default.jpg";

    public static final String MUSIC_STUFFIX_PATH = "/music/";

    public static final String VIDEO_STUFFIX_PATH = "/video/";

    public static final String getLocalPicturePath(final String pathUUID, final String pictureName) {
        return PRE_PATH + pathUUID + PICTURE_STUFFIX_PATH + pictureName;
    }

    public static final String getLocalMusicPath(final String pathUUID, final String musicName) {
        return PRE_PATH + pathUUID + MUSIC_STUFFIX_PATH + musicName;
    }

    public static final String getLocalVideoPath(final String pathUUID, final String videoName) {
        return PRE_PATH + pathUUID + VIDEO_STUFFIX_PATH + videoName;
    }

}
