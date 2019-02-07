package com.springcloud.weibo.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springcloud.weibo.constant.UserPhotoAlbumNameConstant;
import com.springcloud.weibo.mapper.HeadImgMapper;
import com.springcloud.weibo.mapper.PhotoAlbumMapper;
import com.springcloud.weibo.mapper.UserFilePathMapper;
import com.springcloud.weibo.pojo.HeadImg;
import com.springcloud.weibo.pojo.PhotoAlbum;
import com.springcloud.weibo.pojo.UserFilePath;
import com.springcloud.weibo.util.UserFileUrlUtil;
import com.springcloud.weibo.util.UserLocalFilePathUtil;

@Transactional
@Service
public class InitUserRequireInfoServiceImpl implements InitUserRequireInfoService {

    private final String TAG = InitUserRequireInfoServiceImpl.class.getName();

    @Autowired
    private UserFilePathMapper userFilePathMapper;

    @Autowired
    private PhotoAlbumMapper photoAlbumMapper;

    @Autowired
    private HeadImgMapper headImgMapper;

    @Override
    public int initUserHeadImg(String uuid, String uid) throws IOException {
        System.out.println(TAG + "::initUserHeadImg");
        int count = 0;
        if (uuid != null) {
            if (uid != null) {
                Integer albumid = initUserHeadImgAlbum(uid);
                if (albumid != null) {
                    String pictureURLName = copyDefaultPictureToUserPicturePath(uuid);
                    HeadImg headImg = new HeadImg();
                    headImg.setName("我萌吗？");
                    headImg.setUrl(UserFileUrlUtil.getPictureUrl(uuid, pictureURLName));
                    headImg.setIsCurrentHead("Y");
                    headImg.setUid(uid);
                    headImg.setAlbumId(albumid);
                    count = headImgMapper.insertSelective(headImg);
                    if (count > 0) {
                        System.out.println("头像插入成功");
                    } else {
                        System.out.println("头像图片初始化失败");
                    }
                }
            }
        }
        return count;
    }

    private Integer initUserHeadImgAlbum(String uid) throws IOException {
        System.out.println(TAG + "::initUserHeadImgAlbum");
        PhotoAlbum photoAlbum = new PhotoAlbum();
        photoAlbum.setName(UserPhotoAlbumNameConstant.HEAD_IMAGE_ALBUM);
        photoAlbum.setUid(uid);
        int count1 = photoAlbumMapper.insertSelective(photoAlbum);
        PhotoAlbum msgPhotoAlbum = new PhotoAlbum();
        msgPhotoAlbum.setName(UserPhotoAlbumNameConstant.PHOTO_ALBUM);
        msgPhotoAlbum.setUid(uid);
        int count2 = photoAlbumMapper.insertSelective(msgPhotoAlbum);

        if (count1 > 0 && count2 > 0) {
            System.out.println("头像专辑相册初始化成功");
            System.out.println("微博相册初始化成功");
            return photoAlbum.getId();
        }
        return null;
    }

    // 拷贝系统默认图片文件夹下的图片到用户图片文件夹下
    private String copyDefaultPictureToUserPicturePath(String pathId) throws IOException {
        System.out.println(TAG + "::copyDefaultPictureToUserPicturePath");

        String sysDefaultPicturePath = UserLocalFilePathUtil.USER_DEFAULT_HEAD_PICTURE;
        String defaultPictureName = UUID.randomUUID().toString() + ".jpg";
        String userDefaultPicturePath = UserLocalFilePathUtil.getLocalPicturePath(pathId, defaultPictureName);

        File sourcePictureFile = new File(sysDefaultPicturePath);
        File desPictureFile = new File(userDefaultPicturePath);
        System.out.println(sourcePictureFile.exists());
        boolean isSucces = false;
        if (sourcePictureFile.exists()) {
            if (!desPictureFile.exists()) {
                OutputStream outputStream = new FileOutputStream(desPictureFile);
                isSucces = true;
                System.out.println(Files.copy(sourcePictureFile.toPath(), outputStream));
            }
        }
        if (!isSucces) {
            defaultPictureName = null;
        }
        return defaultPictureName;
    }

    @Override
    public String initUserFilePath(String uid) {
        System.out.println(TAG + "::initUserFilePath");
        String uuid = UUID.randomUUID().toString();
        String[] paths = {UserLocalFilePathUtil.getLocalPicturePath(uuid, ""),
                UserLocalFilePathUtil.getLocalMusicPath(uuid, ""), UserLocalFilePathUtil.getLocalVideoPath(uuid, "")};
        boolean result = false;
        for (String path : paths) {
            File file = new File(path);
            result = file.mkdirs();
            if (result) {
                System.out.println(result);
            }
        }
        if (result) {
            UserFilePath userFilePath = new UserFilePath();
            userFilePath.setUuid(uuid);
            userFilePath.setUid(uid);
            int count = userFilePathMapper.insert(userFilePath);
            if (count <= 0) {
                uuid = null;
                System.out.println("用户文件夹创建成功");
            }
        }
        return uuid;
    }

}
