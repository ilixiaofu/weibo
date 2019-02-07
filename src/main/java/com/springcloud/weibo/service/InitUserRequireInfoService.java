package com.springcloud.weibo.service;

import java.io.IOException;

public interface InitUserRequireInfoService {

    int initUserHeadImg(String pathId, String uId) throws IOException;

    String initUserFilePath(String uid);
}
