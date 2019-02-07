package com.springcloud.weibo.constant;

public class ViewResourceConstant {

    public static final String HOME_PAGE = "weibo.html";

    public static final String REGISTER_PAGE = "register.html";

    public static final class UserHomePage {
        public static final String PREFIX = "UHP/";
        public static final String STUFFIX = ".html";
        public static final String USER_HOME_PAGE = PREFIX + "index" + STUFFIX;
    }

    public static final class UpdateUserInfoPage {
        public static final String PREFIX = "UUIP/";
        public static final String STUFFIX = ".html";
        public static final String UPDATE_USER_INFO_PAGE = PREFIX + "index" + STUFFIX;
    }

    public static final class UpdatePasswordPage {
        public static final String PREFIX = "UUPP/";
        public static final String STUFFIX = ".html";
        public static final String UPDATE_USER_PASSWORD_PAGE = PREFIX + "changepassword" + STUFFIX;
    }

    public static final class MyPage {
        public static final String PREFIX = "m/";
        public static final String STUFFIX = ".html";
        public static final String MY_WEIBO_PAGE = PREFIX + "index" + STUFFIX;
    }

    public static final class BackStageManagement {
        public static final String PREFIX = "admin/";
        public static final String STUFFIX = ".html";
        public static final String MANAGMENT_PAGE = PREFIX + "houtai" + STUFFIX;
    }
}
